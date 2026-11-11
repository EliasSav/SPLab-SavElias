package com.example.labb.observer;

public interface Observer<T> {
    void update(T value);
}