package org.example.tradingbackend.dto;

//package com.trading.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TradeResponse {

    private String symbol;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private BigDecimal remainingBalance;
}

