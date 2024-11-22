package org.example.repettion.repository;

import org.example.repettion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.countMessageCurrentDay > 0")
    List<User> findUsersWithMessages();

    @Query("SELECT u FROM User u WHERE size(u.tasks) > 0")
    List<User> findUsersWithTasks();
}
