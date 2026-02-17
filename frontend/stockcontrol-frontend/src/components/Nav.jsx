import { NavLink } from "react-router-dom";

const linkStyle = ({ isActive }) => ({
    padding: "10px 12px",
    borderRadius: 10,
    textDecoration: "none",
    color: isActive ? "#fff" : "#111",
    background: isActive ? "#111" : "transparent"
});

export default function Nav() {
    return (
        <nav style={{ display: "flex", gap: 8, flexWrap: "wrap" }}>
            <NavLink to="/" style={linkStyle}>Início</NavLink>
            <NavLink to="/products" style={linkStyle}>Produtos</NavLink>
            <NavLink to="/raw-materials" style={linkStyle}>Matérias-primas</NavLink>
            <NavLink to="/bom" style={linkStyle}>BOM</NavLink>
            <NavLink to="/suggestions" style={linkStyle}>Sugestões</NavLink>
        </nav>
    );
}