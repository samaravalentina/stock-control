describe("Raw Materials CRUD", () => {
  it("should open raw materials page", () => {
    cy.visit("/raw-materials");
    cy.contains(/mat(é|e)rias-?primas/i).should("be.visible");
  });
});
