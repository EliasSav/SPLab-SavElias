package com.example.labb.commands;

public interface CommandExecutor {
    <T> TaskStatus<T> execute(Command<T> command);
}