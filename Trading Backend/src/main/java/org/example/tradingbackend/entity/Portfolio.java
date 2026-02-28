package org.example.tradingbackend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String symbol;
    private int quantity;
}

