package com.example.labb.commands;

import com.example.labb.models.BookDto;
import com.example.labb.services.BooksService;

import java.util.List;

public class GetAllBooksCommand implements Command<List<BookDto>> {
    private final BooksService booksService;

    public GetAllBooksCommand(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public List<BookDto> execute() {
        return booksService.findAll();
    }
}