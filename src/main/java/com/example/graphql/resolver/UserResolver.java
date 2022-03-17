package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.domain.CustomPagination;
import com.example.graphql.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLResolver<User> {

    @Autowired
    private Query query;

    @Autowired
    private Mutation mutation;

    public List<User> getAllUser(){
        return query.findAllUser();
    }

    public User findByUserId(String userId){
        return query.findByUserId(userId);
    }

    public User createNewUser(String username, String avatarUrl) throws Exception {
        return mutation.createNewUser(username, avatarUrl);
    }

    public User updateUser(String id, String username, String avatarUrl) throws Exception {
        return mutation.updateUser(id, username, avatarUrl);
    }

    public CustomPagination findPageUser(String username, Integer page, Integer size, String sort, Boolean direction){
        Pageable pageable = createPageable(page, size, sort, direction);
        return query.getPageUser(username, pageable);
    }

    private Pageable createPageable(Integer page, Integer size, String sort, Boolean direction) {
        String sortOrder = StringUtils.isEmpty(sort) ? "id" : sort;
        return PageRequest.of(page == null ? 0 : page, size == null ? 10 : size,
                Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, sortOrder));
    }
}
