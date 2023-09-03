package com.deepeningInSpringBoot.service;

import com.deepeningInSpringBoot.domain.transaction.Transaction;
import com.deepeningInSpringBoot.domain.user.User;
import com.deepeningInSpringBoot.dto.TransactionDTO;
import com.deepeningInSpringBoot.repositories.TransactionRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRep repository;
    @Autowired
    private RestTemplate restTemplate;
    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User reciever = this.userService.findUserById(transaction.recieverId());

        userService.validadeUserTransaction(sender, transaction.value());
        boolean isAuthorized = this.authorizedTransaction(sender, transaction.value());
        if (!isAuthorized){
            throw new Exception("Transação não autorizada!");
        }

        Transaction newTrasaction = new Transaction();
        newTrasaction.setAmount(transaction.value());
        newTrasaction.setSender(sender);
        newTrasaction.setReciver(reciever);
        newTrasaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        reciever.setBalance(reciever.getBalance().add(transaction.value()));

        this.repository.save(newTrasaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(reciever);

    }

    public boolean authorizedTransaction(User sender, BigDecimal amount){
        ResponseEntity<Map> authorizedTransaction = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
        if (authorizedTransaction.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizedTransaction.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else return false;
    }
}
