package com.example.labb.commands;

import com.example.labb.models.BookDto;
import com.example.labb.services.BooksService;

public class GetBookByIdCommand implements Command<BookDto> {
    private final BooksService booksService;
    private final long id;

    public GetBookByIdCommand(BooksService booksService, long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public BookDto execute() {
        return booksService.findById(id);
    }
}