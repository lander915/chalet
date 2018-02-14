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
        <link rel="stylesheet" href="Assets/style/reset.css">
        <link rel="stylesheet" href="Assets/style/bootstrap.css">
        <link rel="stylesheet" href="Assets/style/style.css">
        <title>Chalet '98</title>
    </head>
    <body>
        <header class="jumbotron text-center">
            <h1>Chalet '98</h1>
        </header>
        
        <h1 class="text-center">Een lid toevoegen</h1>

        <form class="col-sm-6 offset-sm-3 text-center" action="addMember" method="GET">
            <div class="form-group">
                <label for="naam">Naam</label>
                <input type="text" class="form-control" id="naam" name="naam" placeholder="Vul naam in">
            </div>
            <div class="form-group">
                <label for="geld">Geld</label>
                <input type="number" class="form-control" id="geld" name="geld" placeholder="Vul een bedrag in">
            </div>
            <button type="submit" class="btn btn-primary">Toevoegen</button>
        </form>
        
        <footer class="page-footer orange">
        <!--Footer Links-->
        <div class="container-fluid">
            <div class="row">
                <ul>
                    <li><a href="inventaris">Inventaris</a></li>
                    <li><a href="addMember.jsp">Lid Toevoegen</a></li>
                    <li><a href="addDrink.jsp">Drank Toevoegen</a></li>
                </ul>
            </div>
        </div>
    </footer>
    <script src="Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="Assets/script/bootstrap.js"></script>
        <script src="Assets/script/script.js"></script>
    </body>
</html>
