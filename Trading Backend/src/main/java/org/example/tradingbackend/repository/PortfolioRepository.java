package org.example.tradingbackend.repository;

import org.example.tradingbackend.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByUsernameAndSymbol(String username, String symbol);

    List<Portfolio> findByUsername(String username);
}
