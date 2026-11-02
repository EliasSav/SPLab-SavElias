package com.example.labb.commands;

import com.example.labb.Book;
import com.example.labb.persistence.BooksRepository;

import java.util.List;

public class GetAllBooksCommand implements Command<List<Book>> {
    private final BooksRepository repo;

    public GetAllBooksCommand(BooksRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Book> execute() {
        return repo.findAll();
    }
}