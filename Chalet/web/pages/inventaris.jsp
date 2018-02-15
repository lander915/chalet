<%-- 
    Document   : addMember
    Created on : 13-feb-2018, 20:53:27
    Author     : lander
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Collection"%>
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

        <h1 class="text-center">Inventaris</h1>

        <div class="container">
            <div class="row">

                <%
                    List<Product> products = (List<Product>) Repositories.getInventarisRepository().getAllProducts();
                    Collections.sort(products, Product.Comparators.STOCK);
                    request.getSession().setAttribute("PRODUCTS", products);
                %>

                <c:forEach var="prod" items="${PRODUCTS}">
                    <div class="article col-lg-4">
                        <img class="rounded-circle"
                             src="../${prod.imageUrl}"
                             alt="Generic placeholder image">

                        <h2>${prod.naam}</h2>

                        <c:if test="${prod.aantal lt 6}">
                            <h3 class="red">In Stock: ${prod.aantal}</h3>
                        </c:if>
                        <c:if test="${prod.aantal ge 6}">
                            <h3>In Stock: ${prod.aantal}</h3>
                        </c:if>


                        <p>Euro: ${prod.prijs}</p>
                        
                        <form class="col-sm-6 offset-sm-3 text-center" action="../refill" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="prodId" value="${prod.id}" />
                            <input type="number" class="form-control" id="aantal" min="0" step="1" name="aantal" placeholder="Vul een aantal in">
                        </div>
                        <button type="submit" class="btn btn-primary">Aanvullen</button>
                        </form>

                    </div>
                </c:forEach>            
            </div>
        </div>

                <%@include file="../WEB-INF/footer.jspf" %>
        <script src="../Assets/script/jquery-3.3.1.min.js"></script>    
        <script src="../Assets/script/bootstrap.js"></script>
        <script src="../Assets/script/script.js"></script>
    </body>
</html>
