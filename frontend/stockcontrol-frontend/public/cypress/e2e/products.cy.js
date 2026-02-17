describe("Products CRUD", () => {
  it("should open products page", () => {
    cy.visit("/products");
    cy.contains(/produtos/i).should("be.visible");
  });
});
