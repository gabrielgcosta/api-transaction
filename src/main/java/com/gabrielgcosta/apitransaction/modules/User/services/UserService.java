package com.gabrielgcosta.apitransaction.modules.User.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielgcosta.apitransaction.modules.User.dto.UserDTO;
import com.gabrielgcosta.apitransaction.modules.User.entities.UserEntity;
import com.gabrielgcosta.apitransaction.modules.User.enums.UserType;
import com.gabrielgcosta.apitransaction.modules.User.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void validateTransaction(UserEntity user, BigDecimal amount) throws Exception{
        if(user.getUserType() == UserType.MERCHANT){
            throw new Exception("This user can not make a transaction");
        }

        if(user.getBalance().compareTo(amount) < 0){
            throw new Exception("This user does not have enough balance to carry out the transaction");
        }

    }

    public UserEntity findById(UUID id) throws Exception{
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public UserEntity findByDocument(String document) throws Exception{
        return this.userRepository.findByDocument(document).orElseThrow(() -> new Exception("User not found"));
    }

    public UserEntity createUser(UserDTO user){
        UserEntity newUser = new UserEntity(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<UserEntity> listAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser(UserEntity user){
        this.userRepository.save(user);
    }
    
}
