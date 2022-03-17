package com.example.graphql.repository;

import com.example.graphql.domain.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAll();

    Optional<User> getOneByUsername(String username);

    @NotNull
    Optional<User> findById(String id);

    Page<User> findByUsername(String username, Pageable pageable);
}
