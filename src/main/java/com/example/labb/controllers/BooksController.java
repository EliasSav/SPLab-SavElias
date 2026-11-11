package com.example.labb.controllers;

import com.example.labb.Book;
import com.example.labb.commands.*;
import com.example.labb.models.BookDto;
import com.example.labb.observer.AllBooksSubject;
import com.example.labb.persistence.CrudRepository;
import com.example.labb.services.IsbnService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final CrudRepository<Book, Long> repo;
    private final IsbnService isbnService;
    private final AllBooksSubject allBooksSubject;
    private final CommandExecutor syncExecutor;
    private final CommandExecutor asyncExecutor;

    public BooksController(CrudRepository<Book, Long> repo,
                           IsbnService isbnService,
                           AllBooksSubject allBooksSubject,
                           @Qualifier("syncExecutor") CommandExecutor syncExecutor,
                           @Qualifier("asyncExecutor") CommandExecutor asyncExecutor) {
        this.repo = repo;
        this.isbnService = isbnService;
        this.allBooksSubject = allBooksSubject;
        this.syncExecutor = syncExecutor;
        this.asyncExecutor = asyncExecutor;
    }

    @GetMapping
    public List<Book> getAll() {
        return syncExecutor.execute(new GetAllBooksCommand(repo)).result();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable long id) {
        return syncExecutor.execute(new GetBookByIdCommand(repo, id)).result();
    }

    @PostMapping
    public ResponseEntity<TaskStatus<Book>> create(@RequestBody BookDto book) {
        TaskStatus<Book> status =
                asyncExecutor.execute(new CreateBookCommand(repo, isbnService, allBooksSubject, book));
        return ResponseEntity.accepted()
                .location(URI.create("/tasks/" + status.id()))
                .body(status);
    }

    @PutMapping("/{id}")
    public Book replace(@PathVariable long id, @RequestBody BookDto book) {
        return syncExecutor.execute(new UpdateBookCommand(repo, id, book)).result();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        syncExecutor.execute(new DeleteBookCommand(repo, id));
        return ResponseEntity.noContent().build();
    }
}