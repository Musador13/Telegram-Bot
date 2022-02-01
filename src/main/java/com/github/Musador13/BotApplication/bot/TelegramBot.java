package com.github.Musador13.BotApplication.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Telegram Bot for simple tasks
 */
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    /**
     * Точка входа, куда будут поступать сообщения от пользователей. Отсюда идет вся логика.
     * Условием проверям поступление непустого сообщения, содержащего текст, при соблюдении условия извлекаем текст
     * (удалив пробелы с двух сторон) и Id чата. Передаем сообщение обратно пользователю.
     *
     * @param update переданное соббщение.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String chatId = update.getMessage().getChatId().toString();

            //Создаем объект для отправки сообщения, передаем в него само сообщение и айди чата - т.е. то, что должен
            // отправить бот и куда.
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(message);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                //todo add logging to the project.
                e.printStackTrace();
            }
        }
    }

    /**
     * Здесь мы добавляем имя нашего бота.
     *
     * @return имя бота, переданное в application.properties
     */
    @Override
    public String getBotUsername() {
        return username;
    }

    /**
     * Здесь мы добавляем токен бота.
     *
     * @return токен, переданный в application.properties
     */
    @Override
    public String getBotToken() {
        return token;
    }

}
