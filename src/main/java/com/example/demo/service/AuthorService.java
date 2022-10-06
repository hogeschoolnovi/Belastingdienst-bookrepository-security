package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.model.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    public static AuthorDto fromAuthor(Author author){
        AuthorDto dto = new AuthorDto(author.getName(), author.getBooks());
        return dto;
    }

    public static Author toAuthor(AuthorDto dto){
        Author author = new Author(dto.getBooks(), dto.getName());
        return author;
    }
}
