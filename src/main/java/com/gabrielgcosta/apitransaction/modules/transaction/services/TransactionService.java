package com.gabrielgcosta.apitransaction.modules.transaction.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gabrielgcosta.apitransaction.modules.User.entities.UserEntity;
import com.gabrielgcosta.apitransaction.modules.User.services.UserService;
import com.gabrielgcosta.apitransaction.modules.notification.services.NotificationService;
import com.gabrielgcosta.apitransaction.modules.transaction.dto.TransactionDTO;
import com.gabrielgcosta.apitransaction.modules.transaction.entities.TransactionEntity;
import com.gabrielgcosta.apitransaction.modules.transaction.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public TransactionEntity createTransaction(TransactionDTO transaction) throws Exception{
        UserEntity receiver = this.userService.findById(transaction.receiverId());
        UserEntity sender = this.userService.findById(transaction.senderId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = authorizeTransaction(sender, transaction.value());
        if(!isAuthorized){
            throw new Exception("The transaction was not authorized");
        }

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transaction.value());
        transactionEntity.setReceiver(receiver);
        transactionEntity.setSender(sender);
        transactionEntity.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        transactionRepository.save(transactionEntity);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        notificationService.sendNotification(sender, "Transaction completed successfully");
        notificationService.sendNotification(receiver, "Transaction received successfully");

        return transactionEntity;
    }

    public boolean authorizeTransaction(UserEntity sender, BigDecimal amount){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    }

    public List<TransactionEntity> getAllTransactions(){
        return transactionRepository.findAll();
    }
    
}
