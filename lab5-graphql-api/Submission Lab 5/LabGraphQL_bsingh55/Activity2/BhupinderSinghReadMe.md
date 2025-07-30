# 📚 Activity 2 – SER 421 Lab 5: GraphQL API Extensions

## 👨‍💻 Developer Info
- **Name:** Bhupinder Singh  
- **Course:** SER 421 – Web & Distributed Software Development  
- **School:** Arizona State University  

---

## 📝 Overview
This project extends the basic Spring Boot GraphQL server by implementing **five new APIs** related to books and authors. Each task contributes to enriching the data model and increasing interactivity between queries and mutations.

---

## ✅ Completed Tasks

### 🔹 Task 1: Get Books by Author ID
Returns a list of books for a given `authorId`.

**GraphQL Query Example:**
```graphql
query {
  booksByAuthorId(authorId: 0) {
    isbn
    title
    authorId
  }
}
```

---

### 🔹 Task 2: Get Authors by Last Name
Returns a list of authors matching a given `lastName`.

**GraphQL Query Example:**
```graphql
query {
  authorsByLastName(lastName: "Fowler") {
    id
    firstName
    lastName
  }
}
```

---

### 🔹 Task 3: Update Author’s First Name by ID
Updates the `firstName` of an author by their ID. Returns the old first name if successful, otherwise returns `null`.

**GraphQL Mutation Example:**
```graphql
mutation {
  updateAuthorFirstName(id: 0, newFirstName: "Bob")
}
```

---

### 🔹 Task 4: Delete a Book by ISBN
Deletes a book based on its `ISBN`. Returns the `ISBN` if the operation is successful, else returns `null`.

**GraphQL Mutation Example:**
```graphql
mutation {
  deleteBookByIsbn(isbn: "123456789")
}
```

---

### 🔹 Task 5: Get Book Titles by Author First Name
Returns a list of all book titles written by authors with a given `firstName`.

**GraphQL Query Example:**
```graphql
query {
  bookTitlesByAuthorFirstName(firstName: "Robert")
}
```

---

## 🧪 How to Run & Test

### ▶️ Start the Server
Open a terminal and run:
```bash
./gradlew bootRun
```

The server will start at:
```
http://localhost:8080/graphql
```

---

### 📬 Testing with Postman (GraphQL Body Type)

1. **Open Postman**
2. **Create a New Request:**
   - Method: `POST`
   - URL: `http://localhost:8080/graphql`
3. **Set Request Body Type:**
   - Go to `Body` tab
   - Select the `GraphQL` option
4. **Enter GraphQL Queries/Mutations** using the examples above

---

## 📁 Project Structure

- `BookRepository.java` – Handles book logic (fetch, save, delete)
- `AuthorRepository.java` – Handles author logic (find, update, save)
- `BookController.java` – Maps GraphQL queries/mutations to BookRepository
- `AuthorController.java` – Maps GraphQL queries/mutations to AuthorRepository
- `schema.graphqls` – Defines GraphQL types, queries, and mutations

---

## 🧾 Notes

- Uses in-memory dummy data; no database setup required
- Data resets on each server restart
- All mutations/queries are schema-compliant and ready for integration