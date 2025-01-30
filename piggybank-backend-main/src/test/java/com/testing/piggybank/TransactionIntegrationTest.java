package com.testing.piggybank;

import com.testing.piggybank.model.Currency;
import com.testing.piggybank.transaction.CreateTransactionRequest;
import com.testing.piggybank.transaction.GetTransactionsResponse;
import com.testing.piggybank.transaction.TransactionController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;

@SpringBootTest
public class TransactionIntegrationTest {

    @Autowired
    private TransactionController transactionController;

    @Test
    public void shouldCreateNewTransactionSuccessfully() {
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setAmount(BigDecimal.valueOf(20.50));
        request.setCurrency(Currency.EURO);
        request.setDescription("Test transactie");
        request.setReceiverAccountId(3);
        request.setSenderAccountId(1);

        ResponseEntity<HttpStatus> response = transactionController.createTransaction(request);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldNotFailCreatingTransactionWithNegativeAmount() {
        CreateTransactionRequest request = new CreateTransactionRequest();
        request.setAmount(BigDecimal.valueOf(-5.00));
        request.setCurrency(Currency.EURO);
        request.setDescription("Negatieve transactie test");
        request.setReceiverAccountId(2);
        request.setSenderAccountId(1);

        ResponseEntity<HttpStatus> response = transactionController.createTransaction(request);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldRetrieveTransactionsForUser() {
        ResponseEntity<GetTransactionsResponse> response = transactionController.getTransactions(5, 1);
        Assertions.assertNotNull(response.getBody(), "De response mag niet null zijn.");
        Assertions.assertFalse(response.getBody().getTransactions().isEmpty(), "De lijst met transacties mag niet leeg zijn.");
    }
}
