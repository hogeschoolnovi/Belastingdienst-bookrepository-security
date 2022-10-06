package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    BookRepository bookRepository;

    public BookService (BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        for(Book book : books){
            bookDtos.add(fromBook(book));
        }
        return bookDtos;
    }

    public BookDto getOneBook(Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(!bookOptional.isPresent()){
            throw new RecordNotFoundException("Book not found");
        } else {
            Book book = bookOptional.get();
            BookDto dto = fromBook(book);
            return dto;
        }
    }

    public BookDto createBook(BookDto bookDto) {
        Book book = toBook(bookDto);
        Book newBook = bookRepository.save(book);
        BookDto dto = fromBook(newBook);
        return dto;

    }

    public BookDto getByAuthor(String author){
        Optional<Book> optionalBook = bookRepository.findByAuthorNameIgnoreCase(author);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            BookDto dto = fromBook(book);
            return dto;
        } else {
            throw new RecordNotFoundException("Book with author " + author + " not found");
        }
    }

    public void updateBook(BookDto bookDto, Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            if(bookDto.getAuthor() != null){
                book.setAuthorName(bookDto.getAuthor());
            }
            if(bookDto.getPublished() != null){
                book.setPublished(bookDto.getPublished());
            }
            if(bookDto.getTitle() != null){
                book.setTitle(bookDto.getTitle());
            }

            bookRepository.save(book);
        } else {
            throw new RecordNotFoundException("Book with id "+id+" not found");
        }
    }

    private BookDto fromBook(Book book){
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthorName());
        dto.setPublished(book.getPublished());
        return dto;
    }

    private Book toBook(BookDto dto){
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthorName(dto.getAuthor());
        book.setPublished(dto.getPublished());
        return book;
    }
}
