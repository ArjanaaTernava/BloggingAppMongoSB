package com.project.bloggingappmongosb.service;

import com.project.bloggingappmongosb.collection.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User createUser(User User);

    User getUserById(String id);

    String deleteUser(String id);

    List<User> getUsers();

    ResponseEntity<User> updateUser(String id, User User);
}
