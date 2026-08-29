package com.example.transactionstarter.transaction;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Transaction create(@RequestBody Transaction transaction) {
        return service.createTransaction(transaction);
    }

    // GET
    @GetMapping("/{transactionId}")
    public Transaction get(@PathVariable String transactionId) {
        return service.getTransaction(transactionId);
    }

    // UPDATE STATUS
    @PutMapping("/{transactionId}/status")
    public Transaction updateStatus(
            @PathVariable String transactionId,
            @RequestParam String status) {

        return service.updateStatus(transactionId, status);
    }

    // GET CUSTOMER TRANSACTIONS
    @GetMapping("/customer/{customerId}")
    public List<Transaction> getCustomerTransactions(
            @PathVariable String customerId) {

        return service.getCustomerTransactions(customerId);
    }
}