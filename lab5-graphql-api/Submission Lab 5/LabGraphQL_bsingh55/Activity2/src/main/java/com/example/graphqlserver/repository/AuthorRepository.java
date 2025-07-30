package com.example.graphqlserver.repository;

import com.example.graphqlserver.model.Author;
import com.example.graphqlserver.model.Book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class AuthorRepository {


    private static List<Author> dummyAuthors = new ArrayList<>();

    static {
dummyAuthors.addAll(Arrays.asList(
                new Author(0, "Robert", "Frost", new ArrayList<>()),
                new Author(1, "Martin", "Fowler", new ArrayList<>()),
                new Author(2, "Kevin", "Gary", new ArrayList<>())
        ));
    }

    public List<Author> getAuthors() {
        return dummyAuthors;
    }

    public Author getAuthorById(int id) {
        for (Author author : dummyAuthors) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    /**
     * Task 2: Returns a list of authors by their last name.
     * Supports the GraphQL query 'authorsByLastName(lastName: String!): [Author]'.
     */
    public List<Author> getAuthorsByLastName(String lastName) {
        System.out.println("Searching for authors with last name: " + lastName);
        List<Author> authorList = new ArrayList<>();
        for (Author author : dummyAuthors) {
            System.out.println("Checking author: " + author.getLastName());
            if (author.getLastName().equalsIgnoreCase(lastName)) {
                authorList.add(author);
            }       
        }
        return authorList;
    }


    public Author save(String firstName, String lastName) {
        List<Book> book = new ArrayList<>();
        int nextId = dummyAuthors.isEmpty() ? 0 : dummyAuthors.get(dummyAuthors.size() - 1).getId() + 1;
        Author newAuthor = new Author(nextId, firstName, lastName, book);
        dummyAuthors.add(newAuthor);
        return newAuthor;
    }

    /**
    * Task 3: Updates the first name of an author based on their unique ID.
    * Returns the old first name if the author is found, otherwise returns null.
    */ 
   public String updateAuthorFirstName(int id, String newFirstName) {
    for (Author author : dummyAuthors) {
        if (author.getId() == id) {
            String oldFirstName = author.getFirstName();
            author.setFirstName(newFirstName);
            return oldFirstName;
        }
    }
        return null;
    }

    /**
     * Task 5: Fetch all book titles by author with a given first name.
     * Supports the GraphQL query 'bookTitlesByAuthorFirstName(firstName: String!): [String]'.
     */
    public List<Author> getAuthorsByFirstName(String firstName) {
    List<Author> matches = new ArrayList<>();
    for (Author author : dummyAuthors) {
        if (author.getFirstName().equalsIgnoreCase(firstName)) {
            matches.add(author);
        }
    }
    return matches;
}


}
