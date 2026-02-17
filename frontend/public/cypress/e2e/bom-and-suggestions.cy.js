describe("BOM + Production Suggestions flow", () => {
  it("should load BOM and request suggestions", () => {
    // BOM
    cy.visit("/bom");
    cy.contains(/BOM/i).should("be.visible");

    // tenta carregar BOM do produto 1
    cy.get("input").first().clear().type("1");
    cy.contains(/carregar bom/i).click();

    // se existir lista, deve aparecer algum texto de retorno
    cy.contains(/nenhum item no bom|raw material|required|id/i).should("be.visible");

    // Sugestões
    cy.visit("/suggestions");
    cy.contains(/sugest(õ|o)es/i).should("be.visible");
    cy.contains(/buscar sugest(õ|o)es/i).click();

    // deve aparecer um JSON ou lista com productId/total/grandTotal
    cy.contains(/productId|grandTotal|total/i).should("be.visible");
  });
});
