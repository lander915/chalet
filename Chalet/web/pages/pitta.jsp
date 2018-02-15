<%-- 
    Document   : addMember
    Created on : 13-feb-2018, 20:53:27
    Author     : lander
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../Assets/style/reset.css">
        <link rel="stylesheet" href="../Assets/style/bootstrap.css">
        <link rel="stylesheet" href="../Assets/style/style.css">
        <link rel="icon" type="image/png" href="../Media/favicon.PNG" />
        <title>Chalet '98</title>
    </head>
    <body>
        <header class="jumbotron text-center">
            <h1>Chalet '98</h1>
        </header>

        <h1 id="titel">Pita bestellen</h1>

        <div class="container col-sm-12 pittapage">

            <%@include file="../WEB-INF/pittaForm.jspf" %>


        </div>

        <div class="container col-sm-10 order">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Naam</th>
                        <th>Soort</th>
                        <th>Type</th>
                        <th>Saus</th>
                        <th>Opmerking</th>
                        <th>Prijs</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>

        </div>

        <%@include file="../WEB-INF/footer.jspf" %>


        <script src="../Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="../Assets/script/bootstrap.js"></script>
        <script src="../Assets/script/script.js"></script>
        <script src="../Assets/script/webcam.js"></script>
    </body>
</html>
