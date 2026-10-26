package com.example.labb.commands;

public record TaskStatus<T>(String id, State state, T result, String error) {
    public enum State { PENDING, DONE, FAILED }
}