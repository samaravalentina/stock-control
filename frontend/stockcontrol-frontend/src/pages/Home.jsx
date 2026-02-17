export default function Home() {
  return (
    <div style={{ display: "grid", gap: 10 }}>
      <h2 style={{ margin: 0 }}>Tudo certo ✅</h2>
      <p style={{ margin: 0 }}>
        Use o menu acima para testar a API pelo frontend.
      </p>

      <div style={{ padding: 12, border: "1px solid #eee", borderRadius: 12 }}>
        <b>Checklist:</b>
        <ul>
          <li>Backend rodando em <code>http://localhost:8080</code></li>
          <li>CORS liberado para <code>http://localhost:5173</code></li>
          <li>Teste no Insomnia funciona</li>
        </ul>
      </div>
    </div>
  );
}
