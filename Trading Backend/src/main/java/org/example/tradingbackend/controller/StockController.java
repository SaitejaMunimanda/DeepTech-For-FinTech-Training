package org.example.tradingbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.tradingbackend.service.StockPriceService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class StockController {

    private final StockPriceService stockPriceService;

    // ================= GET ALL STOCKS =================
    @GetMapping
    public Map<String, BigDecimal> getAllStocks() {
        return stockPriceService.getAllStocks();
    }

    // ================= GET SINGLE STOCK =================
    @GetMapping("/{symbol}")
    public Map<String, Object> getPrice(@PathVariable String symbol) {

        BigDecimal price = stockPriceService.getPrice(symbol);

        Map<String, Object> response = new HashMap<>();
        response.put("symbol", symbol.toUpperCase());
        response.put("price", price);

        return response;
    }
}
