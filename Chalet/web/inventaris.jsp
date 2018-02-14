<%-- 
    Document   : addMember
    Created on : 13-feb-2018, 20:53:27
    Author     : lander
--%>

<%@page import="data.Repositories"%>
<%@page import="domain.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        
        <h1 class="text-center">Inventaris</h1>

        <div class="container">
        <div class="row">

                <%
                    List<Product> products = (List<Product>) Repositories.getInventarisRepository().getAllProducts();
                    request.getSession().setAttribute("PRODUCTS", products);
                    System.err.println(products);
                %>

                <c:forEach var="prod" items="${PRODUCTS}">
                    <div class="article col-lg-4">
                        <img class="rounded-circle"
                             src="${prod.imageUrl}"
                             alt="Generic placeholder image">

                        <h2>${prod.naam}</h2>
                        <h3>In Stock: ${prod.aantal}</h3>
                        <a href="refill?prod=${prod.id}">Aanvullen</a>
                        <p>Euro: ${prod.prijs}</p>


                        <p><a class="btn btn-secondary" href="bestel?prod=${prod.id}" role="button">Bestel</a></p>

                    </div>
                </c:forEach>            
            </div>
                        </div>
        
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
    <script src="Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="Assets/script/bootstrap.js"></script>
        <script src="Assets/script/script.js"></script>
    </body>
</html>
