package com.deepeningInSpringBoot.service;

import com.deepeningInSpringBoot.domain.user.User;
import com.deepeningInSpringBoot.domain.user.UserType;
import com.deepeningInSpringBoot.repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRep repository;

    public void validadeUserTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("O usuario logista não pode fazer transações.");
        }

        if (sender.getBalance().compareTo(amount) < 0){
            throw new Exception("O usuario não tem saldo o suficiente para fazer a transação.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }

    public void saveUser(User id){
        this.repository.save(id);
    }
}
