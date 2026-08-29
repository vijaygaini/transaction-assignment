# Customer Transaction Service

## 1. Understanding of the Problem

This project implements a small transaction-processing REST service using Java and Spring Boot.

The service manages customer transactions. Each transaction contains:

- Transaction ID
- Customer ID
- Amount
- Currency
- Transaction Type
- Transaction Status

The application implements the four operations required by the exercise:

1. Create a transaction
2. Get a transaction by Transaction ID
3. Update the status of an existing transaction
4. Get all transactions for a Customer ID

The application uses Spring Boot, Spring Data JPA and an H2 embedded database.

---

## 2. Assumptions

The following assumptions were made while implementing the service:

- Transaction ID uniquely identifies a transaction.
- A Transaction ID cannot be reused for another transaction.
- Transaction ID and Customer ID must not be empty.
- Amount must be provided, numeric, and greater than zero.
- Currency must be provided.
- Transaction Type must be provided.
- Initial Transaction Status must be provided.
- An attempt to create a transaction with an existing Transaction ID is rejected.
- A request for a transaction that does not exist returns HTTP 404.
- Invalid transaction input returns HTTP 400.
- An empty status is not allowed when updating a transaction.
- H2 is used as the embedded database provided by the starter project.

---

## 3. Validation Rules

The service validates the following fields when creating a transaction:

### Transaction ID

- Must be provided.
- Must not be blank.
- Must be unique.

### Customer ID

- Must be provided.
- Must not be blank.

### Amount

- Must be provided.
- Must be numeric.
- Must be greater than zero.

### Currency

- Must be provided.
- Must not be blank.

### Transaction Type

- Must be provided.
- Must not be blank.

### Initial Status

- Must be provided.
- Must not be blank.

### Duplicate Transaction

If a Transaction ID already exists in the database, the new transaction is rejected with HTTP 400.

### Update Status

When updating a transaction, the transaction must exist and the new status must not be blank.

These validation rules were chosen because the exercise requires the candidate to define what makes a transaction valid.

---

## 4. API Endpoints

### 4.1 Create Transaction

**Method:**

`POST /api/transactions`

Creates and stores a new transaction.

Example request:

```json
{
  "transactionId": "T100",
  "customerId": "C100",
  "amount": "500",
  "currency": "INR",
  "transactionType": "CREDIT",
  "status": "SUCCESS"
}

Successful creation returns HTTP 200.

Invalid transactions and duplicate Transaction IDs return HTTP 400.


---

4.2 Get Transaction

Method:

GET /api/transactions/{transactionId}

Retrieves a transaction using its Transaction ID.

Example:

GET /api/transactions/T100

If the transaction does not exist, the service returns HTTP 404.


---

4.3 Update Transaction Status

Method:

PUT /api/transactions/{transactionId}/status?status=SUCCESS

Updates the status of an existing transaction.

Example:

PUT /api/transactions/T100/status?status=SUCCESS

The transaction must exist and the new status must not be empty.

( Status Transition Rules)

The transaction must exist before its status can be updated.

The new status must not be null, empty, or blank.

This implementation does not restrict transactions to a fixed list of status transitions. Any non-empty status value is accepted because the README does not specify a fixed status workflow.


---

4.4 Get Customer Transactions

Method:

GET /api/transactions/customer/{customerId}

Retrieves all transactions belonging to the specified Customer ID.

Example:

GET /api/transactions/customer/C100


---

5. Application Structure

The application is organized into separate layers:

Entity

Transaction

Represents a transaction stored in the H2 database.

Repository

TransactionRepository

Uses Spring Data JPA to access transaction data.

Service

TransactionService

Contains the main business logic, validation, duplicate checking, transaction retrieval, status update, and customer transaction retrieval.

Controller

TransactionController

Provides the REST API endpoints and connects HTTP requests to the service layer.


---

6. Testing Approach

Automated tests were created using JUnit and Spring Boot MockMvc.

The tests cover the four required scenarios:

1. A transaction is created successfully.


2. An invalid transaction is rejected.


3. A duplicate Transaction ID is rejected.


4. A request for a transaction that does not exist is handled correctly.

The tests verify HTTP responses rather than only checking whether the application starts.
The test command used on Windows is:
.\mvnw.cmd clean test

Latest successful test result:

Tests run: 4
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS


---

7. Known Limitations

The application currently uses the H2 embedded database, which is suitable for this exercise but is not intended as permanent production storage.
Authentication and authorization are not implemented.
The API does not currently use a detailed structured error response model.
Only the required minimum automated tests have currently been implemented.
Transaction status transition rules are kept simple.



---

8. Improvements With More Time
With more time, I would:
Add more automated tests for update status and customer transaction retrieval.
Add Bean Validation annotations such as @NotBlank and appropriate amount validation.
Introduce stronger transaction status transition rules.
Add a structured error response format.
Add API documentation using OpenAPI/Swagger.
Add authentication and authorization.
Use a production database instead of H2.
Improve logging and monitoring.
Add integration tests covering more end-to-end scenarios.



---

9. Technology Used
Java 17
Spring Boot
Spring Web
Spring Data JPA
H2 Database
Maven
JUnit
Spring Boot MockMvc


