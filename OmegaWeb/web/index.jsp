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
        <%@include file="jsp/loading.jsp" %>
        <%@include file="jsp/header.jsp" %>  
        <%
            boolean b;
            if (user != null && user.getDbName() == null) {
                b = true;
            } else {
                b = false;
            }%>
        <div id="createDatabase" style="display:<%= b ? "block" : "none"%>">
            <input type="hidden" id="user_id" value="<%= user.getId()%>"/>
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
        <div id="database" style="display:<%= !b ? "block" : "none"%>">
            <button onclick="callDeleteDatabase();">Borrar <%= user.getDbName() != null ? user.getDbName() : "null" %></button>
        </div>

    </body>
</html>
