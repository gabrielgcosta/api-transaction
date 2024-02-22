package com.gabrielgcosta.apitransaction.modules.transaction.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.gabrielgcosta.apitransaction.modules.User.entities.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    private LocalDateTime timeStamp;
    
}
