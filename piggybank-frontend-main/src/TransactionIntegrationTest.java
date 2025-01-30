package com.testing.piggybank.transaction;

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
    public void shouldReturnTransactions_WhenTransactionsExist() {
        // Simuleer het ophalen van transacties
        ResponseEntity<GetTransactionsResponse> response = transactionController.getTransactions(3, 1);

        // Controleer of de response niet null is en of er 3 transacties zijn
        Assertions.assertNotNull(response.getBody(), "Response mag niet null zijn");
        Assertions.assertEquals(3, response.getBody().getTransactions().size(), "Aantal transacties moet 3 zijn");
    }

    @Test
    public void shouldCreateTransaction_WhenValidDataProvided() {
        // Maak een nieuwe transactie-aanvraag
        CreateTransactionRequest transactionRequest = new CreateTransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(12.00));
        transactionRequest.setCurrency(Currency.EURO);
        transactionRequest.setDescription("Testtransactie");
        transactionRequest.setReceiverAccountId(2);
        transactionRequest.setSenderAccountId(1);

        // Voer de transactie uit
        ResponseEntity<HttpStatus> response = transactionController.createTransaction(transactionRequest);

        // Controleer of de transactie succesvol is verwerkt
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), "Transactie moet succesvol verwerkt worden");
    }
}
