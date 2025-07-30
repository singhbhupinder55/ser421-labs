package com.example.graphqlserver.controller;

import com.example.graphqlserver.dto.input.AddBookInput;
import com.example.graphqlserver.dto.output.AddBookPayload;
import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;
import com.example.graphqlserver.repository.AuthorRepository;
import com.example.graphqlserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public  Book bookByISBN(@Argument("isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @MutationMapping
    public AddBookPayload addBook(@Argument AddBookInput input) {
       Author author = authorRepository.findById(Long.valueOf(input.authorId()))
                .orElseThrow(() -> new IllegalArgumentException("Author with ID " + input.authorId() + " does not exist"));

        Book book = new Book();
        book.setIsbn(input.isbn());
        book.setTitle(input.title());
        book.setAuthor(author); // Correct relational mapping

        Book savedBook = bookRepository.save(book);
        return new AddBookPayload(savedBook);
    }
 
}
