package com.testing.piggybank.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;

@SpringBootTest  // Laadt de volledige Spring Boot context
public class AccountsIntegrationTest {

    @Autowired
    private AccountController accountController;  // Injecteert de controller zodat we deze direct kunnen testen

    @Test
    public void shouldReturnAccountsForUser() {
        // Arrange (Voorbereiding)
        long userId = 5;  // We testen met gebruiker ID 1

        // Act (Uitvoering)
        ResponseEntity<GetAccountsResponse> response = accountController.getAccounts(userId);

        // Assert (Controle)
        Assertions.assertNotNull(response.getBody(), "De response mag niet null zijn.");  // Checkt of er een response is
        Assertions.assertFalse(response.getBody().getAccounts().isEmpty(), "De lijst met accounts mag niet leeg zijn.");  // Checkt of de gebruiker accounts heeft
    }
}
