# AI Usage Disclosure

## Overview

AI assistance was used during the development of this Customer Transaction Service project.

The AI was mainly used as a support tool for understanding the requirements, clarifying Spring Boot concepts, troubleshooting implementation issues, and getting guidance on some of the more complicated parts of the project.

The implementation was reviewed, integrated, and tested by me.

---

## Areas Where AI Assistance Was Used

AI assistance was used for the following areas:

### 1. Understanding the Project Requirements

AI was used to help understand the requirements given in the README, including:

- Creating a transaction
- Getting a transaction using Transaction ID
- Updating the transaction status
- Getting all transactions belonging to a customer
- Understanding the required transaction fields
- Understanding the validation requirements
- Understanding the testing expectations

---

### 2. Spring Boot Project Structure

AI was used for guidance in understanding how the different Spring Boot components work together, including:

- Entity class
- Repository
- Service
- REST Controller
- Spring Boot application
- JUnit/Spring Boot tests

The project uses Spring Boot, Spring Web, Spring Data JPA, H2 database, Java 17, and JUnit/Spring Boot Test.

---

### 3. Transaction Entity

AI assistance was used to understand the purpose of the `Transaction` entity and its fields:

- Transaction ID
- Customer ID
- Amount
- Currency
- Transaction Type
- Transaction Status

The transaction ID is used as the entity identifier.

---

### 4. Repository

AI was used to understand how Spring Data JPA can be used to store and retrieve transactions.

The repository provides operations such as:

- Saving a transaction
- Finding a transaction by Transaction ID
- Checking whether a Transaction ID already exists
- Finding all transactions belonging to a Customer ID

A customer-based repository query was used to retrieve transactions for a specific customer.

---

### 5. Service Layer

AI assistance was used for some of the more complicated business logic in the service layer.

The service performs validation before creating a transaction.

The validation includes checking that:

- Transaction ID is provided
- Customer ID is provided
- Amount is provided
- Currency is provided
- Transaction Type is provided
- Initial Status is provided
- Amount is greater than zero
- Amount contains a valid numeric value
- Transaction ID is not already present

The service also handles:

- Creating transactions
- Retrieving transactions
- Updating transaction status
- Retrieving transactions for a customer
- Returning appropriate HTTP errors for invalid requests

---

### 6. REST API Controller

AI was used for guidance on implementing the REST endpoints using Spring Boot annotations.

The implemented operations are:

#### Create Transaction

```text
POST /api/transactions

Creates a new transaction.

Get Transaction

GET /api/transactions/{transactionId}

Retrieves a transaction using its Transaction ID.

Update Transaction Status

PUT /api/transactions/{transactionId}/status

Updates the status of an existing transaction.

Get Customer Transactions

GET /api/transactions/customer/{customerId}

Retrieves all transactions belonging to a specified customer.


---

7. Validation and Error Handling

AI assistance was used to understand how to handle invalid input and exceptions in Spring Boot.

The implementation returns:

400 Bad Request for invalid transaction data

400 Bad Request when a duplicate Transaction ID is submitted

400 Bad Request when an invalid amount is supplied

400 Bad Request when the amount is zero or negative

400 Bad Request when the status supplied for an update is empty

404 Not Found when a requested transaction does not exist


The business validation added beyond the basic field checks includes checking that the transaction amount is a valid number greater than zero and that the Transaction ID is unique.


---

Testing

AI assistance was also used for guidance while creating and troubleshooting meaningful tests.

The test class contains tests for:

1. Successful Transaction Creation

Verifies that a valid transaction can be created successfully.

Expected result:

200 OK

2. Invalid Transaction

Verifies that a transaction with a negative amount is rejected.

Expected result:

400 Bad Request

3. Duplicate Transaction ID

Verifies that creating a transaction with an already existing Transaction ID is rejected.

Expected result for the second request:

400 Bad Request

4. Non-existing Transaction

Verifies that requesting a transaction that does not exist returns the appropriate response.

Expected result:

404 Not Found


---

Troubleshooting

AI assistance was used to help understand and troubleshoot Maven test failures and HTTP status-code failures during development.

The implementation was modified and tested until the test suite completed successfully.

The final test execution showed:

Tests run: 4
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS


---

Final Statement

AI was used as a development assistance and learning tool, particularly for understanding complicated Spring Boot concepts, REST API implementation, validation, exception handling, JPA repository operations, and troubleshooting test failures.

The generated suggestions were reviewed and integrated into the project, and the resulting implementation was tested locally.

The final project was verified by running the Maven test suite successfully.
