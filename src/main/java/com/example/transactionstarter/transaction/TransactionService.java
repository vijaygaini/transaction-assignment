package com.example.transactionstarter.transaction;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    // CREATE TRANSACTION
    public Transaction createTransaction(Transaction transaction) {

        // Validate required fields
        if (transaction.getTransactionId() == null ||
            transaction.getTransactionId().isBlank() ||
            transaction.getCustomerId() == null ||
            transaction.getCustomerId().isBlank() ||
            transaction.getAmount() == null ||
            transaction.getAmount().isBlank() ||
            transaction.getCurrency() == null ||
            transaction.getCurrency().isBlank() ||
            transaction.getTransactionType() == null ||
            transaction.getTransactionType().isBlank() ||
            transaction.getStatus() == null ||
            transaction.getStatus().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid transaction"
            );
        }

        // Amount must be greater than 0
        try {
            BigDecimal amount = new BigDecimal(transaction.getAmount());

            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Amount must be greater than 0"
                );
            }

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid amount"
            );
        }

        // Check duplicate transaction ID
        if (repository.existsById(transaction.getTransactionId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Transaction ID already exists"
            );
        }

        return repository.save(transaction);
    }

    // GET TRANSACTION
    public Transaction getTransaction(String transactionId) {

        return repository.findById(transactionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Transaction not found"
                ));
    }

    // UPDATE STATUS
    public Transaction updateStatus(String transactionId, String status) {

        Transaction transaction = getTransaction(transactionId);

        if (status == null || status.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status cannot be empty"
            );
        }

        transaction.setStatus(status);

        return repository.save(transaction);
    }

    // GET CUSTOMER TRANSACTIONS
    public List<Transaction> getCustomerTransactions(String customerId) {

        return repository.findByCustomerId(customerId);
    }
}