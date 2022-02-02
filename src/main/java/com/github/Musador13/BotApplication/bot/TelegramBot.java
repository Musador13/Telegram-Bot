package com.github.Musador13.BotApplication.bot;

import com.github.Musador13.BotApplication.command.CommandContainer;
import com.github.Musador13.BotApplication.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.Musador13.BotApplication.command.CommandName.NO;

/**
 * Telegram Bot for simple tasks
 */
@Component
public class TelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public TelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

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
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
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