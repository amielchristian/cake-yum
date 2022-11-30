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
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <header id="header">
        <div class="logo">
            <h1>Dessert Shop</h1>
        </div>
        <nav class="navbar">
            <ul class="nav-list">
                <li><a href="index.jsp">Shop</a></li>
                <li><a href="order-page.jsp">Products</a></li>
                <li><a href="purchases.jsp">Order Tracker</a></li>
                <li><a href="cart.jsp">Cart</a></li>
                    <%
                        /*
                    Login/Logout Scriptlet        
                         */
                        if (session.getAttribute("username") == null) {
                            out.println("<li><a href=\"login.jsp\">Login</a></li>");
                        } else {
                            out.println("<li><a href=\"Account\"><img height=\"20\" src=\"user.png\">  " + session.getAttribute("username") + "</a></li>");
                        }
                    %>
            </ul>
        </nav>
    </header>
</html>
