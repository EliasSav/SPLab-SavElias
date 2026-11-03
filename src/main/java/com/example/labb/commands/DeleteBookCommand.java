package com.example.labb.commands;

import com.example.labb.Book;
import com.example.labb.persistence.CrudRepository;
import com.example.labb.services.BookNotFoundException;

public class DeleteBookCommand implements Command<Void> {
    private final CrudRepository<Book, Long> repo;
    private final long id;

    public DeleteBookCommand(CrudRepository<Book, Long> repo, long id) {
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