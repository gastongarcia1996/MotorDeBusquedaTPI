<%-- 
    Document   : index
    Created on : Apr 11, 2018, 1:35:16 AM
    Author     : dlcusr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Motor de Busqueda</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />

        <link rel="apple-touch-icon" sizes="57x57" href="/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192"  href="/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
        <link rel="manifest" href="/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <br />
        <div style="position: relative; left: 1em;">
            <h4><strong>Presentacion del motor de busqueda para la materia DLC</strong></h4>
        </div>
        <div class="row">
            <div class="col-md-9">
                <div class="input-group" style="width: 45em">

                    <span class="input-group-addon" id="basic-addon1"><strong>Ingrese la consulta</strong></span>                 
                    <input type="text" class="form-control" placeholder="Ingrese la consulta" aria-describedby="basic-addon1">                   
                    <span class="input-group-btn" aria-hidden="true"><button class="btn btn-primary"> 
                            <span class="glyphicon glyphicon-search"></span> Buscar</button></span>                  
                </div>   

            </div>
            <div class="col-md-3">
                <label><strong>Indexar los archivos:</strong></label>
                <button type="button" class="btn btn-danger" onclick="cargar_tabla()" >
                    <span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
                    Indexar
                </button>
            </div>

        </div>
        <div id="div_carga">


        </div>
    <br />
    <jsp:include page="footer.jsp"/>

    <script>
        function cargar_tabla()
        {
            var div = docuemnt.getElementById("div_carga");
            
            var html = "<table>";
            html += "<thead>";
            html += "<div class="col-md-4"><h4>Nombre</h4></div>";
            html += "<div class="col-md-4"><h4>Peso</h4></div>";
            html += "</thead>";
            html += "<tbody>";
            html += "<c:forEach items="${docs}" var="doc">";
            html += "<tr>";
            html += "<td class="col-md-4">${doc.nombre}</td>";
            html += "<td class="col-md-4">${doc.peso}</td>";"
            html += "</tr>";
            html += "</c:forEach>";
            html += "</tbody>";
            html += "</table>";
            
            div.innerHTML = html; 
        }
                   
    </script>
</body>
</html>