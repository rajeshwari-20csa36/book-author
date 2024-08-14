package com.ust.controller;

import com.ust.dto.Author;
import com.ust.dto.BookDTO;
import com.ust.feing_client.AuthorClient;
import com.ust.model.Book;
import com.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorClient authorClient;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        System.out.println(bookRepository.findAll());
        return bookRepository.findAll().stream()
                .map(this::convertToBookDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            BookDTO bookDTO = convertToBookDTO(book);
            return ResponseEntity.ok(bookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private BookDTO convertToBookDTO(Book book) {
        Author author = authorClient.getAuthorById(book.getAuthorId());
        return new BookDTO(book.getId(), book.getTitle(), author);
    }
}

