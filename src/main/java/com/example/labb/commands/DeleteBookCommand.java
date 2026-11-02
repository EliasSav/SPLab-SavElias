package com.example.labb.commands;

import com.example.labb.persistence.BooksRepository;
import com.example.labb.services.BookNotFoundException;

public class DeleteBookCommand implements Command<Void> {
    private final BooksRepository repo;
    private final long id;

    public DeleteBookCommand(BooksRepository repo, long id) {
        this.repo = repo;
        this.id = id;
    }

    @Override
    public Void execute() {
        if (!repo.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        repo.deleteById(id);
        return null;
    }
}