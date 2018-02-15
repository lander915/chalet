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

        <h1 class="text-center">Drank toevoegen</h1>

        <form class="col-sm-6 offset-sm-3 text-center specialFormTwo" action="../addDrink" method="GET">
            <div class="form-group">
                <label for="naam">Naam</label>
                <input type="text" class="form-control" id="naam" name="naam" placeholder="Vul naam in" required>
            </div>
            <div class="form-group">
                <label for="geld">Prijs</label>
                <input type="number" class="form-control" id="prijs" min="0" step="0.01" name="prijs" placeholder="Vul een bedrag in" required>
            </div>
            <div class="form-group">
                <label for="aantal">Aantal</label>
                <input type="number" class="form-control" id="aantal" min="0" name="aantal" placeholder="Vul een aantal in" required>
            </div>
            <a href="#" id="askPicture">Foto Nemen?</a>
                <div class="form-group picture">
                    <label id="foto" for="foto"></label>
                    
                    <div id="webcam">
                    <video id="v" width="300" height="300" class="rounded-circle"></video>
                    <canvas id="c" style="display:none;" width="300" height="300"></canvas>
                    </div>
                    
                </div>
            <button type="submit" class="btn btn-primary">Toevoegen</button>
        </form>

        <footer class="page-footer orange">
            <!--Footer Links-->
            <div class="container-fluid">
                <div class="row">
                    <ul>
                        <li><a href="inventaris.jsp">Inventaris</a></li>
                        <li><a href="addMember.jsp">Lid Toevoegen</a></li>
                        <li><a href="addDrink.jsp">Drank Toevoegen</a></li>
                    </ul>
                </div>
            </div>
        </footer>
        <script src="../Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="../Assets/script/bootstrap.js"></script>
        <script src="../Assets/script/script.js"></script>
        <script src="../Assets/script/webcam.js"></script>
    </body>
</html>
