<%@page import="data.Repositories"%>
<%@page import="ChaletController.Controller"%>
<%@page import="java.util.List"%>
<%@page import="domain.Lid"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="Assets/style/reset.css">
        <link rel="stylesheet" href="Assets/style/bootstrap.css">
        <link rel="stylesheet" href="Assets/style/style.css">
        <link rel="icon" type="image/png" href="Media/favicon.PNG" />
        <title>Chalet '98</title>
    </head>
    <body>
        <header class="jumbotron text-center">
            <h1>Chalet '98</h1>
        </header>

        <%
            List<Lid> leden = (List<Lid>) Repositories.getLedenRepository().getAllMembers();
            request.getSession().setAttribute("LEDEN", leden);
        %>

        <div class="container">
            <div class="row">

                <c:forEach var="lid" items="${LEDEN}">
                    <div class="article col-lg-4">
                        <img class="rounded-circle"
                             src="Media/${lid.imageUrl}"
                             alt="${USER.imageUrl}">

                        <h2><a href="setUser?user=${lid.id}">${lid.naam}</a></h2>
                        <c:if test="${lid.geld lt 0}">
                            <p class="red">Euro: ${lid.geld} </p>
                            <p><a class="btn btn-secondary" href="selectUser?user=${lid.id}" role="button">Gelieve geld te storten</a></p>
                        </c:if>
                        <c:if test="${lid.geld ge 0}">
                            <p>Euro: ${lid.geld}</p>
                            <p><a class="btn btn-secondary" href="selectUser?user=${lid.id}" role="button">Kies Drank</a></p>
                        </c:if>
                    </div>            
                </c:forEach>

            </div>

        </div>
        
        <footer class="page-footer orange">
    <!--Footer Links-->
    <div class="container-fluid">
        <div class="row">
            <ul>
                <li><a href="pages/inventaris.jsp">Inventaris</a></li>
                <li><a href="pages/addMember.jsp">Lid Toevoegen</a></li>
                <li><a href="pages/addDrink.jsp">Drank Toevoegen</a></li>
                <li><a href="pages/pitta.jsp">Pitta Bestellen</a></li>
            </ul>
        </div>
    </div>
</footer> 


<script src="Assets/script/jquery-3.3.1.min.js"></script>
<script src="Assets/script/bootstrap.js"></script>
<script src="Assets/script/script.js"></script>
</body>
</html>