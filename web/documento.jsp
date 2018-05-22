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

        <!--<object type="text/plain" data="DocumentosTPI/00ws110.txt"></object>-->
        <div id="list">
            <iframe src="${docPath}" width="100%" id="idIframe" onload="autoResize('idIframe');" ></iframe>
            <script>

            </script>
        </div>
        <!--<p>$ {documentoString}</p>-->
        <a href="index.jsp">Volver Atras</a>
        <script type="text/javascript">
            function autoResize(id)
            {
                var newheight;
                var newwidth;
                if (document.getElementById)
                {
                    newheight = document.getElementById(id).contentWindow.document.body.scrollHeight;
                    newwidth = document.getElementById(id).contentWindow.document.body.scrollWidth;
                }
                document.getElementById(id).height = (newheight) + "px";
                document.getElementById(id).width = (newwidth) + "px";
            }
        </script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
