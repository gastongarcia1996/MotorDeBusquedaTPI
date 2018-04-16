<%-- 
    Document   : index
    Created on : Apr 11, 2018, 1:35:16 AM
    Author     : dlcusr
--%>

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
        <div style="position: relative; left: 2em;">
            <p class="font-weight-bold">Presentacion del motor de busqueda para la materia DLC</p>
        </div>
        <div style="position: relative; left: 3em;">
            <div class="form-group row">
                <div class="col-xs-3">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="">Ingrese la consulta</span>
                        </div>
                        <input type="text" class="form-control" placeholder="Ingrese la consulta">            
                        <button type="button" class="btn btn-primary">Buscar</button>                   
                    </div>   
                </div>
                <div class="col-xs-3" style="position: relative; left:38%">
                    <label><strong>Indexar los archivos:</strong></label>
                    <button type="button" class="btn btn-danger" >
                        Indexar
                    </button>
                </div>
            </div>          
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>