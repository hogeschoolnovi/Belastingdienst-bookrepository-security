package com.example.demo.service;

import com.example.demo.dto.AuthorDto;
import com.example.demo.dto.BookDto;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Cover;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Mock
    Book book;

    @BeforeEach
    public void setup(){
       book = new Book(1L, "Java for dummies", LocalDate.now());
    }

    @Test
    void getOneBook() {
        // arrange
        BookDto expected = new BookDto(1L, "Java for dummies", LocalDate.now(), null, null);

        // act
        Mockito
                .when(bookRepository.findById(book.getId()))
                .thenReturn(Optional.of(book));

        BookDto actual = bookService.getOneBook(book.getId());

        // assert
        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(actual.getId(), expected.getId());
        assertEquals(actual.getPublished(), expected.getPublished());
        assertEquals(actual.getAuthor(), expected.getAuthor());
        assertEquals(actual.getCover(), expected.getCover());
        assertThrows(RecordNotFoundException.class,
                ()->{bookService.getOneBook(book.getId()+1);},
                "Id is niet goed");
    }

    @Test
    void updateBook() {
        //arrange
        ArrayList<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(book);
        AuthorDto author = new AuthorDto("name", listOfBooks);
        BookDto newBook = new BookDto(2L, "Testen for dummies", LocalDate.now(), null, author);
        BookDto expected = new BookDto(1L, "Java for dummies", LocalDate.now(), null, null);
        //act
        Mockito
                .when(bookRepository.findById(book.getId()))
                .thenReturn(Optional.of(book));

        Mockito
                .when(bookRepository.save(book))
                .thenReturn(book);

        bookService.updateBook(newBook, 1L);


        // assert
        Mockito.verify(bookRepository, Mockito.times(1)).save(book);
        assertThrows(RecordNotFoundException.class,
                ()->{bookService.updateBook(newBook, book.getId()+1);},
                "Id is niet goed");

    }
}