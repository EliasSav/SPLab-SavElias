package com.example.labb;

import java.util.ArrayList;
import java.util.List;

public class Book extends Section {
    private final List<Author> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addContent(Element element) {
        add(element);
    }

    @Override
    public void render() {
        System.out.println("Book: " + getTitle());
        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        acceptChildren(visitor);
    }
}