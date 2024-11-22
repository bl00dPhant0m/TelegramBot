package org.example.repettion.service;

import org.example.repettion.entity.Task;
import org.example.repettion.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    List<User> findUsersWithMessages();
    void addTaskToUser(String message, User user);
    List<User> findUsersWithTasks();
}
