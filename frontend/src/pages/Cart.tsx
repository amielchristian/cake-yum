import Header from "../components/Header";
import "../styles/cart-styles.css";
import { useState } from "react";

export default function Cart() {
  const [orders, _] = useState([]);

  return (
    <>
      <Header />
      <div className="cart-body">
        <div className="cart-container">
          {orders.length === 0 ? (
            <div className="no-item">
              <p>
                It doesn't seem like you have anything in your cart right now.
              </p>
            </div>
          ) : (
            <>
              <div className="cart-list">
                <div className="cart-header">
                  <h1 className="cart-heading">Your Cart</h1>
                </div>

                <div className="cart-div">
                  <div className="table-guide">
                    <p className="product-title">product</p>
                    <p className="price-title">price</p>
                    <p className="quantity-title">quantity</p>
                    <p className="edit-title"></p>
                    <p className="remove-title"></p>
                  </div>

                  <form
                    method="post"
                    name="Checkout"
                    id="Checkout"
                    action="Checkout"
                  ></form>
                  <form
                    name="remove"
                    id="remove"
                    action="RemoveFromCart"
                  ></form>
                  {orders.map(() => (
                    <>
                      <div className="cart-item">
                        <input
                          type="checkbox"
                          name="<%= productID %>"
                          value="<%= entry.getValue() %>"
                          form="Checkout"
                          checked={true}
                        />
                        <div className="top-cart-item">
                          <div className="product">
                            <img
                              className="product-image"
                              src="data:img/jpg;base64,<%= imageString %>"
                            />
                            <p className="item-title">order.name</p>
                          </div>
                          <p className="price-column">
                            <b>&#8369</b>order.price
                          </p>
                          <p className="quantity-column">order.quantity</p>
                        </div>
                        <div className="bottom-cart-item">
                          <div className="edit-column">
                            <a href="Products?name=<%= altName %>">
                              <button className="edit-button">Edit</button>
                            </a>
                          </div>
                          <div className="remove-column">
                            <button
                              className="remove-button"
                              form="remove"
                              name="remove"
                              value="<%= altName %>"
                            >
                              Remove
                            </button>
                          </div>
                        </div>
                      </div>
                      <br />
                    </>
                  ))}
                </div>
              </div>

              <div className="checkout-list">
                <div className="checkout-container">
                  <button
                    className="checkout-button"
                    form="Checkout"
                    value="Checkout"
                  >
                    Checkout
                  </button>
                </div>
              </div>
            </>
          )}
        </div>
      </div>
    </>
  );
}
