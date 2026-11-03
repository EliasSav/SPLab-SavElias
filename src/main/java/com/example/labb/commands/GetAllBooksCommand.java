package com.example.labb.commands;

import com.example.labb.Book;
import com.example.labb.persistence.CrudRepository;

import java.util.List;

public class GetAllBooksCommand implements Command<List<Book>> {
    private final CrudRepository<Book, Long> repo;

    public GetAllBooksCommand(CrudRepository<Book, Long> repo) {
        this.repo = repo;
    }

    @Override
    public List<Book> execute() {
        return repo.findAll();
    }
}