package com.project.bloggingappmongosb.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Builder
@Document(collection = "posts")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    @Id
    private String id;
    private String title;
    private String content;
    @DBRef
    private User author;
    private Date publicationDate;
    @DBRef
    private List<Tag> tags;
    @DBRef
    private List<Comment> comments;
    @DBRef
    private List<Like> likes;
    @DBRef
    private Category category;


}