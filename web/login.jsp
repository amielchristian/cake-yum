<%-- 
    Document   : login
    Created on : Nov 24, 2022, 12:43:45 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="styles/login-styles.css">
    </head>
    <body>

        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->

        <%
            // Invalidates session after returning to login screen
            if (session.getAttribute("username")!=null)    {
                session.invalidate();
            }
            // Ensures invalidation when previous login attempt failed
            if (request.getParameter("invalidLoginCredentials") != null)    {
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Invalid username or password. Please try again.');");  
                out.println("</script>");
            }
            // Ensures invalidation when previous login attempt failed
            if (request.getParameter("attemptedGuestAccess") != null)    {
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('You must log in first.');");  
                out.println("</script>");
            }
            
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        %>
        
        <div class="center">
            <div class="container">
                <div class="title"><span>Login</span></div>
                <form action="Login" method="post">
                    <div class="row">
                        <label>Username:</label>
                        <input type="text" class="text" name="username">
                    </div>
                    <div class="row">
                        <label>Password:</label> 
                        <input class="text" type="password" name="password">
                    </div>
                    <div class="row button">
                        <input class="button" type="submit" value="Login">
                    </div>
                </form>

            </div>

        </div>

    </body>
</html>
