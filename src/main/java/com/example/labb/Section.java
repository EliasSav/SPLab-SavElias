package com.example.labb;

import java.util.ArrayList;
import java.util.List;

public class Section extends Element {
    private final String title;
    protected final List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void render() {
        System.out.println(title);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        acceptChildren(visitor);
    }

    protected void acceptChildren(Visitor visitor) {
        for (Element child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public void add(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (element.getParent() != null) {
            throw new IllegalStateException("Element already added to another section!");
        }
        for (Element p = this; p != null; p = p.getParent()) {
            if (p == element) {
                throw new IllegalArgumentException("Cycle detected");
            }
        }
        element.setParent(this);
        children.add(element);
    }

    @Override
    public void remove(Element element) {
        if (children.remove(element)) {
            element.setParent(null);
        }
    }

    @Override
    public Element get(int index) {
        return children.get(index);
    }
}