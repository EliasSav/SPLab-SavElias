package com.example.labb;

public abstract class Element {
    protected Element parent;

    public abstract void print();
    public abstract void add(Element element);
    public abstract void remove(Element element);
    public abstract Element get(int index);

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }
}