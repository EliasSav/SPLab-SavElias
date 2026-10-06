package com.example.labb;

public class ImageProxy extends Element {
    private String url;
    private Image realImage = null;

    public ImageProxy(String url) {
        this.url = url;
    }

    public Image loadImage() {
        if (realImage == null) {
            realImage = new Image(url);
        }
        return realImage;
    }

    @Override
    public void print() {
        loadImage();
        realImage.print();
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