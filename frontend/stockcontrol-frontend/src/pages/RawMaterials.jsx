import { useEffect, useState } from "react";
import { http } from "../api/http";

export default function RawMaterials() {
  const [items, setItems] = useState([]);
  const [err, setErr] = useState("");
  const [loading, setLoading] = useState(true);

  // form
  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [stockQuantity, setStockQuantity] = useState("");

  async function load() {
    try {
      setErr("");
      setLoading(true);
      const { data } = await http.get("/raw-materials");
      setItems(data);
    } catch (e) {
      setErr(e?.response?.data?.message || e.message || "Erro ao carregar");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, []);

  async function create(e) {
    e.preventDefault();
    try {
      setErr("");
      await http.post("/raw-materials", {
        code,
        name,
        stockQuantity: Number(stockQuantity),
      });
      setCode(""); setName(""); setStockQuantity("");
      await load();
    } catch (e2) {
      setErr(e2?.response?.data?.message || e2.message || "Erro ao criar");
    }
  }

  return (
    <div style={{ display: "grid", gap: 14 }}>
      <h2 style={{ margin: 0 }}>Matérias-primas</h2>

      <form onSubmit={create} style={{ display: "grid", gap: 8, padding: 12, border: "1px solid #eee", borderRadius: 12 }}>
        <b>Criar matéria-prima</b>

        <div style={{ display: "grid", gap: 6 }}>
          <label>Código</label>
          <input value={code} onChange={(e) => setCode(e.target.value)} placeholder="RM001" required />
        </div>

        <div style={{ display: "grid", gap: 6 }}>
          <label>Nome</label>
          <input value={name} onChange={(e) => setName(e.target.value)} placeholder="Madeira" required />
        </div>

        <div style={{ display: "grid", gap: 6 }}>
          <label>Estoque</label>
          <input value={stockQuantity} onChange={(e) => setStockQuantity(e.target.value)} placeholder="100" type="number" step="1" required />
        </div>

        <button style={{ padding: "10px 12px", borderRadius: 10, border: "1px solid #111", background: "#111", color: "#fff" }}>
          Salvar
        </button>

        {err && <div style={{ color: "crimson" }}>{String(err)}</div>}
      </form>

      <div style={{ display: "grid", gap: 8 }}>
        <button onClick={load} style={{ padding: "8px 10px", borderRadius: 10, width: "fit-content" }}>
          Recarregar
        </button>

        <div style={{ border: "1px solid #eee", borderRadius: 12, overflow: "hidden" }}>
          <table style={{ width: "100%", borderCollapse: "collapse" }}>
            <thead style={{ background: "#fafafa" }}>
              <tr>
                <th style={{ textAlign: "left", padding: 10 }}>ID</th>
                <th style={{ textAlign: "left", padding: 10 }}>Código</th>
                <th style={{ textAlign: "left", padding: 10 }}>Nome</th>
                <th style={{ textAlign: "left", padding: 10 }}>Estoque</th>
              </tr>
            </thead>
            <tbody>
              {items?.map((rm) => (
                <tr key={rm.id} style={{ borderTop: "1px solid #eee" }}>
                  <td style={{ padding: 10 }}>{rm.id}</td>
                  <td style={{ padding: 10 }}>{rm.code}</td>
                  <td style={{ padding: 10 }}>{rm.name}</td>
                  <td style={{ padding: 10 }}>{rm.stockQuantity}</td>
                </tr>
              ))}
              {!loading && (!items || items.length === 0) && (
                <tr>
                  <td colSpan="4" style={{ padding: 12, opacity: 0.7 }}>Nenhuma matéria-prima cadastrada.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
