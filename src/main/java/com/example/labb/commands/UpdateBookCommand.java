package com.example.labb.commands;

import com.example.labb.Book;
import com.example.labb.models.BookDto;
import com.example.labb.persistence.BooksRepository;
import com.example.labb.services.BookNotFoundException;

public class UpdateBookCommand implements Command<Book> {
    private final BooksRepository repo;
    private final long id;
    private final BookDto data;

    public UpdateBookCommand(BooksRepository repo, long id, BookDto data) {
        this.repo = repo;
        this.id = id;
        this.data = data;
    }

    @Override
    public Book execute() {
        Book book = repo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(data.title());
        book.setIsbn(data.isbn());
        return repo.save(book);
    }
}