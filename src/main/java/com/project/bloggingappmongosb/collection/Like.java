package com.project.bloggingappmongosb.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document(collection = "likes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Like {
    @Id
    private String id;
    @DBRef
    private Post post;
    @DBRef
    private List<User> users;
}
