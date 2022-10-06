package com.example.demo.util;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader {
    BookRepository bookRepository;

    public DataLoader(BookRepository bookRepository){
        this.bookRepository = bookRepository;
        load();
    }

    private void load(){
      Book book = new Book(1001L, "DataLoaderBook", "Mark", LocalDate.now(), true);
      bookRepository.save(book);
    }
}
