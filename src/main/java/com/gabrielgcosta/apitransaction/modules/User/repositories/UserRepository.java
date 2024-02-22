package com.gabrielgcosta.apitransaction.modules.User.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielgcosta.apitransaction.modules.User.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByDocument(String document); 

    Optional<UserEntity> findById(UUID id);    
}
