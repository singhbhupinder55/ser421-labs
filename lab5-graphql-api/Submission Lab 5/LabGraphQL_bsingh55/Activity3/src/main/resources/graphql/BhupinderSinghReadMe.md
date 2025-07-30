# SER 421 – Lab 4 – Activity 3: Convert GraphQL API to use a JPA backend

## 🔧 Objective
This activity involved replacing static mock data from the original GraphQL API with a JPA-backed H2 in-memory database using Spring Boot. The schema and GraphQL operations remained similar to Activity 2, but the data source was migrated to real database persistence.

---

## ✅ What We Did

1. **Removed Static Initialization**
   - Deleted hardcoded dummy data blocks from `AuthorRepository.java` and `BookRepository.java`.
   - Removed all static lists and manual population code.

2. **Integrated Spring Data JPA**
   - Annotated `Author` and `Book` classes with `@Entity`, `@Id`, `@ManyToOne`, and `@OneToMany`.
   - Added `AuthorRepository` and `BookRepository` interfaces extending `JpaRepository`.

3. **Configured H2 Database**
   - Set up H2 in `application.properties` with console enabled:
     ```properties
     spring.datasource.url=jdbc:h2:mem:booktown
     spring.datasource.driverClassName=org.h2.Driver
     spring.datasource.username=sa
     spring.datasource.password=
     spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
     spring.h2.console.enabled=true
     spring.jpa.show-sql=true
     ```

4. **Updated GraphQL Resolvers**
   - Used repositories to perform actual database operations for queries and mutations.
   - Ensured mutation input types conform to GraphQL schema.

---

## 🧪 How to Test

### ▶️ Run the App

Make sure the Spring Boot application is running (e.g., via `./gradlew bootRun` or your IDE).

---

### 🧪 Test with GraphQL Playground or Postman

#### 🟩 1. Add an Author
```graphql
mutation {
  addAuthor(input: {
    firstName: "John",
    lastName: "Doe"
  }) {
    author {
      id
      firstName
      lastName
    }
  }
}
```

#### 📘 2. Add a Book (use the returned `id` from the previous step)
```graphql
mutation {
  addBook(input: {
    isbn: "9781234567890",
    title: "GraphQL for Beginners",
    authorId: "1"
  }) {
    book {
      isbn
      title
      author {
        id
        firstName
        lastName
      }
    }
  }
}
```

#### 🔍 3. Query All Authors
```graphql
query {
  authors {
    id
    firstName
    lastName
  }
}
```

#### 📚 4. Query All Books
```graphql
query {
  books {
    isbn
    title
    author {
      firstName
      lastName
    }
  }
}
```

---

## 🧪 Test with H2 Console

1. Navigate to [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
2. Enter JDBC URL:
   ```
   jdbc:h2:mem:testdb
   ```
3. Click **Connect**

### Example Queries:
- View authors:
  ```sql
  SELECT * FROM AUTHOR;
  ```
- View books:
  ```sql
  SELECT * FROM BOOK;
  ```

---

## 📝 Conclusion

- Fully replaced static mocks with real JPA entities.
- Successfully integrated Spring Data JPA and H2.
- Verified persistence via H2 Console.
- Confirmed GraphQL queries and mutations work seamlessly with the database.
