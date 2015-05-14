<%-- 
    Document   : new_table
    Created on : May 13, 2015, 12:47:16 AM
    Author     : manemarron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Table</title>
        <%@include file="jsp/styles_scripts.jsp" %>
        <script type="text/javascript" src="js/new_table.js" ></script>
        <link rel="stylesheet" type="text/css" href="css/new_table.css" />
    </head>
    <body>
        <%@include file="jsp/loading.jsp" %>
        <%@include file="jsp/header.jsp" %>  
        <article id ="table">
            
            <h2>Nueva tabla</h2>
            <form name="table_form" onsubmit="return validateForm();" action="#">
                <input type="submit" value="Terminar" />
                <header>Nombre de la tabla: <input type="text" name="table_name" /></header>
                <section id="columnas"></section>
                <button type="button" onclick="agregarColumna()" >Agregar columna</button>
            </form>
        </article>
    </body>
</html>
