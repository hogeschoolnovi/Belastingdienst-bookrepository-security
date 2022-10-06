package com.example.demo.dto;

import com.example.demo.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class AuthorDto {
        String name;

        @JsonIgnore
        List<Book> books;

        public AuthorDto(String name, List<Book> books) {
                this.name = name;
                this.books = books;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public List<Book> getBooks() {
                return books;
        }

        public void setBooks(List<Book> books) {
                this.books = books;
        }
}
