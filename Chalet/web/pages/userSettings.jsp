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
        <title>Chalet '98</title>
    </head>
    <body>
        <header class="jumbotron text-center">
            <h1>Chalet '98</h1>
        </header>
        
        <div class="container">
            <div class="row">
                <div class="article col-lg-4 col-centered">
                    <img class="rounded-circle"
                         src="../Media/${USER.imageUrl}"
                         alt="${USER.imageUrl}">

                    <h2 id="naam">${USER.naam}</h2>
                    <p class="geld">Euro: ${USER.geld}</p>
                    <p><a href="../index.jsp">Go back</a></p>
                </div>
            </div>
            <h1 class="text-center">Lid aanpassen</h1>

            <form class="col-sm-6 offset-sm-3 text-center specialForm" action="../settings" method="POST">
                <div class="form-group">
                    <label for="geld">Geld toevoegen</label>
                    <input type="number" class="form-control" id="geld" step="0.01" name="geld" min="0" value="0">
                </div>
                <a href="#" id="askPicture">Foto Nemen?</a>
                <div class="form-group picture">
                    <input type="hidden" id="picture" name="picture" value="false">
                    <label id="foto" for="foto"></label>
                    
                    <div id="webcam">
                    <video id="v" width="300" height="300" class="rounded-circle"></video>
                    <canvas id="c" style="display:none;" width="300" height="300"></canvas>
                    </div>
                    
                </div>
                <button type="submit" id="submitMemberChange "class="btn btn-primary">Aanpassen</button>
            </form>

            <%@include file="../WEB-INF/adminModal.jspf" %>
            <%@include file="../WEB-INF/footer.jspf" %>        
        </div>

        <script src="../Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="../Assets/script/bootstrap.js"></script>
        <script src="../Assets/script/script.js"></script>
        <script src="../Assets/script/webcam.js"></script>
    </body>
</html>
