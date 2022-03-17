package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.domain.User;
import com.example.graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    public User createNewUser(String username, String avatar) throws Exception {

        Optional<User> optionalUser = userRepository.getOneByUsername(username);
        if(optionalUser.isPresent()){
            throw new Exception("duplicate name");
        }
        User user = new User();

        user.setUsername(username);
        user.setAvatarUrl(avatar);

        return userRepository.save(user);
    }

    public User updateUser(String id, String username, String avatarUrl) throws Exception {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new Exception("User not found");
        }
        User user = optionalUser.get();

        user.setUsername(username);
        user.setAvatarUrl(avatarUrl);

        return userRepository.save(user);
    }

}
