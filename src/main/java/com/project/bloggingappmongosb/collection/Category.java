package com.project.bloggingappmongosb.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "categories")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
    @Id
    private String id;
    private String categoryName;
    private String description;
    @DBRef
    private Post post;
}
