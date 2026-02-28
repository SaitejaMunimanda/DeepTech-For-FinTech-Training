package org.example.tradingbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String symbol;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalAmount;

    private String type; // BUY or SELL

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
