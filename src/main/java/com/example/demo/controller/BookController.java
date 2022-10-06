package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    List<String> books;

    @GetMapping(value = "")
    public ResponseEntity<List<String>> getAllBooks(){
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getOneBook(@PathVariable int id){
        if(books.size()-1 > id){
            throw new RecordNotFoundException("Id does not exist");
        } else {
            return ResponseEntity.ok(books.get(id));
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createBook(@RequestBody String book){
        books.add(book);
        return ResponseEntity.created(null).build();
    }


}
