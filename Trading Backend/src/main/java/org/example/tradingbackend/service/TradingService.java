package org.example.tradingbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.tradingbackend.dto.*;
import org.springframework.stereotype.Service;

import org.example.tradingbackend.entity.Portfolio;
import org.example.tradingbackend.entity.Transaction;
import org.example.tradingbackend.entity.User;
import org.example.tradingbackend.repository.PortfolioRepository;
import org.example.tradingbackend.repository.TransactionRepository;
import org.example.tradingbackend.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TradingService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final TransactionRepository transactionRepository;
    private final StockPriceService stockPriceService;   // 🔥 FIXED NAME


    // ================= BUY STOCK =================
    @Transactional
    public TradeResponse buyStock(TradeRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BigDecimal price = stockPriceService.getPrice(request.getSymbol()); // 🔥 FIXED

        BigDecimal totalAmount =
                price.multiply(BigDecimal.valueOf(request.getQuantity()));

        if (user.getBalance().compareTo(totalAmount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance().subtract(totalAmount));
        userRepository.save(user);

        Optional<Portfolio> existing =
                portfolioRepository.findByUsernameAndSymbol(
                        request.getUsername(),
                        request.getSymbol()
                );

        if (existing.isPresent()) {
            Portfolio portfolio = existing.get();
            portfolio.setQuantity(
                    portfolio.getQuantity() + request.getQuantity()
            );
            portfolioRepository.save(portfolio);
        } else {
            Portfolio portfolio = new Portfolio();
            portfolio.setUsername(request.getUsername());
            portfolio.setSymbol(request.getSymbol());
            portfolio.setQuantity(request.getQuantity());
            portfolioRepository.save(portfolio);
        }

        Transaction transaction = new Transaction();
        transaction.setUsername(request.getUsername());
        transaction.setSymbol(request.getSymbol());
        transaction.setQuantity(request.getQuantity());
        transaction.setPrice(price);
        transaction.setTotalAmount(totalAmount);
        transaction.setType("BUY");

        transactionRepository.save(transaction);

        return TradeResponse.builder()
                .symbol(request.getSymbol())
                .quantity(request.getQuantity())
                .price(price)
                .totalAmount(totalAmount)
                .remainingBalance(user.getBalance())
                .build();
    }


    // ================= SELL STOCK =================
    @Transactional
    public TradeResponse sellStock(SellRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Portfolio portfolio = portfolioRepository
                .findByUsernameAndSymbol(request.getUsername(), request.getSymbol())
                .orElseThrow(() -> new RuntimeException("Stock not found in portfolio"));

        if (request.getQuantity() > portfolio.getQuantity()) {
            throw new RuntimeException("Not enough shares to sell");
        }

        BigDecimal price = stockPriceService.getPrice(request.getSymbol()); // 🔥 FIXED

        BigDecimal totalAmount =
                price.multiply(BigDecimal.valueOf(request.getQuantity()));

        user.setBalance(user.getBalance().add(totalAmount));
        userRepository.save(user);

        int remainingQty = portfolio.getQuantity() - request.getQuantity();

        if (remainingQty == 0) {
            portfolioRepository.delete(portfolio);
        } else {
            portfolio.setQuantity(remainingQty);
            portfolioRepository.save(portfolio);
        }

        Transaction transaction = new Transaction();
        transaction.setUsername(request.getUsername());
        transaction.setSymbol(request.getSymbol());
        transaction.setQuantity(request.getQuantity());
        transaction.setPrice(price);
        transaction.setTotalAmount(totalAmount);
        transaction.setType("SELL");

        transactionRepository.save(transaction);

        return TradeResponse.builder()
                .symbol(request.getSymbol())
                .quantity(request.getQuantity())
                .price(price)
                .totalAmount(totalAmount)
                .remainingBalance(user.getBalance())
                .build();
    }


    // ================= PORTFOLIO =================
    public List<PortfolioItemResponse> getPortfolio(String username) {

        List<Portfolio> portfolioList = portfolioRepository.findByUsername(username);

        return portfolioList.stream().map(p -> {

            BigDecimal currentPrice = stockPriceService.getPrice(p.getSymbol()); // 🔥 FIXED

            BigDecimal totalValue =
                    currentPrice.multiply(BigDecimal.valueOf(p.getQuantity()));

            BigDecimal buyPrice = transactionRepository
                    .findByUsernameOrderByIdDesc(username).stream()
                    .filter(t -> t.getSymbol().equals(p.getSymbol()) && t.getType().equals("BUY"))
                    .map(Transaction::getPrice)
                    .findFirst()
                    .orElse(BigDecimal.ZERO);

            BigDecimal invested =
                    buyPrice.multiply(BigDecimal.valueOf(p.getQuantity()));

            BigDecimal profitLoss = totalValue.subtract(invested);

            return PortfolioItemResponse.builder()
                    .symbol(p.getSymbol())
                    .quantity(p.getQuantity())
                    .avgPrice(buyPrice)
                    .currentPrice(currentPrice)
                    .totalValue(totalValue)
                    .profitLoss(profitLoss)
                    .build();

        }).toList();
    }


    // ================= TRANSACTIONS =================
    public List<TransactionResponse> getTransactions(String username) {

        List<Transaction> transactions =
                transactionRepository.findByUsernameOrderByIdDesc(username);

        return transactions.stream().map(t ->
                TransactionResponse.builder()
                        .symbol(t.getSymbol())
                        .type(t.getType())
                        .quantity(t.getQuantity())
                        .price(t.getPrice())
                        .totalAmount(t.getTotalAmount())
                        .createdAt(t.getCreatedAt())
                        .build()
        ).toList();
    }
}
