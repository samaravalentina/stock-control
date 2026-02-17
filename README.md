# 📦 Stock Control

Sistema de controle de estoque desenvolvido como desafio técnico.  
Inclui **backend em Spring Boot** e **frontend em React (Vite)**, com testes de integração em **Cypress**.  
Banco de dados utilizado: **MySQL** (configurado via MySQL Workbench).  
Frontend publicado na **Vercel** para acesso online.

---

## 🚀 Tecnologias utilizadas
- **Backend**: Java, Spring Boot, Maven, JPA/Hibernate, MySQL  
- **Frontend**: React, Vite, Axios  
- **Testes**: Cypress (end-to-end)  
- **Deploy**: Vercel (frontend)  

---

## 📂 Estrutura do projeto
```
stock-control/
│── backend/   -> código Spring Boot
│── frontend/  -> código React (Vite)
└── README.md  -> instruções gerais
```

---

## ⚙️ Como rodar o projeto localmente

### 1. Banco de Dados (MySQL)
Crie um banco chamado `stock_control` no MySQL Workbench:

```sql
CREATE DATABASE stock_control;
Configure usuário e senha no arquivo do backend (application.properties):
spring.datasource.url=jdbc:mysql://localhost:3306/stock_control
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 2. Backend
```
cd backend
mvn spring-boot:run
Servidor disponível em: http://localhost:8080
```
### 3. Frontend
```
cd frontend
npm install
npm run dev
Aplicação disponível em: http://localhost:5173
```
🌐 Deploy na Vercel
O frontend foi publicado na Vercel.
Após rodar npm run build, basta conectar o repositório GitHub à Vercel e o deploy será automático.

URL pública: Stock Control Online

📌 Endpoints principais (Backend)
Produtos
POST /products – criar produto

GET /products – listar produtos

PUT /products/{id} – atualizar produto

DELETE /products/{id} – excluir produto

Matérias-primas
POST /raw-materials – criar matéria-prima

GET /raw-materials – listar matérias-primas

BOM (Bill of Materials)
POST /bill-of-material-items – associar produto ↔ matéria-prima

GET /bill-of-material-items – listar associações

Sugestões de Produção
GET /production-suggestions – calcular produtos sugeridos com base no estoque

✅ Requisitos atendidos
RF001–RF004: Backend implementado (CRUDs + cálculo de sugestões)

RF005–RF008: Frontend implementado (CRUDs + tela de sugestões)

Testes de integração: Cypress validando CRUD de produtos e sugestões

🧪 Testes com Cypress
Para rodar os testes de integração:
cd frontend
npx cypress open
Selecione os arquivos em cypress/e2e/:

product.cy.js → valida CRUD de produtos

suggestions.cy.js → valida tela de sugestões de produção
📖 Observações
Backend configurado para usar MySQL (via MySQL Workbench).

CORS habilitado para permitir comunicação com o frontend.

Projeto organizado em pastas separadas para facilitar manutenção e deploy.

Frontend publicado na Vercel para acesso online.
https://stock-control-roan.vercel.app/
---

