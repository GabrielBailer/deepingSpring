package com.deepeningInSpringBoot.service;

import com.deepeningInSpringBoot.domain.user.User;
import com.deepeningInSpringBoot.domain.user.UserType;
import com.deepeningInSpringBoot.dto.UserDTO;
import com.deepeningInSpringBoot.repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User id){
        this.repository.save(id);
    }
}
