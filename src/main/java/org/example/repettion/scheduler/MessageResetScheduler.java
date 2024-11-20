package org.example.repettion.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.repettion.model.User;
import org.example.repettion.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableAsync
public class MessageResetScheduler {
    private final UserService userService;

    @Scheduled(cron ="0 0 0 * * ?")
    @Async
    void messageReset() {
        List<User> users = userService.findUsersWithMessages();

        if (users == null || users.isEmpty()) {
            return;
        }

        users.stream().peek(user -> user.setCountMessageCurrentDay(0L))
                .forEach(userService::updateUser);
    }

}
