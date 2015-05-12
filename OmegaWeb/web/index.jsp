<%-- 
    Document   : index
    Created on : Apr 21, 2015, 7:38:51 PM
    Author     : manemarron
--%>

<%@page import="users_connection.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User) session.getAttribute("user"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="jsp/styles_scripts.jsp" %>
        <link type="text/css" rel="stylesheet" href="css/index.css" />
        <script type="text/javascript" src="js/index.js" ></script>
    </head>
    <body>
        <%@include file="jsp/header.jsp" %>  
        <% if (user != null && user.getDbName() == null) { %>
        <div id="createDatabase">
            <p>
                Aun no has configurado tu base de datos.<br/>
                <button onclick="configureDatabase();" >Configura tu base de datos</button>
            </p>
            
            <div id="configureDB_div">
                Nombre de la base de datos: <input type="text" id="dbName" />
                Usuario: <input type="text" id="user" />
                Contraseña: <input type="password" id="pw" />
                Confirma contraseña: <input type="password" id="confirm_pw" />
                <button onclick="callConfigureDatabase();">Terminar</button>
            </div>
        </div>
        <% }%>

    </body>
</html>
