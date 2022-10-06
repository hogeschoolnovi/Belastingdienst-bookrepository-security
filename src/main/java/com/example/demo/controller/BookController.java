package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getOneBook(@PathVariable Long id){
        return ResponseEntity.ok(bookRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("book not found")));
    }

    @PostMapping()
    public ResponseEntity<Object> createBook(@RequestBody Book book){
        Book newBook = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newBook.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/author/{author}")
    public ResponseEntity<Book> getByAuthor(@PathVariable String author){
        Optional<Book> optionalBook = bookRepository.findByAuthorNameIgnoreCase(author);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return ResponseEntity.ok(book);
        } else {
            throw new RecordNotFoundException("Book with author " + author + " not found");
        }
    }
}
