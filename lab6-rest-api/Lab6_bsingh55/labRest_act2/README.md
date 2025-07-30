# SER 421 Lab 6 - Activity 2: Survey System REST API  
**Author:** Bhupinder Singh (bsingh55)  
**Course:** SER 421 - Web-Based Applications  
**Lab:** Lab 6 - Activity 2  

---

## 📌 Project Overview

This project implements a fully functional **RESTful web service** using **Spring Boot** to manage an educational **Survey System**. The system supports four primary entities:

1. **Survey Items** – individual questions with multiple possible answers and one correct answer.
2. **Surveys** – a collection of survey items grouped under a survey name.
3. **Survey Instances** – a user-specific attempt to take a survey.
4. **Survey Item Instances** – individual answers submitted by users to the survey items during an instance.

All operations are accessible via a REST API. The application uses **JPA** for object-relational mapping and an **H2 in-memory database** for temporary storage. Sample data is loaded on application startup for demonstration and testing purposes.

---

## 🧾 Functional Summary

### ✅ Features Implemented:

- **Survey Item API**
  - Create a new survey item
  - Retrieve all survey items

- **Survey API**
  - Create a new survey using one or more existing survey items
  - Retrieve all surveys or a specific survey by ID
  - Add survey items to an existing survey
  - Soft-delete surveys

- **Survey Instance API**
  - Create a new survey instance (user attempt)
  - Retrieve all instances or by specific state
  - Retrieve specific instance by ID

- **Survey Item Instance API**
  - Submit answer for a survey item instance

---


## 🧪 Testing Summary

A total of **20 API test cases** were created and executed using **Postman**.

### ✅ 10 Passing Test Cases:

1. Create Survey Item  
2. Create Survey  
3. Add Survey Item to Survey  
4. Get All Surveys  
5. Get Specific Survey  
6. Create Survey Instance  
7. Accept Answer for Survey Item Instance  
8. Get Survey Instances by State  
9. Get Specific Survey Instance  
10. Delete Survey  

### ❌ 10 Failing Test Cases (Negative Testing):

11. Delete Survey (Invalid ID)  
12. Create Survey Item (Bad Format)  
13. Create Survey (Missing Name)  
14. Add Survey Item to Invalid Survey  
15. Get Specific Survey (Invalid ID)  
16. Bad HTTP Method on Get All Surveys  
17. Create Survey Instance from Deleted Survey  
18. Accept Answer with Invalid Survey Item Instance  
19. Get Survey Instance (Invalid ID)  
20. Delete Survey (Non-existent ID)

All requests are sent to:  
`http://localhost:8080/api/`

The full collection is included in the file:  
**`Lab6SurveyAPI_bsingh55.postman_collection.json`**

---

## ▶️ How to Run This Project

### Prerequisites:

- Java 17 or later installed
- Gradle installed or use the Gradle wrapper (`./gradlew`)

### Run the Server:

```bash
./gradlew bootRun

- This will start the Spring Boot application on:
-  http://localhost:8080


## How to Run the Tests
- Open Postman.
- Import the file Lab6SurveyAPI_bsingh55.postman_collection.json.
- Select the collection named Lab6 Survey API Tests.
- Run each request individually or run the full collection as a test suite.
- Make sure your server is running before sending requests.

##  Additional Notes
- The application uses H2 in-memory database, so all data is reset on each restart.
- The project uses Spring Data JPA for persistence.
- Controllers return proper HTTP status codes for success and errors.
- Bad input handling and validations are implemented throughout the API.
- The soft-delete mechanism ensures deleted surveys do not participate in new instances.
