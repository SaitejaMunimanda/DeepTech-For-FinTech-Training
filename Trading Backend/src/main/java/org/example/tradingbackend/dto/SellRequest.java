package org.example.tradingbackend.dto;

//package org.example.tradingbackend.dto;

import lombok.Data;

@Data
public class SellRequest {

    private String username;
    private String symbol;
    private int quantity;
}

