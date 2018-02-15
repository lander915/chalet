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
    console.log(elements);

    for (var i = 0; i < elements.length; i++) {
        //console.log(elements[i].src);
        var urlstatus = UrlExists(elements[i].src);
        console.log(urlstatus);
        if (urlstatus === false ){
            if(UrlExists('../Media/default.PNG')) elements[i].src = '../Media/default.PNG';
            else elements[i].src = 'Media/default.PNG';
//            var urlstatus = UrlExists(elements[i].src);
//            elements[i].src = '../Media/default.PNG';
        }
    }


};

function UrlExists(url) {
    var http = new XMLHttpRequest();
    http.open('HEAD', url, false);
    http.send();
    return http.status !== 404;
}


$(document).ready(function () {
    $("header").on("click", redirect);
    checkImages();
});

