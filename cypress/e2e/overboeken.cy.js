// Cypress tests for PiggyBank Application based on the Testplan
describe('PiggyBank Application Tests', () => {

    beforeEach(() => {
        // Zorg ervoor dat de applicatie draait op localhost
        cy.visit('http://localhost:3000');
    });

    it('Testscenario 1: Inloggen zonder wachtwoord', () => {
        cy.get('.login__account').first().click(); // Gebruik een eenvoudige en werkende login zoals het voorbeeld
    });

    it('Testscenario 2: Saldo bekijken op de hoofdpagina', () => {
        cy.get('.login__account').contains('Cem').click();
                      // Controleer of het saldo correct wordt weergegeven
        cy.get('.accounts__account-balance').should('contain.text', '€ 1000');
    });

    it('Testscenario 3: Geld overboeken naar een andere rekening', () => {
        cy.get('.login__account').contains('Cem').click();
        // Controleer of de zijmenu-knop zichtbaar is en klik erop
        cy.visit('http://localhost:3000/transfer')
        cy.get('.container')
        cy.get('.toaccount').select('Melvin Webster');
        cy.get('.amount-input').type('50');
        cy.get('.description').type('Test betaling');
        cy.get('.overboeken').click();
    });

    it('Testscenario 4: Overboeken met te weinig saldo', () => {
        cy.get('.login__account').contains('Cem').click();
        // Controleer of de zijmenu-knop zichtbaar is en klik erop
        cy.visit('http://localhost:3000/transfer')
        cy.get('.container')
        cy.get('.toaccount').select('Melvin Webster');
        cy.get('.amount-input').type('960');
        cy.get('.description').type('Test betaling mingetal');
        cy.get('.overboeken').click();
    });

    it('Testscenario 5: Naam aanpassen in de instellingen', () => {
        cy.get('.login__account').contains('Cem').click();
        // Controleer of de zijmenu-knop zichtbaar is en klik erop
        cy.visit('http://localhost:3000/settings')
        cy.get('.accountName').type(' Yilmaz');
        cy.get('.Opslaan').click();
        // Controleer of de naam is opgeslagen
    });

    it('Testscenario 6: Uitloggen en terugkeren naar de loginpagina', () => {
        cy.get('.login__account').contains('Cem').click();
        // Controleer of de zijmenu-knop zichtbaar is en klik erop
        cy.get('.app__logout', { timeout: 10000 }).should('be.visible').click();

    });

});
