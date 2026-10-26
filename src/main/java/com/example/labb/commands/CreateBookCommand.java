package com.example.labb.commands;

import com.example.labb.models.BookDto;
import com.example.labb.services.BooksService;
import com.example.labb.services.IsbnService;

public class CreateBookCommand implements Command<BookDto> {
    private final BooksService booksService;
    private final IsbnService isbnService;
    private final BookDto data;

    public CreateBookCommand(BooksService booksService, IsbnService isbnService, BookDto data) {
        this.booksService = booksService;
        this.isbnService = isbnService;
        this.data = data;
    }

    @Override
    public BookDto execute() {
        String isbn = isbnService.generateIsbn();
        return booksService.create(data.title(), data.author(), isbn);
    }
}