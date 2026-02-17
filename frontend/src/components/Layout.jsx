import Nav from "./Nav";

export default function Layout({ children }) {
  return (
    <div style={{ maxWidth: 980, margin: "0 auto", padding: 20 }}>
      <header style={{ display: "flex", flexDirection: "column", gap: 12, marginBottom: 18 }}>
        <h1 style={{ margin: 0 }}>Stock Control</h1>
        <p style={{ margin: 0, opacity: 0.75 }}>
          Frontend React consumindo a API Spring Boot (localhost:8080)
        </p>
        <Nav />
        <hr style={{ width: "100%", opacity: 0.2 }} />
      </header>

      <main>{children}</main>

      <footer style={{ marginTop: 30, opacity: 0.6 }}>
        <hr style={{ width: "100%", opacity: 0.2 }} />
        <small>Rodando em {window.location.origin}</small>
      </footer>
    </div>
  );
}
