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

        <div class="center">
            <div class="container">

                <div class="title"><span>Login</span></div>
                <div class="invalid">
                    <% // This scriptlet prints out an error message when invalid login credentials are entered.
                        if ((Boolean) session.getAttribute("invalidCredentials")) {
                            out.println("<br><p style=\"color:red\">Invalid username or password. Please try again.</p>");
                            session.setAttribute("invalidCredentials", true);
                            session.removeAttribute("username");
                            session.invalidate();
                        }
                    %>
                </div>
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
