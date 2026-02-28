package org.example.tradingbackend.dto;

//package com.trading.dto;

import lombok.Data;

@Data
public class TradeRequest {
    private String username;
    private String symbol;
    private int quantity;
}

