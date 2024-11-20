package org.example.repettion.bot;

import lombok.extern.slf4j.Slf4j;
import org.example.repettion.model.User;
import org.example.repettion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j

public class Bot extends TelegramLongPollingBot {

    @Value("${telegram.username}")
    private String username;

    private UserService userService;

    public Bot(@Value("${telegram.token}") String botToken, @Autowired UserService userService) {
        super(botToken);
        this.userService = userService;
    }



    @Override
    public void onUpdateReceived(Update update) {

        long id = update.getMessage().getChatId();
        User userWithID = userService.getUserById(id);

        if (userWithID == null) {
            User user = new User();
            user.setChat_id(update.getMessage().getChatId());
            userService.addUser(user);
            log.info("Регистрация user id {} ",user.getChat_id());
            responseMessage(update, "Вы зарегистрированы");
            return;
        }

        log.info("request chat id: " + update.getMessage().getChatId()
                + ", message: " + update.getMessage().getText());

        if (userWithID.getCountMessageCurrentDay() >= 5){
            responseMessage(update,"вы превысили лимит сообщений");
            return;
        }


        responseMessage(update,"ваш запрос принят");
        userWithID.setCountMessageCurrentDay(userWithID.getCountMessageCurrentDay() + 1);
        userService.updateUser(userWithID);




    }


    private void  responseMessage(Update update,String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getBotUsername() {
        return username;
    }


}
