package org.example.repettion.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.repettion.bot.Bot;
import org.example.repettion.entity.Task;
import org.example.repettion.entity.User;
import org.example.repettion.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableAsync
@Slf4j
public class RemindScheduler {
    private final UserService userService;
    private final Bot bot;

    @Async
    @Scheduled(cron = "0 0 18,20 * * ?")
    public void remind() {
        List<User> users = userService.findUsersWithTasks();

        if (users.isEmpty()) {
            return;
        }

        for (User user : users) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Напоминание о ваших задачах:\n");
            List<Task> tasks = user.getTasks();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);

                stringBuilder.append(i + 1 + ". " + task.getMessage()
                        + " от " + task.getDateOfCreation() + "\n");
            }
            bot.sendMessage(user.getChatID(), stringBuilder.toString());
            log.info("Оповещение юзеру с id: " + user.getChatID() + "отправлено.");
        }
    }
}
