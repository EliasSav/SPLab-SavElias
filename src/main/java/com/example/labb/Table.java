package com.example.labb;

public class Table extends Element {
    private String title;

    public Table(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Table with title: " + title);
    }

    @Override
    public void add(Element element) {}

    @Override
    public void remove(Element element) {}

    @Override
    public Element get(int index) { return null; }
}