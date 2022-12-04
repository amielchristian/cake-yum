<%-- 
    Document   : header
    Created on : Nov 30, 2022, 4:32:28 PM
    Author     : Charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Mogra&display=swap" rel="stylesheet"> 
        <link href="styles/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <header id="header">
        <div class="logo">
            <img src="temp-image-folder/cupcake.PNG" alt="logo"/>
            <h1>Dessert Shop</h1>
        </div>
        <nav class="navbar">
            <ul class="nav-list">
                <li><a href="index.jsp">SHOP</a></li>
                <li><a href="products.jsp">PRODUCTS</a></li>
                <li><a href="purchases.jsp">ORDER TRACKER</a></li>
                <li><a href="cart.jsp">CART</a></li>
                    <%
                        /*
                    Login/Logout Scriptlet        
                         */
                        if (session.getAttribute("username") == null) {
                            out.println("<li><a href=\"login.jsp\">LOGIN</a></li>");
                        } else {
                            out.println("<li><a href=\"Account\"><img height=\"20\" src=\"user.png\">  " + session.getAttribute("username").toString().toUpperCase() + "</a></li>");
                        }
                    %>
            </ul>
        </nav>
    </header>
</html>
