<%-- 
    Document   : account
    Created on : Nov 30, 2022, 1:41:15 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Details</title>
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
                    <%
                    /*
                    Login/Logout Scriptlet        
                    */
                        if (session.getAttribute("username") == null)   {
                            out.println("<li><a href=\"login.jsp\">Login</a></li>");
                        }
                        else    {
                            out.println("<li><a href=\"account.jsp\"><img height=\"20\" src=\"user.png\">  "+session.getAttribute("username")+"</a></li>");
                        }
                    %>
                </ul>
            </nav>
        </header>
        
        <h1><%= session.getAttribute("username") %>'s Account</h1>
        <table>
            <tr><th>Account Details</th></tr>
            <tr>
                <td>Name: </td>
            </tr>
            <tr>
                <td>Address: </td>
            </tr>
            <tr>
                <td>Contact Number: </td>
            </tr>
        </table>
    </body>
</html>