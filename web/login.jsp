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
        <link rel="stylesheet" href="styles/login-styles.css">
    </head>
    <body>
        
        <div class="center">
        <div class="container">
            <div class="invalid">
        <%
            // This scriptlet prints out an error message when invalid login credentials are entered.
            if ((Boolean)session.getAttribute("invalidCredentials")) {
                out.println("<p style=\"color:red\">Invalid username or password. Please try again.</p>");
                session.setAttribute("invalidCredentials", true);
                session.removeAttribute("username");
                session.invalidate();
            }
        %>
            </div>
        <form action="Login" method="post">
            
            <label>Username:</label>
                <input type="text" name="username"><br>
                <label>Password:</label> 
                <input type="password" name="password"><br>
            <input class="button" type="submit" value="Login">
        </form>
            
        </div>
            
        </div>
       
    </body>
</html>
