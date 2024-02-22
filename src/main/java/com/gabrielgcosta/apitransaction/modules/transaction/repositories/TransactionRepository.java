package com.gabrielgcosta.apitransaction.modules.transaction.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielgcosta.apitransaction.modules.transaction.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
        
}
