<%-- 
    Document   : cart
    Created on : Nov 24, 2022, 12:47:06 PM
    Author     : chris
--%>

<%@page import="java.util.*"%>
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
        Cache Scriptlet
        */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        %>
        
        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->
        
        <%
            List productsList = new ArrayList();
            if (productsList.isEmpty())   {
                out.println("<h1>It doesn't seem like you have anything in your cart yet.</h1>");
            }
            
            else    {
        %>
        
        <form>
            <input type="button" value="Checkout">
        </form>
        <% } %>
    </body>
</html>
