describe('Product CRUD flow', () => {
  beforeEach(() => {
    // abre a aplicação frontend
    cy.visit('http://localhost:5173');
  });

  it('should create a new product', () => {
    cy.contains('Produtos').click();

    cy.get('input[name="code"]').type('P-001');
    cy.get('input[name="name"]').type('Mesa');
    cy.get('input[name="price"]').type('50'); // garantir que não fica vazio

    cy.intercept('POST', '/products').as('createProduct');
    cy.contains('Salvar').click();
    cy.wait('@createProduct');

    cy.contains('Mesa').should('exist');
  });
});