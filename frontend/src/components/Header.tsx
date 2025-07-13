import "../styles/header-styles.css";
import { useState } from "react";

export default function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const username = "Guest"; // Replace with actual username logic

  return (
    <header id="header">
      <div className="logo">
        <img src="header.gif" id="logo-img" />
      </div>
      <nav className="navbar">
        <ul className="nav-ul">
          <li className="nav-list">
            <a href="/" className="nav-item">
              SHOP
            </a>
          </li>
          <li className="nav-list">
            <a href="/products" className="nav-item">
              PRODUCTS
            </a>
          </li>
          <li className="nav-list">
            <a href="/purchases" className="nav-item">
              ORDER TRACKER
            </a>
          </li>
          <li className="nav-list">
            <a href="cart" className="nav-item">
              CART
            </a>
          </li>
          {isLoggedIn ? (
            <li className="nav-list">
              <a href="/login" className="nav-item">
                LOGIN
              </a>
            </li>
          ) : (
            <li className="nav-list">
              <a href="/account" className="nav-item">
                <div className="nav-account">
                  <img height="20" src="./user.png" />
                  <span>{username}</span>
                </div>
              </a>
            </li>
          )}
        </ul>
      </nav>
    </header>
  );
}
