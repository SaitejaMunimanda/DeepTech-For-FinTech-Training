package org.example.tradingbackend.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockPriceService {

    private final Map<String, BigDecimal> stockPrices = new HashMap<>();

    public StockPriceService() {
        stockPrices.put("AAPL", new BigDecimal("180"));
        stockPrices.put("TSLA", new BigDecimal("250"));
        stockPrices.put("GOOGL", new BigDecimal("130"));
        stockPrices.put("INFY", new BigDecimal("1500"));
        stockPrices.put("TCS", new BigDecimal("3900"));
    }

    public BigDecimal getPrice(String symbol) {
        BigDecimal price = stockPrices.get(symbol.toUpperCase());
        if (price == null) throw new RuntimeException("Stock not supported: " + symbol);
        return price;
    }

    public Map<String, BigDecimal> getAllStocks() {
        return stockPrices;
    }
}
