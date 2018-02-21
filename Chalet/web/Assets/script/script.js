/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var redirect = function () {
    window.location.replace("http://localhost:8080/Chalet/");
};

var checkImages = function () {
    var elements = document.getElementsByClassName("rounded-circle");

    for (var i = 0; i < elements.length; i++) {
        //console.log(elements[i].src);
        var urlstatus = UrlExists(elements[i].src);
        if (urlstatus === false) {
            if (UrlExists('../Media/default.PNG'))
                elements[i].src = '../Media/default.PNG';
            else
                elements[i].src = 'Media/default.PNG';
        }
    }


};

function UrlExists(url) {
    var http = new XMLHttpRequest();
    http.open('HEAD', url, false);
    http.send();
    return http.status !== 404;
}

var totalPrice = 0;
var removeFromList = function () {

    $(this).parent().parent().remove();
    var prijs = $(this).parent().parent().find("td:eq(5)").data("prijs");
    prijs = parseFloat(prijs);
    totalPrice = totalPrice - prijs;
    $("#titel").text("Pita bestellen voor " + totalPrice + "€");
};

var addToList = function () {
    event.preventDefault();

    var naam = $(this).parent().find("#naam").val();
    var soort = $(this).parent().find("select").val();
    var type = $(this).parent().find("select:eq(1)").val();
    var saus = $(this).parent().find("select:eq(-1)").val();
    var prijs = $(this).parent().find("select:eq(0)").find(":selected").data("prijs");
    prijs = parseFloat(prijs);
    totalPrice = totalPrice + prijs;
    var opmerking = $(this).parent().find("textarea").val();

    $("#titel").text("Pita bestellen voor " + totalPrice + "€");

    $("tbody:last-child").append("<tr>");
    var trString = "<td>" + naam + "</td>" + "<td>" + soort + "</td>" + "<td>" + type + "</td>" + "<td>" + saus + "</td>" + "<td>" + opmerking + "</td>" + "<td data-prijs=" + prijs + ">" + prijs + " €</td>" + "<td><button type='button' class='btn btn-danger delPita'>&#10007</button></td>";
    $("tbody tr:last-child").append(trString);
    $("tbody:last-child").append("</tr>");

    $("#pittaForm")[0].reset();
};


var getData = function () {
    console.log("in getdata");
    reqUrl = $(this)[0].href;
};

var askAdmin = function () {
    event.preventDefault();
    var pw = $("#password").val();
    console.log(reqUrl);

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/Chalet/admin",
        data: {'pw': pw},
        success: function (data, textStatus) {
            window.location = reqUrl;
        },
        error: function (xhr, textStatus, errorThrown) {
            window.location.reload();
        }
    });

};

$(document).ready(function () {
    $("header").on("click", redirect);
    $(document).on("submit", "#pittaForm", addToList);
    $(document).on("click", ".delPita", removeFromList);
    $(document).on("click", ".adminLink a", getData);
    $(document).on("submit", "#adminForm", askAdmin);

    checkImages();
});

