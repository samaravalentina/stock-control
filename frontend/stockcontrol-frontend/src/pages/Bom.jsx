import { useState } from "react";
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

export default function Bom() {
  const [productId, setProductId] = useState(1);
  const [rawMaterialId, setRawMaterialId] = useState("");
  const [requiredQuantity, setRequiredQuantity] = useState("");
  const [items, setItems] = useState([]);
  const [error, setError] = useState("");

  async function loadBom() {
    setError("");
    try {
      const { data } = await api.get(`/products/${productId}/bom`);
      setItems(data);
    } catch (e) {
      setError(e?.response?.data?.message ?? e.message);
    }
  }

  async function saveItem() {
    setError("");
    try {
      await api.post(`/products/${productId}/bom`, {
        rawMaterialId: Number(rawMaterialId),
        requiredQuantity: Number(requiredQuantity),
      });

      await loadBom(); // recarrega a lista depois de salvar
    } catch (e) {
      setError(e?.response?.data?.message ?? e.message);
    }
  }

  return (
    <div style={{ padding: 24 }}>
      <h2>BOM (Bill of Materials)</h2>

      <div>
        <label>Product ID</label>
        <input
          value={productId}
          onChange={(e) => setProductId(Number(e.target.value))}
        />
        <button onClick={loadBom}>Carregar BOM</button>
      </div>

      <h3>Adicionar/Atualizar item do BOM</h3>

      <div>
        <label>Raw Material ID</label>
        <input
          value={rawMaterialId}
          onChange={(e) => setRawMaterialId(e.target.value)}
        />
      </div>

      <div>
        <label>Quantidade necessária</label>
        <input
          value={requiredQuantity}
          onChange={(e) => setRequiredQuantity(e.target.value)}
        />
      </div>

      <button onClick={saveItem}>Salvar</button>

      {error && <p style={{ color: "red" }}>{String(error)}</p>}

      <hr />

      <pre>{JSON.stringify(items, null, 2)}</pre>
    </div>
  );
}
