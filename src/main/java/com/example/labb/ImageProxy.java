package com.example.labb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class ImageProxy extends Element {
    private String url;

    @Transient
    private Image realImage;

    protected ImageProxy() {
    }

    public ImageProxy(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @JsonIgnore
    private Image loadImage() {
        if (realImage == null) {
            realImage = new Image(url);
        }
        return realImage;
    }

    public void render() {
        loadImage().render();
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