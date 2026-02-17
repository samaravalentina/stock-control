import { useState } from "react";
import { http } from "../api/http";

export default function Suggestions() {
  const [data, setData] = useState(null);
  const [err, setErr] = useState("");

  async function load() {
    try {
      setErr("");
      // rota corrigida
      const res = await http.get("/production-suggestions");
      setData(res.data);
    } catch (e) {
      setErr(e?.response?.data?.message || e.message || "Erro ao buscar sugestões");
    }
  }

  return (
    <div style={{ display: "grid", gap: 14 }}>
      <h2 style={{ margin: 0 }}>Sugestões de Produção</h2>

      <button onClick={load} style={{ padding: "10px 12px", borderRadius: 10, width: "fit-content" }}>
        Buscar sugestões
      </button>

      {err && <div style={{ color: "crimson" }}>{String(err)}</div>}

      {data && (
  <div style={{ padding: 12, border: "1px solid #eee", borderRadius: 12 }}>
    <table style={{ width: "100%", borderCollapse: "collapse" }}>
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Preço</th>
          <th>Quantidade</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>
        {data.items.map(item => (
          <tr key={item.productId}>
            <td>{item.code}</td>
            <td>{item.name}</td>
            <td>{item.price}</td>
            <td>{item.quantity}</td>
            <td>{item.total}</td>
          </tr>
        ))}
      </tbody>
    </table>
    <div style={{ marginTop: 12 }}>
      <strong>Valor total:</strong> {data.grandTotal}
    </div>
  </div>
)}
    </div>
  );
}