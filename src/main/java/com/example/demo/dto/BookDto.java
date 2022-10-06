package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookDto {

    @NotNull
    private Long id;

    @Length(min = 8, max = 25)
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate published;

    private CoverDto Cover;

    private AuthorDto Author;


    public BookDto(Long id, String title, LocalDate published, CoverDto CoverDto, AuthorDto AuthorDto) {
        this.id = id;
        this.title = title;
        this.Author = AuthorDto;
        this.published = published;
        this.Cover = CoverDto;
    }

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDto getAuthor() {
        return Author;
    }

    public void setAuthor(AuthorDto AuthorDto) {
        this.Author = AuthorDto;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public CoverDto getCover() {
        return Cover;
    }

    public void setCover(CoverDto CoverDto) {
        this.Cover = CoverDto;
    }


}
