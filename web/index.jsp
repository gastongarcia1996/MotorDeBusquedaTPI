<%-- 
    Document   : index
    Created on : Apr 11, 2018, 1:35:16 AM
    Author     : dlcusr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Motor de Busqueda</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>

        <!--<link href="fonts/fontawesome-all.css" rel="stylesheet"> -->   
        <script defer src="fonts/js/fontawesome-all.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <br />
        <div style="position: relative; left: 1em;">
            <h4><strong>Presentacion del motor de busqueda para la materia DLC</strong></h4>
        </div>
        
        <form method="post" action="<c:url value="/consulta"/>">
            <div>${formMsg}</div>
            <div class="row" style="position: relative; left: 1em;">          
                <div class="col-md-9">
                    <div class="input-group" style="width: 45em">

                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"><strong>Ingrese la consulta</strong></span> 
                        </div>
                        <input type="text" class="form-control" name="consulta" placeholder="Ingrese la consulta" aria-describedby="basic-addon1">                   
                        <span class="input-group-append" aria-hidden="true"><button type="submit" class="btn btn-primary"> 
                                <span><i class="fas fa-search"></i></span> Buscar</button></span>                   
                    </div>   

                </div>

                <div class="col-md-3">
                    <label><strong>Indexar los archivos:</strong></label>
                    <a class="btn btn-danger" href="<c:url value="/indexacion"/>" target="_blank">
                        <span aria-hidden="true"><i class="fas fa-arrow-alt-circle-up"></i></span>
                        Indexar
                    </a>
                </div>                
            </div>
        </form>
        <br />
        <div class="row" style="position: relative; left: 1em;">
            <div class="col-md-6" id="div_carga">
                <h3>Resultados de la busqueda de :''${consulta}'':</h3>
                <table class="table table-hover table-bordered">
                    <thead>
                    <th><h4>Id</h4></th>
                    <th><h4>Nombre</h4></th> 
                    <th><h4>Peso</h4></th>
                    </thead>
                    <tbody>
                        <c:forEach items="${documentos}" var="documento">
                            <tr>
                                <td>${documento.id_doc}</td>
                                
                                <td><a name="documento" href="<c:url value="/documento?namedoc=${documento.nombre_doc}"/>">${documento.nombre_doc}</a></td>  <%-- <c:url value="/documento"/>--%>
                                <td>${documento.peso}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
        <br />
        <jsp:include page="footer.jsp"/>

        <script type="text/javascript">

            function indexar()
            {
                window.location.open('<c:url value="/indexacion"/>', '_blank');

            }

        </script>
    </body>
</html>