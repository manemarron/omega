<%-- 
    Document   : login
    Created on : May 13, 2015, 12:47:51 AM
    Author     : manemarron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="jsp/styles_scripts.jsp" %>
        <% if(session.getAttribute("error") != null) { %>
        <script type="text/javascript">
            alert("<%= session.getAttribute("error") %>");
        </script>
        <% } 
       session.removeAttribute("error");%>
    </head>
    <body>
        <%@include file="jsp/header.jsp" %>
        <h1>Login</h1>
        <form action="<%= request.getContextPath()%>/login_control" method="post">
            username: <input type="text" name="username" value="" /><br/>
            password: <input type="password" name="password" /><br/>
            <input type="submit" value="Login" name="type" />            
        </form>
            <p>
                Si no estás registrado, <a href="register" >haz clic aquí</a> para registrarte
            </p>
    </body>
</html>
