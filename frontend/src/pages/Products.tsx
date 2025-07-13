import Header from "../components/Header";

export default function Products() {
  const items = [
    {
      name: "Chocolate Cake",
      image: "chocolate-cake.jpg",
      price: 500,
    },
    {
      name: "Vanilla Cake",
      image: "vanilla-cake.jpg",
      price: 450,
    },
    {
      name: "Red Velvet Cake",
      image: "red-velvet-cake.jpg",
      price: 600,
    },
  ];

  return (
    <>
      <Header />
      <div className="main">
        <div className="products-container">
          {items.map((item) => (
            <a href={`/products/${item.name}`} className="card">
              <img src={item.image} alt={`Image of ${item.name}`} />
              <div>
                <h2>{item.name}</h2>
                <h3>
                  Price: <b>₱</b>
                  {item.price}
                </h3>
              </div>
            </a>
          ))}
        </div>
      </div>
    </>
  );
}
