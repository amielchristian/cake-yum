<%-- 
    Document   : account
    Created on : Nov 30, 2022, 1:41:15 AM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="/CakeYum/images/logo/cupcake.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Account - CakeYum</title>
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
    </body>
</html>
