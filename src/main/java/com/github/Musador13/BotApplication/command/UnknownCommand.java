package com.github.Musador13.BotApplication.command;

import com.github.Musador13.BotApplication.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Unknown {@link Command}.
 */
public class UnknownCommand implements Command {
  
    public static final String UNKNOWN_MESSAGE =
            "Не понимаю вашей команды \uD83D\uDE1F, напишите /help чтобы ознакомиться с командами.";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}
