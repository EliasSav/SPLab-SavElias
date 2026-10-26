package com.example.labb.commands;

public interface Command<T> {
    T execute();
}