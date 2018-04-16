<%-- 
    Document   : error
    Created on : Apr 11, 2018, 8:08:06 PM
    Author     : dlcusr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>${errorMsg.titulo}</h1>
        <p>${errorMsg.mensaje}</p>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
