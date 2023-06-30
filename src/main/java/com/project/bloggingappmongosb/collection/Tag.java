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
@Document(collection = "tags")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {
    @Id
    private String id;
    private String tagName;
    private String description;
    @DBRef
    private List<Post> posts;
}
