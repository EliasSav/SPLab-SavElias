package com.example.labb.commands;

import com.example.labb.Book;
import com.example.labb.persistence.BooksRepository;
import com.example.labb.services.BookNotFoundException;

public class GetBookByIdCommand implements Command<Book> {
    private final BooksRepository repo;
    private final long id;

    public GetBookByIdCommand(BooksRepository repo, long id) {
        this.repo = repo;
        this.id = id;
    }

    @Override
    public Book execute() {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}