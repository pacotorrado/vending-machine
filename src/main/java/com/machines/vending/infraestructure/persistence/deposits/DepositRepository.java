package com.machines.vending.infraestructure.persistence.deposits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DepositRepository extends JpaRepository<DepositEntity, Integer> {
    @Query("SELECT d FROM DepositEntity d WHERE d.buyerId = :buyerId")
    Optional<DepositEntity> findByBuyerId(int buyerId);
}
