package com.example.transactionstarter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionStarterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createTransactionSuccessfully() throws Exception {

        String json = """
                {
                    "transactionId": "T100",
                    "customerId": "C100",
                    "amount": "500",
                    "currency": "INR",
                    "transactionType": "CREDIT",
                    "status": "SUCCESS"
                }
                """;

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void rejectInvalidTransaction() throws Exception {

        String json = """
                {
                    "transactionId": "T101",
                    "customerId": "C101",
                    "amount": "-500",
                    "currency": "INR",
                    "transactionType": "CREDIT",
                    "status": "SUCCESS"
                }
                """;

        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void rejectDuplicateTransactionId() throws Exception {

        String json = """
                {
                    "transactionId": "T102",
                    "customerId": "C102",
                    "amount": "500",
                    "currency": "INR",
                    "transactionType": "CREDIT",
                    "status": "SUCCESS"
                }
                """;

        // First creation should succeed
        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        // Same Transaction ID should be rejected
        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getNonExistingTransaction() throws Exception {

        mockMvc.perform(get("/api/transactions/DOES_NOT_EXIST"))
                .andExpect(status().isNotFound());
    }
}