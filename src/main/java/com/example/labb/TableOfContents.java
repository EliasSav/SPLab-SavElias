package com.example.labb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TableOfContents extends Element {
    @Transient
    private final List<String> entries = new ArrayList<>();

    public void addEntry(String title, int level, int page) {
        entries.add("  ".repeat(level) + title + ", page: " + page);
    }

    @JsonIgnore
    public List<String> getEntries() {
        return entries;
    }

    public void render() {
        System.out.println("Table of Contents:");
        for (String entry : entries) {
            System.out.println(entry);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void add(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Element element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Element get(int index) {
        throw new UnsupportedOperationException();
    }
}