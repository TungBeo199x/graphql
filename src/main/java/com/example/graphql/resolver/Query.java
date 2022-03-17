package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.domain.CustomPagination;
import com.example.graphql.domain.User;
import com.example.graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User findByUserId(String userId){
        return userRepository.findById(userId).orElse(null);
    }

    public CustomPagination getPageUser(String username, Pageable pageable) {

        Page<User> userPage = userRepository.findByUsername(username, pageable);

        CustomPagination customPagination = new CustomPagination();
        customPagination.setData(userPage.getContent());
        customPagination.setCurrentPage(userPage.getNumber());
        customPagination.setTotalPage(userPage.getTotalPages());

        return customPagination;

    }
}
