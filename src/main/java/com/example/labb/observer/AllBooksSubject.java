package com.example.labb.observer;

import com.example.labb.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AllBooksSubject implements Subject<Book> {
    private final List<Observer<Book>> observers = new CopyOnWriteArrayList<>();

    @Override
    public void attach(Observer<Book> observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer<Book> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Book book) {
        for (Observer<Book> observer : observers) {
            try {
                observer.update(book);
            } catch (RuntimeException e) {
                detach(observer); // client disconnected
            }
        }
    }

    // changes the state of the subject => notifies observers
    public void add(Book book) {
        notifyObservers(book);
    }
}