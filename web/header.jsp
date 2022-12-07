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
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
        <link href="styles/header-styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <header id="header">
        <div class="logo">
            <img src="images/logo/cupcake.png" alt="logo" id="logo-img"/>
            <h1 id="logo-name">CakeYum</h1>
            <img src="images/logo/cupcake.png" alt="logo" id="logo-img"/>
        </div>
        <nav class="navbar">
            <ul class="nav-ul">
                <li class="nav-list"><a href="index.jsp" class="nav-item">SHOP</a></li>
                <li class="nav-list"><a href="products.jsp" class="nav-item">PRODUCTS</a></li>
                <li class="nav-list"><a href="purchases.jsp" class="nav-item">ORDER TRACKER</a></li>
                <li class="nav-list"><a href="cart.jsp" class="nav-item">CART</a></li>
                    <%
                        // Login/Logout Scriptlet        
                        if (session.getAttribute("username") == null) {
                            out.println("<li class=\"nav-list\"><a href=\"login.jsp\" class=\"nav-item\">LOGIN</a></li>");
                        } else {
                            out.println("<li class=\"nav-list\"><a href=\"Account\" class=\"nav-item\"><img height=\"20\" src=\"user.png\">  " + session.getAttribute("username").toString().toUpperCase() + "</a></li>");
                        }
                    %>
            </ul>
        </nav>
    </header>
</html>
