package com.project.bloggingappmongosb.controller;


import com.project.bloggingappmongosb.collection.Tag;
import com.project.bloggingappmongosb.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/Tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public Tag saveTag(@Valid @RequestBody Tag Tag){
        return tagService.createTag(Tag);
    }

    @GetMapping("{id}")
    public Tag getTagById(@PathVariable String id){
        return tagService.getTagById(id);
    }

    @DeleteMapping("{id}")
    public String deleteTag(@PathVariable String id){
        return tagService.deleteTag(id);
    }

    @GetMapping("/all")
    public List<Tag> getTags(){
        return tagService.getTags();
    }

    @PutMapping("{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable String id, @Valid @RequestBody Tag Tag){
        return tagService.updateTag(id,Tag);
    }
}
