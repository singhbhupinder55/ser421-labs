package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class BookRepository {

    private static List<Book> dummyBooks = new ArrayList<>();

    static {
        dummyBooks.addAll(Arrays.asList(
                new Book("123456789", "The Road Not Taken", 0),
                new Book("987654321", "To Kill a Mockingbird", 1),
                new Book("456789123", "The Great Gatsby", 2)
        ));
    }

    public List<Book> getBooks() {
        return dummyBooks;
    }

    public Book getBookByISBN(String isbn) {
        for (Book book : dummyBooks) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Book save(String isbn, String title, int authorId) {
        Book newBook = new Book(isbn, title, authorId);
        dummyBooks.add(newBook);
        return newBook;
    }

     /**
    * Task 1: Returns a list of books for the given author ID.
    * Supports the GraphQL query 'booksByAuthorId(authorId: Int!): [Book]'.
    */
    public List<Book> getBooksByAuthorId(int id) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : dummyBooks) {
            if (book.getAuthorId() == id) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    /**
     * Task 4: Deletes a book by its ISBN.
     * Supports the GraphQL mutation 'deleteBookByIsbn(isbn: String!): String'.
     */
    public String deleteBookByIsbn(String isbn) {
        for (Book book : dummyBooks) {
            if (book.getIsbn().equals(isbn)) {
                dummyBooks.remove(book);
                return isbn;
            }
        }
        return null;
    }

}
