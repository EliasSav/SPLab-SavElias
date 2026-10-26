package com.example.labb.commands;

import com.example.labb.services.BooksService;

public class DeleteBookCommand implements Command<Void> {
    private final BooksService booksService;
    private final long id;

    public DeleteBookCommand(BooksService booksService, long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Void execute() {
        booksService.delete(id);
        return null;
    }
}