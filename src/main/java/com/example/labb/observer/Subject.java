package com.example.labb.observer;

public interface Subject<T> {
    void attach(Observer<T> observer);

    void detach(Observer<T> observer);

    void notifyObservers(T value);
}