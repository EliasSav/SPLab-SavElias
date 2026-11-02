package com.example.labb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    protected Element parent;

    public Long getId() { return id; }

    public abstract void accept(Visitor visitor);
    public abstract void add(Element element);
    public abstract void remove(Element element);
    public abstract Element get(int index);

    @JsonIgnore
    public Element getParent() { return parent; }
    public void setParent(Element parent) { this.parent = parent; }
}