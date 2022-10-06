package com.example.demo.dto;

import com.example.demo.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CoverDto {

    Long id;

    @JsonIgnore
    Book book;

    String color;
    String material;

    public CoverDto(Long id, Book book, String color, String material) {
        this.id = id;
        this.book = book;
        this.color = color;
        this.material = material;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
