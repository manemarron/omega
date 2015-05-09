<%-- 
    Document   : test
    Created on : May 9, 2015, 12:48:07 AM
    Author     : manemarron
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function AJAX_call(method,target,params) {
                var ajaxRequest;
                if (window.XMLHttpRequest) {
                    ajaxRequest = new XMLHttpRequest(); // IE7+, Firefox, Chrome, Opera, Safari
                } else {
                    ajaxRequest = new ActiveXObject("Microsoft.XMLHTTP"); // IE6, IE5
                }
                ajaxRequest.onreadystatechange = function () {
                    if (ajaxRequest.readyState === 4 && ajaxRequest.status === 200) {
                        alert("Success");
                    }
                }
                ajaxRequest.open(method, target, true /*async*/);
                ajaxRequest.setRequestHeader("Content-Type", "application/json");
                if(params)
                    ajaxRequest.send(JSON.stringify(params));
                else
                    ajaxRequest.send();
            }
        </script>
    </head>
    <body>
        <input type="text" id="dbName" />
        <input type="text" id="user" />
        <input type="text" id="pw" />
        <button onclick="AJAX_call('PUT','http://localhost:8080/OmegaWeb/db/api/createDatabase',{dbName:document.getElementById('dbName').value,user:document.getElementById('user').value,pw:document.getElementById('pw').value})">Crear BD</button>
    </body>
</html>
