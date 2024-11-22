package org.example.repettion.service;

import lombok.RequiredArgsConstructor;
import org.example.repettion.entity.Task;
import org.example.repettion.entity.User;
import org.example.repettion.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersWithMessages() {
        return userRepository.findUsersWithMessages();
    }

    @Override
    public void addTaskToUser(String message, User user) {
        Task task = new Task(message);
        task.setUser(user);
        user.getTasks().add(task);
    }

    @Override
    public List<User> findUsersWithTasks() {
        return userRepository.findUsersWithTasks();
    }
}
