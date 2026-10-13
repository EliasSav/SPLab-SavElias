package com.example.labb;

import java.util.concurrent.TimeUnit;

public class Image extends Element {
    private String url;

    public Image(String url) {
        this.url = url;
        try {
            TimeUnit.SECONDS.sleep(5); // Simulează încărcarea unei imagini mari
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        System.out.println("Image with name:" + url);
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