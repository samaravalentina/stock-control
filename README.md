📦 Stock Control

Sistema de controle de estoque desenvolvido como desafio técnico.

O projeto é composto por:

Backend em Spring Boot

Frontend em React

Testes end-to-end com Cypress

Banco de dados MySQL

Deploy do frontend na Vercel

🚀 Tecnologias Utilizadas
🔹 Backend

Java

Spring Boot

Maven

JPA / Hibernate

MySQL

🔹 Frontend

React

Vite

Axios

🔹 Testes

Cypress (End-to-End)

🔹 Deploy

Vercel (Frontend)

📂 Estrutura do Projeto
stock-control/
│
├── backend/        # API REST - Spring Boot
├── frontend/       # Interface React
└── README.md       # Documentação do projeto
⚙️ Como Rodar o Projeto Localmente
1️⃣ Banco de Dados (MySQL)

Crie o banco no MySQL:
CREATE DATABASE stock_control;
Configure o arquivo application.properties no backend:
spring.datasource.url=jdbc:mysql://localhost:3306/stock_control
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
2️⃣ Rodando o Backend
cd backend
mvn spring-boot:run
Servidor disponível em: http://localhost:8080
3️⃣ Rodando o Frontend
cd frontend
npm install
npm run dev
Aplicação disponível em: http://localhost:5173

🌐 Deploy na Vercel
O frontend foi publicado na Vercel.

Para realizar o deploy:

1.Execute: npm run build
2.Conecte o repositório GitHub à Vercel.

3.O deploy será realizado automaticamente.

🔗 URL pública:
(adicione aqui o link gerado pela Vercel)
📌 Endpoints Principais (Backend)
🧾 Produtos

POST /products → Criar produto

GET /products → Listar produtos

PUT /products/{id} → Atualizar produto

DELETE /products/{id} → Excluir produto

🏗 Matérias-Primas

POST /raw-materials → Criar matéria-prima

GET /raw-materials → Listar matérias-primas

🧩 BOM (Bill of Materials)

POST /bill-of-material-items → Associar produto ↔ matéria-prima

GET /bill-of-material-items → Listar associações

📊 Sugestões de Produção

GET /production-suggestions → Calcular produtos sugeridos com base no estoque

🧪 Testes com Cypress

Para rodar os testes end-to-end: cd frontend
npx cypress open
Selecione os arquivos em cypress/e2e/:

product.cy.js → Valida CRUD de produtos

suggestions.cy.js → Valida tela de sugestões de produção

✅ Requisitos Atendidos

RF001–RF004 → Backend implementado (CRUDs + cálculo de sugestões)

RF005–RF008 → Frontend implementado (CRUDs + tela de sugestões)

Testes E2E com Cypress validando funcionalidades principais

📖 Observações Técnicas

Backend configurado para MySQL.

CORS habilitado para integração com o frontend.

Estrutura organizada para facilitar manutenção e deploy.

Frontend publicado na Vercel para acesso online.
