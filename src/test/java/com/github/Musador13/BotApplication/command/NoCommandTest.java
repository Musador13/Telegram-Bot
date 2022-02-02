package com.github.Musador13.BotApplication.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.Musador13.BotApplication.command.CommandName.NO;
import static com.github.Musador13.BotApplication.command.NoCommand.NO_MESSAGE;

@DisplayName("Unit-level testing for NOCommand")
public class NoCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}