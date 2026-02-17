import { useEffect, useState } from "react";
import { http } from "../api/http";

export default function Products() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState("");

  // form
  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");

  async function load() {
    try {
      setErr("");
      setLoading(true);
      const { data } = await http.get("/products");
      setItems(data);
    } catch (e) {
      setErr(e?.response?.data?.message || e.message || "Erro ao carregar");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
  }, []);

  async function create(e) {
    e.preventDefault();
    try {
      setErr("");
      await http.post("/products", {
        code,
        name,
        price: Number(price),
      });
      setCode(""); setName(""); setPrice("");
      await load();
    } catch (e2) {
      setErr(e2?.response?.data?.message || e2.message || "Erro ao criar");
    }
  }

  return (
    <div style={{ display: "grid", gap: 14 }}>
      <h2 style={{ margin: 0 }}>Produtos</h2>

     <form onSubmit={create} style={{ display: "grid", gap: 8, padding: 12, border: "1px solid #eee", borderRadius: 12 }}>
  <b>Criar produto</b>

  <div style={{ display: "grid", gap: 6 }}>
    <label>Código</label>
    <input
      value={code}
      onChange={(e) => setCode(e.target.value)}
      placeholder="P001"
      required
      data-cy="product-code"
    />
  </div>

  <div style={{ display: "grid", gap: 6 }}>
    <label>Nome</label>
    <input
      value={name}
      onChange={(e) => setName(e.target.value)}
      placeholder="Mesa"
      required
      data-cy="product-name"
    />
  </div>

  <div style={{ display: "grid", gap: 6 }}>
    <label>Preço</label>
    <input
      type="number"
      step="0.01"
      value={price}
      onChange={(e) => setPrice(e.target.value)}
      data-cy="product-price"
    />
  </div>

  <button
    type="submit"
    style={{ padding: "10px 12px", borderRadius: 10, border: "1px solid #111", background: "#111", color: "#fff" }}
    data-cy="save-product"
  >
    Salvar
  </button>

  {err && <div style={{ color: "crimson" }}>{String(err)}</div>}
</form>

      <div style={{ display: "grid", gap: 8 }}>
        <div style={{ display: "flex", gap: 8, alignItems: "center" }}>
          <button onClick={load} style={{ padding: "8px 10px", borderRadius: 10 }}>Recarregar</button>
          {loading && <span>Carregando...</span>}
        </div>

        <div style={{ border: "1px solid #eee", borderRadius: 12, overflow: "hidden" }}>
          <table style={{ width: "100%", borderCollapse: "collapse" }}>
            <thead style={{ background: "#fafafa" }}>
              <tr>
                <th style={{ textAlign: "left", padding: 10 }}>ID</th>
                <th style={{ textAlign: "left", padding: 10 }}>Código</th>
                <th style={{ textAlign: "left", padding: 10 }}>Nome</th>
                <th style={{ textAlign: "left", padding: 10 }}>Preço</th>
              </tr>
            </thead>
            <tbody>
              {items?.map((p) => (
                <tr key={p.id} style={{ borderTop: "1px solid #eee" }}>
                  <td style={{ padding: 10 }}>{p.id}</td>
                  <td style={{ padding: 10 }}>{p.code}</td>
                  <td style={{ padding: 10 }}>{p.name}</td>
                  <td style={{ padding: 10 }}>{p.price}</td>
                </tr>
              ))}
              {!loading && (!items || items.length === 0) && (
                <tr>
                  <td colSpan="4" style={{ padding: 12, opacity: 0.7 }}>Nenhum produto cadastrado.</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
