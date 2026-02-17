import { Route, Routes } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./pages/Home";
import Products from "./pages/Products";
import RawMaterials from "./pages/RawMaterials";
import Bom from "./pages/Bom";
import Suggestions from "./pages/Suggestions";

export default function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<Products />} />
        <Route path="/raw-materials" element={<RawMaterials />} />
        <Route path="/bom" element={<Bom />} />
        <Route path="/suggestions" element={<Suggestions />} />
      </Routes>
    </Layout>
  );
}