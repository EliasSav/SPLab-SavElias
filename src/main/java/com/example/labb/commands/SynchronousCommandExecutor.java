package com.example.labb.commands;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("syncExecutor")
public class SynchronousCommandExecutor implements CommandExecutor {
    @Override
    public <T> TaskStatus<T> execute(Command<T> command) {
        T result = command.execute(); // exceptions propagate (e.g. 404)
        return new TaskStatus<>(UUID.randomUUID().toString(), TaskStatus.State.DONE, result, null);
    }
}