package com.example.labb.controllers;

import com.example.labb.commands.*;
import com.example.labb.models.BookDto;
import com.example.labb.services.BooksService;
import com.example.labb.services.IsbnService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final IsbnService isbnService;
    private final CommandExecutor syncExecutor;
    private final CommandExecutor asyncExecutor;

    public BooksController(BooksService booksService,
                           IsbnService isbnService,
                           @Qualifier("syncExecutor") CommandExecutor syncExecutor,
                           @Qualifier("asyncExecutor") CommandExecutor asyncExecutor) {
        this.booksService = booksService;
        this.isbnService = isbnService;
        this.syncExecutor = syncExecutor;
        this.asyncExecutor = asyncExecutor;
    }

    @GetMapping
    public List<BookDto> getAll() {
        return syncExecutor.execute(new GetAllBooksCommand(booksService)).result();
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable long id) {
        return syncExecutor.execute(new GetBookByIdCommand(booksService, id)).result();
    }

    @PostMapping
    public ResponseEntity<TaskStatus<BookDto>> create(@RequestBody BookDto book) {
        TaskStatus<BookDto> status =
                asyncExecutor.execute(new CreateBookCommand(booksService, isbnService, book));
        return ResponseEntity.accepted()
                .location(URI.create("/tasks/" + status.id()))
                .body(status);
    }

    @PutMapping("/{id}")
    public BookDto replace(@PathVariable long id, @RequestBody BookDto book) {
        return syncExecutor.execute(new UpdateBookCommand(booksService, id, book)).result();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        syncExecutor.execute(new DeleteBookCommand(booksService, id));
        return ResponseEntity.noContent().build();
    }
}