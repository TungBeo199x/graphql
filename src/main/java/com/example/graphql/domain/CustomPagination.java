package com.example.graphql.domain;

import lombok.Data;

import java.util.List;

@Data
public class CustomPagination {
    List<User> data;
    Integer totalPage;
    Integer currentPage;
}
