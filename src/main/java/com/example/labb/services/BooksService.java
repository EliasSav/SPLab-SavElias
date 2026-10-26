package com.example.labb.services;

import com.example.labb.models.BookDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BooksService {
    private final Map<Long, BookDto> books = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public List<BookDto> findAll() {
        return new ArrayList<>(books.values());
    }

    public BookDto findById(long id) {
        BookDto book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public BookDto create(String title, String author, String isbn) {
        long id = sequence.incrementAndGet();
        BookDto book = new BookDto(id, title, author, isbn);
        books.put(id, book);
        return book;
    }

    public BookDto replace(long id, BookDto newData) {
        findById(id); // throws 404 if missing
        BookDto book = new BookDto(id, newData.title(), newData.author(), newData.isbn());
        books.put(id, book);
        return book;
    }

    public void delete(long id) {
        if (books.remove(id) == null) {
            throw new BookNotFoundException(id);
        }
    }
}