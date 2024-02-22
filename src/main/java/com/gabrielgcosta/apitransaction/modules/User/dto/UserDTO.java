package com.gabrielgcosta.apitransaction.modules.User.dto;

import java.math.BigDecimal;

import com.gabrielgcosta.apitransaction.modules.User.enums.UserType;


public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
    
}
