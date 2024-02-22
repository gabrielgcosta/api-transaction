package com.gabrielgcosta.apitransaction.modules.User.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielgcosta.apitransaction.modules.User.dto.UserDTO;
import com.gabrielgcosta.apitransaction.modules.User.entities.UserEntity;
import com.gabrielgcosta.apitransaction.modules.User.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO dto) {
        UserEntity newUser = userService.createUser(dto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);                
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers(){
        List<UserEntity> users = userService.listAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    
}
