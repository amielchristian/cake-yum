<%-- 
    Document   : cart
    Created on : Nov 24, 2022, 12:47:06 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <%
        /*
        Login Scriptlet
            If the user has not logged in, this scriptlet prevents them from seeing the other pages in the web app
        */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            
            if (session.getAttribute("username") == null)   {
                response.sendRedirect("login.jsp");
            }
        %>
        
        <header id="header">
            <div class="logo">
                <h1>Website Name</h1>
            </div>
            <nav class="navbar">
                <ul class="nav-list">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="order-page.jsp">Discover</a></li>
                    <li><a href="purchases.jsp">Order Tracker</a></li>
                    <li><a href="cart.jsp">Cart</a></li>
                    <li><a href="Logout">Logout</a></li>
                </ul>
            </nav>
        </header>
    </body>
</html>
