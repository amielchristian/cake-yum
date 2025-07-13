import Header from "../components/Header";
import "../styles/purchases-styles.css";

export default function OrderTracker() {
  const orders = [];

  return (
    <>
      <Header />
      <div className="purchases-body">
        <div className="purchases-container">
          {orders.length === 0 ? (
            <div className="no-item">You haven't placed any orders yet.</div>
          ) : (
            <>
              <div className="order">
                <div className="order-heading">
                  <p>Order ID: {"{orderID}"}</p>
                  <p>Placed on {"{dateTime}"}</p>
                </div>
                <div className="table" style={{ overflowX: "auto" }}>
                  <table>
                    <tr>
                      <th className="product-column">Product Name</th>
                      <th className="quantity-column">Qty.</th>
                      <th className="price-column">Price</th>
                    </tr>
                    <tr>
                      <td className="item-name">{"{productName}"}</td>
                      <td className="item-quantity">{"{quantity}"}</td>
                      <td className="item-price">
                        <b>&#8369;</b>
                        {"{price}"}
                      </td>
                    </tr>
                    <tr className="total-row">
                      <td className="total-title" colSpan={2}>
                        Total:{" "}
                      </td>
                      <td className="order-price">
                        <b>&#8369;</b>
                        {"{total}"}
                      </td>
                    </tr>
                  </table>
                </div>
              </div>
              <br />
              <form method="post" action="CakeYum_Receipt" target="_blank">
                <input
                  type="submit"
                  value="Download Receipt"
                  className="button"
                  name="submit"
                />
              </form>
              <br />
            </>
          )}
        </div>
      </div>
    </>
  );
}
