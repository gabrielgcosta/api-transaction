package com.gabrielgcosta.apitransaction.modules.transaction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielgcosta.apitransaction.modules.transaction.dto.TransactionDTO;
import com.gabrielgcosta.apitransaction.modules.transaction.entities.TransactionEntity;
import com.gabrielgcosta.apitransaction.modules.transaction.services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionDTO dto) throws Exception{
        TransactionEntity transactionEntity = transactionService.createTransaction(dto);
        return new ResponseEntity<>(transactionEntity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getTransactions() {
        List<TransactionEntity> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    
    
}
