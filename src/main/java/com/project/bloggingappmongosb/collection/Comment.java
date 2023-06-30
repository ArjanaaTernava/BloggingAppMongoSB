package com.project.bloggingappmongosb.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "comments")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    @Id
    private String id;
    @DBRef
    private Post post;
    @DBRef
    private User commenter;
    private String comment;
}
