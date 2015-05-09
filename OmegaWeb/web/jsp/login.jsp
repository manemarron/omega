<%-- 
    Document   : login
    Created on : Apr 20, 2015, 11:54:50 PM
    Author     : manemarron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="<%= request.getContextPath() %>/login_control" method="post">
            username: <input type="text" name="username" value="" /><br/>
            password: <input type="password" name="password" /><br/>
            <input type="submit" value="Login" name="type" />            
        </form>
    </body>
</html>
