<%-- 
    Document   : cart
    Created on : Nov 24, 2022, 12:47:06 PM
    Author     : chris
--%>

<%@page import="model.UserGetter"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="model.ProductGetter"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.CartGetter"%>
<%@page import="model.Product"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet"> 
        <link href="styles/cart-styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%
        /*
        Cache Scriptlet
        */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        %>
        
        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->
        <div class="cart-body">
        <div class="cart-container">
        <%
            if (session.getAttribute("username") == null)  {
                response.sendRedirect("LoginRedirect");
            }
            else    {
                // for connectivity
                String driver = getServletContext().getInitParameter("jdbcClassName");
                String username = getServletContext().getInitParameter("dbUsername");
                String password = getServletContext().getInitParameter("dbPassword");

                StringBuffer url = new StringBuffer((String)getServletContext().getInitParameter("jdbcDriverURL"))
                            .append("://")
                            .append((String)getServletContext().getInitParameter("dbHostName"))
                            .append(":")
                            .append((String)getServletContext().getInitParameter("dbPort"))
                            .append("/")
                            .append((String)getServletContext().getInitParameter("dbName"))
                            .append((String)getServletContext().getInitParameter("addlParams"));
                         
                // a preliminary call to the database for getting the ID associated with the current user
                UserGetter ug = new UserGetter(driver, username, password, url.toString());
                Integer userID = ug.getUserID((String)session.getAttribute("username"));  
                          
                // this retrieves the contents of the cart of the current user from the database
                CartGetter cg = new CartGetter(driver, username, password, url.toString());
                Map<Integer,Integer> cartContents = cg.getCart(userID);
                
                if (cartContents.isEmpty())   {
                %>
                    <div class="no-item">
                        <p>It doesn't seem like you have anything in your cart right now.</p>
                    </div>
                <%
                }
                else    {
                %>
                    <div class="cart-list">
                    <div class="cart-header">
                    <h1 class="cart-heading">Your Cart</h1>
                    </div>
                    
                    <div class="cart-div">
                    <div class="table-guide">
                    <p class="product-title">product</p>
                    <p class="price-title">price</p>
                    <p class="quantity-title">quantity</p>
                    <p class="edit-title"></p>
                    <p class="remove-title"></p>
                    </div>
                        
                    <form method="post" name="Checkout" id="Checkout" action="Checkout"></form>
                    <form name="remove" id="remove" action="RemoveFromCart"></form>
                <%
                    for (Map.Entry<Integer,Integer> entry : cartContents.entrySet())   {
                        ProductGetter pg = new ProductGetter(driver, username, password, url.toString());
                        Product product = pg.getProduct(entry.getKey());
                        
                        int productID = product.getID();
                        String name = product.getName();
                        Double price = product.getPrice();
                        String altName = product.getAltName();
                        String imageString = product.getImageString();
                %>
                        <div class="cart-item">
                            <input type="checkbox" name="<%= productID %>" value="<%= entry.getValue() %>" form="Checkout" checked="true"/>
                            <div class="top-cart-item">
                            <div class="product">
                                <img class="product-image" src="data:img/jpg;base64,<%= imageString %>">
                                <p class="item-title"><%=name%></p>
                            </div>
                            <p class="price-column"><b>&#8369</b><%= String.format("%.2f", price)%></p>
                            <p class="quantity-column"><%= entry.getValue()%></p>
                            </div>
                            <div class="bottom-cart-item">
                            <div class="edit-column">
                            <a href="Products?name=<%= altName %>"><button class="edit-button">Edit</button></a>
                            </div>
                            <div class="remove-column">
                            <button class="remove-button" form="remove" name="remove" value="<%= altName %>">Remove</button>
                            </div>
                            </div>
                        </div><br>
                  <%  } %>
                    </div>
                    </div>
                    
                    <div class="checkout-list">
                    <div class="checkout-container">
                    <button class="checkout-button" form="Checkout" value="Checkout">Checkout</button>
                    </div>
                    </div>
                    <%
                }
            }
        %>
        </div>
        </div>
    </body>
</html>
