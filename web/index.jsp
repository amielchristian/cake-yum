<%-- 
    Document   : index
    Created on : Nov 24, 2022, 10:52:37 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <link href="https://fonts.googleapis.com/css2?family=Mogra&display=swap" rel="stylesheet"> 
        <link rel="stylesheet" href="styles/index-styles.css">
        <link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet'>
    </head>
    <body>
        <%
            /*
        Cache Scriptlet
             */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            session.setAttribute("invalidCredentials", false);
        %>

        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->


        <div class="main">
            <div class="block">
                <fieldset>
                    <div class="bg">
                        <div class="flyinTxtCont">
                            <div class="flyIn lineOne">You</div>
                            <div class="flyIn lineTwo">Deserve a</div>
                            <div class="flyIn lineThree">Dessert</div>
                            <div class="flyIn lineFour">Order. Bake. Deliver. Dessert.</div>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>

        <div class="container">
            <div class="card">
                <div class="circle">
                <img class="icon-img" src="shop-images/clock-icon.png">
                </div>
                <p class="title">Open Hours</p>

                <h3>Weekdays</h3>
                <h5>9:00 am - 10:00 pm</h5>

                <h3>Weekends</h3>
                <h5>8:00 am - 11:30 pm</h5>
            </div>

            <div class="card">
                <div class="circle">
                <img class="icon-img" src="shop-images/services-icon.png">
                </div>
                <p class="title">Services</p>
                <h5>Various pastries, cakes, and beverages will be available in the shop. 
                    In some cases, catering and deliveries will also be offered. 
                    For further inquires, please email us at <a href = "mailto: dshop@gmail.com">dshop@gmail.com<a>.
                </h5>
            </div>

            <div class="card">
                <div class="circle">
                <img class="icon-img" src="shop-images/team-icon.png">
                </div>
                <p class="title">Team</p>
                <h5>
                    The four people behind this amaaaazingg shop are Charles Joaquin, Amiel Mala-ay, Patricia Poblete and Ann Salazar. 
                    They are all taking Bachelor of Science in Computer Science at the University of Santo Tomas.
                </h5>
            </div>

            <div class="card">
                <div class="circle">
                <img class="icon-img" src="shop-images/call-icon.png">
                </div>
                <p class="title">Call Us</p>
                <h5>For catering services:</h5>
                <h5><a href = "mailto: dshop.cater@gmail.com">dshop.cater@gmail.com<a>.</h5>
                            <br>
                <h5>Visit us at our physical store:</h5>
                <h5>España Blvd, Sampaloc, Manila, 1008 Metro Manila</h5>
            </div>

        </div>
    </body>
</html>
