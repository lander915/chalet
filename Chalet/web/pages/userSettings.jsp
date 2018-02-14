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
                         src="../${USER.imageUrl}"
                         alt="Generic placeholder image">

                    <h2>${USER.naam}</h2>
                    <p class="geld">Euro: ${USER.geld}</p>
                    <p><a href="index.jsp">Go back</a></p>
                </div>
            </div>
            <h1 class="text-center">Lid aanpassen</h1>

            <form class="col-sm-6 offset-sm-3 text-center" action="../settings" method="GET">
                <div class="form-group">
                    <label for="naam">Naam veranderen</label>
                    <input type="text" class="form-control" id="naam" name="naam" value="${USER.naam}">
                </div>
                <div class="form-group">
                    <label for="geld">Geld toevoegen</label>
                    <input type="number" class="form-control" id="geld" name="geld" min="0" value="0">
                </div>
                <div class="form-group">
                    <label id="foto" for="foto"></label>
                    
                    <div id="webcam">
                    <video id="v" width="300" height="300" class="rounded-circle"></video>
                    <canvas id="c" style="display:none;" width="300" height="300"></canvas>
                    </div>
                    
                </div>
                <button type="submit" id="submitMemberChange "class="btn btn-primary">Toevoegen</button>
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
        </div>

        <script src="../Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="../Assets/script/bootstrap.js"></script>
        <script src="../Assets/script/script.js"></script>
        <script src="../Assets/script/webcam.js"></script>
    </body>
</html>
