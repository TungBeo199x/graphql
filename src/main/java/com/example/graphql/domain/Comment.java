package com.example.graphql.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document("comment")
@Data
public class Comment {

    @Id
    private String id;

    private User user;

    private String content;

    private List<String> likeIds;

    private String postId;

    private Date createdDate;

    private String replyToCommentId;

    private boolean edit;

    public Comment(String id, User user, String content, List<String> likeIds, String postId, String replyToCommentId, boolean edit) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.likeIds = likeIds;
        this.postId = postId;
        this.replyToCommentId = replyToCommentId;
        this.edit = edit;
    }

    public Comment(){
        super();
        this.id = UUID.randomUUID().toString();
    }

}
