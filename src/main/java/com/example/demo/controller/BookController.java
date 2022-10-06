package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> bookDtos = bookService.getAllBooks();
        return ResponseEntity.ok(bookDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDto> getOneBook(@PathVariable Long id){
        BookDto bookDto = bookService.getOneBook(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping()
    public ResponseEntity<Object> createBook(@Valid @RequestBody BookDto bookDto, BindingResult br){
        StringBuilder sb = new StringBuilder();
        if(br.hasErrors()){
            for(FieldError error : br.getFieldErrors()){
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            BookDto newBookDto = bookService.createBook(bookDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newBookDto.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateBook(@Valid @RequestBody BookDto bookDto, @PathVariable Long id, BindingResult br){

        StringBuilder sb = new StringBuilder();
        if(br.hasErrors()){
            for(FieldError error : br.getFieldErrors()){
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            bookService.updateBook(bookDto, id);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/author/{author}")
    public ResponseEntity<BookDto> getByAuthor(@PathVariable String author){
        BookDto bookDto = bookService.getByAuthor(author);
       return ResponseEntity.ok(bookDto);
    }
}
