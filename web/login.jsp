<%-- 
    Document   : login
    Created on : Nov 24, 2022, 12:43:45 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            // This scriptlet prints out an error message when invalid login credentials are entered.
            if ((Boolean)session.getAttribute("invalidCredentials")) {
                out.println("<p style=\"color:red\">Invalid username or password. Please try again.</p>");
            }
        %>
        <form action="Login" method="post">
            Enter username: <input type="text" name="username"><br>
            Enter password <input type="password" name="password"><br>
            <input type="submit" value="Login">
        </form>
    </body>
</html>