package com.example.labb.commands;

import com.example.labb.Author;
import com.example.labb.Book;
import com.example.labb.models.BookDto;
import com.example.labb.observer.AllBooksSubject;
import com.example.labb.persistence.CrudRepository;
import com.example.labb.services.IsbnService;

public class CreateBookCommand implements Command<Book> {
    private final CrudRepository<Book, Long> repo;
    private final IsbnService isbnService;
    private final AllBooksSubject allBooksSubject;
    private final BookDto data;

    public CreateBookCommand(CrudRepository<Book, Long> repo, IsbnService isbnService,
                             AllBooksSubject allBooksSubject, BookDto data) {
        this.repo = repo;
        this.isbnService = isbnService;
        this.allBooksSubject = allBooksSubject;
        this.data = data;
    }

    @Override
    public Book execute() {
        Book book = new Book(data.title());
        if (data.author() != null && !data.author().isBlank()) {
            book.addAuthor(new Author(data.author()));
        }
        book.setIsbn(isbnService.generateIsbn());
        book = repo.save(book);
        allBooksSubject.add(book);
        return book;
    }
}