package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TagService {
    Tag createTag(Tag Tag);

    Tag getTagById(String id);

    String deleteTag(String id);

    List<Tag> getTags();

    ResponseEntity<Tag> updateTag(String id, Tag Tag);
}
