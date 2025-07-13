import Header from "../components/Header";
import "../styles/index-styles.css";

export default function Index() {
  return (
    <>
      <Header />
        <div className="main">
            <div className="block">
                <fieldset>
                    <div className="bg">
                        <div className="flyinTxtCont">
                            <div className="flyIn lineOne">You</div>
                            <div className="flyIn lineTwo">Deserve a</div>
                            <div className="flyIn lineThree">Dessert</div>
                            <div className="flyIn lineFour">Order. Bake. Deliver. Dessert.</div>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>

        <div className="container">
            <div className="card">
                <div className="circle">
                <img className="icon-img" src="./clock-icon.png" />
                </div>
                <p className="title">Open Hours</p>

                <h3>Weekdays</h3>
                <h5>9:00 am - 10:00 pm</h5>

                <h3>Weekends</h3>
                <h5>8:00 am - 11:30 pm</h5>
            </div>

            <div className="card">
                <div className="circle">
                <img className="icon-img" src="./services-icon.png" />
                </div>
                <p className="title">Services</p>
                <h5>Various pastries, cakes, and beverages will be available in the shop. 
                    In some cases, catering and deliveries will also be offered. 
                    For further inquires, please email us at <a href = "mailto: dshop@gmail.com">dshop@gmail.com</a>.
                </h5>
            </div>

            <div className="card">
                <div className="circle">
                <img className="icon-img" src="./team-icon.png" />
                </div>
                <p className="title">Team</p>
                <h5>
                    The four people behind this amaaaazingg shop are Charles Joaquin, Amiel Mala-ay, Patricia Poblete and Ann Salazar. 
                    They are all taking Bachelor of Science in Computer Science at the University of Santo Tomas.
                </h5>
            </div>

            <div className="card">
                <div className="circle">
                <img className="icon-img" src="./call-icon.png" />
                </div>
                <p className="title">Call Us</p>
                <h5>For catering services:</h5>
                <h5><a href = "mailto: dshop.cater@gmail.com">dshop.cater@gmail.com</a>.</h5>
                <br />
                <h5>Visit us at our physical store:</h5>
                <h5>España Blvd, Sampaloc, Manila, 1008 Metro Manila</h5>
            </div>

        </div>
    </>
  );
}
