package org.example.repettion.service;

import org.example.repettion.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    List<User> findUsersWithMessages();
}
