package org.example.tradingbackend.controller;


import lombok.RequiredArgsConstructor;
import org.example.tradingbackend.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.tradingbackend.service.TradingService;

import java.util.List;

@RestController
@RequestMapping("/api/trading")
@RequiredArgsConstructor
public class TradingController {

    private final TradingService tradingService;

    @PostMapping("/buy")
    public ResponseEntity<TradeResponse> buyStock(
            @RequestBody TradeRequest request) {

        return ResponseEntity.ok(
                tradingService.buyStock(request)
        );
    }

    @PostMapping("/sell")
    public ResponseEntity<TradeResponse> sellStock(
            @RequestBody SellRequest request) {

        return ResponseEntity.ok(
                tradingService.sellStock(request)
        );
    }
    @GetMapping("/portfolio/{username}")
    public List<PortfolioItemResponse> getPortfolio(@PathVariable String username) {
        return tradingService.getPortfolio(username);
    }

    @GetMapping("/transactions/{username}")
    public List<TransactionResponse> getTransactions(@PathVariable String username) {
        return tradingService.getTransactions(username);
    }




}
