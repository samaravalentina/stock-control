describe('Production Suggestions flow', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5173');
  });

  it('should display production suggestions based on stock', () => {
    cy.contains('Sugestões').click();

    // intercepta a chamada GET
    cy.intercept('GET', '/production-suggestions').as('getSuggestions');

    // dispara a requisição clicando no botão
    cy.contains('Buscar sugestões').click();

    // espera a resposta da API
    cy.wait('@getSuggestions');

    // valida que a tabela aparece
    cy.contains('Sugestões de Produção').should('exist');
    cy.get('table').find('tr').should('have.length.greaterThan', 1);
  });
});