package org.example.tradingbackend.service;

//package org.example.tradingbackend.service;

import org.example.tradingbackend.dto.StockQuote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class StockService {

    private final String API_KEY = "demo"; // later replace
    private final String URL = "https://finnhub.io/api/v1/quote?symbol=%s&token=%s";

    public StockQuote getStockPrice(String symbol) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format(URL, symbol, API_KEY);

        FinnhubResponse response = restTemplate.getForObject(apiUrl, FinnhubResponse.class);

        if (response == null || response.getC() == 0) {
            throw new RuntimeException("Stock not found or API limit reached");
        }

        StockQuote quote = new StockQuote();
        quote.setSymbol(symbol.toUpperCase());
        quote.setPrice(response.getC());

        return quote;
    }

    private static class FinnhubResponse {
        private double c;
        public double getC() { return c; }
        public void setC(double c) { this.c = c; }
    }
    public BigDecimal getLivePrice(String symbol) {
        // For now mock price (later we connect real API)
        return BigDecimal.valueOf(100 + new java.util.Random().nextInt(500));
    }

}

