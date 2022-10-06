package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    String name;

    @OneToMany(mappedBy = "author")
    List<Book> books;


    public Author(List<Book> books, String name) {
        this.books = books;
        this.name = name;
    }

    public Author() {
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
