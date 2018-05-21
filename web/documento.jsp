<%-- 
    Document   : documento
    Created on : Apr 11, 2018, 1:30:41 AM
    Author     : dlcusr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Documentos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    </head>

    <jsp:include page="header.jsp"/>
    
    <body>
        <br />
       <h4><strong>Documentos</strong></h4> 
       
       <object type="text/plain" data="DocumentosTPI/00ws110.txt"></object>
        
    <jsp:include page="footer.jsp"/>
</body>
</html>
