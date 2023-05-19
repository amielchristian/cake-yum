<%-- 
    Document   : delete
    Created on : May 13, 2023, 8:52:51 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Account</title>
        <link rel="icon" href="/CakeYum/images/logo/cupcake.png"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet"> 
        <link href="styles/delete-styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->
        <%
        /*
        Cache Scriptlet
        */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            
            session.removeAttribute("blockCheckout"); // allow user to access Checkout page
        %>
        <div class="delete-body">
            <div class="delete-container">
                <span class="confirm-delete-msg">Are you sure you want to delete your account? This cannot be undone.</span>
                <form method="post" name="Delete" id="Delete" action="Delete"></form>
                <form method="post" name="Account" id="Account" action="Account"></form>
                
                <button class="delete-button" form="Delete" value="Delete">I'm sure. Delete my account.</button>
                <button class="back-button" form="Account">No, take me back!</button>
            </div>
        </div>
    </body>
</html>
