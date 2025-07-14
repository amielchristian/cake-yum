import { BrowserRouter, Routes, Route } from "react-router";
import Index from "./pages/Index";
import OrderTracker from "./pages/OrderTracker";
import Cart from "./pages/Cart";
import Products from "./pages/Products";

function App() {
  return (
    <BrowserRouter basename="/cake-yum">
      <Routes>
        <Route path="/" element={<Index />} />
        <Route path="/products" element={<Products />} />
        <Route path="/purchases" element={<OrderTracker />} />
        <Route path="/cart" element={<Cart />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
