package com.gabrielgcosta.apitransaction.modules.User.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.gabrielgcosta.apitransaction.modules.User.dto.UserDTO;
import com.gabrielgcosta.apitransaction.modules.User.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;
    private String password;

    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserEntity(UserDTO data){
        this.balance = data.balance();
        this.document = data.document();
        this.email = data.email();
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.password = data.password();
        this.userType = data.userType();

    }
    
}
