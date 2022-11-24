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
        <title>Welcome!</title>
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
        <h1>Website Name</h1>
        
        <navbar>
        <form action="Logout">
            <input type="submit" value="Logout">
        </form>      
        </navbar>
    
    </body>
</html>
