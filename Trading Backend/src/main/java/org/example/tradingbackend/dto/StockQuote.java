package org.example.tradingbackend.dto;

//package org.example.tradingbackend.dto;

import lombok.Data;

@Data
public class StockQuote {
    private String symbol;
    private double price;
}

