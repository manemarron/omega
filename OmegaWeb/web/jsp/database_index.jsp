<%-- 
    Document   : database_index
    Created on : May 12, 2015, 8:46:53 PM
    Author     : manemarron
--%>
<%@page import="users_connection.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    User usr = (User)session.getAttribute("user");
    String dbName = usr.getDbName()==null ? "" : usr.getDbName();
    String dbUsr = usr.getDbUser()==null ? "" : usr.getDbUser();
    String dbPw = usr.getDbPw()==null ? "" : usr.getDbPw();
%>
<button id="db_button" onclick="callDeleteDatabase();">
    Eliminar la base de datos
</button>
<h3 id="db_name"><%= dbName %></h3>
<p><b>username</b>: <span id="username_span" ><%= dbUsr %></span></p>
<p><b>password</b>: <span id="password_span" ><%= dbPw %></span></p>
<ul id="table_list"></ul>
