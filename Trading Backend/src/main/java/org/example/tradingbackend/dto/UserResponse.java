package org.example.tradingbackend.dto;

//package org.example.tradingbackend.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class UserResponse {

    private String username;
    private String email;
    private BigDecimal balance;
}

