package com.example.labb.commands;

import com.example.labb.models.BookDto;
import com.example.labb.services.BooksService;

public class UpdateBookCommand implements Command<BookDto> {
    private final BooksService booksService;
    private final long id;
    private final BookDto data;

    public UpdateBookCommand(BooksService booksService, long id, BookDto data) {
        this.booksService = booksService;
        this.id = id;
        this.data = data;
    }

    @Override
    public BookDto execute() {
        return booksService.replace(id, data);
    }
}