/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var video = document.getElementById("v");
var canvas = document.getElementById("c");
var button = document.getElementById("b");
var state = false;

function showVideo() {
    //var img = document.querySelector('.photo') || document.createElement('img');
    var context;
    var width = video.offsetWidth+65,
            height = video.offsetHeight;

    canvas = canvas || document.createElement('canvas');
    canvas.width = width;
    canvas.height = height;

    console.log("bezig");

    context = canvas.getContext('2d');
    context.drawImage(video, 0, 0, width, height);


    //img.src = canvas.toDataURL('image/png');
    //document.body.appendChild(img);
}

function askForPicture(){
    $(".picture").slideToggle("slow").css("height", "300px");
    $('html, body').animate({
		scrollTop: $("footer").offset().top
	},1000);
     
    if (navigator.mediaDevices) {
    // access the web cam
    navigator.mediaDevices.getUserMedia({video: true})
            // permission granted:
            .then(function (stream) {
                video.srcObject = stream;
            $("footer").remove();
            $(".container").append('<footer class="page-footer orange">'+
                        '<div class="container-fluid">'+
                            '<div class="row">'+
                                '<ul>'+
                                    '<li><a href="inventaris.jsp">Inventaris</a></li>'+
                                    '<li><a href="addMember.jsp">Lid Toevoegen</a></li>'+
                                    '<li><a href="addDrink.jsp">Drank Toevoegen</a></li>'+
                                '</ul>'+
                            '</div>'+
                        '</div>'+
                    '</footer>');
                setInterval(function () {
                    if (!state)
                        showVideo();
                }, 10);
            })
            // permission denied:
            .catch(function (error) {
                console.log(error);
                $("#foto").append('Could not access the camera. Error: ' + error.name);
            });
}
}
// use MediaDevices API
// docs: https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia


function takeSnapShot() {
    state = true;
    $("#v").remove();
    
    var userName = $("#username").text();

    $("#webcam").append("<a download="+userName+".png href="+canvas.toDataURL('image/png')+" title='profilePic' id='download' ><img class='rounded-circle' src=" + canvas.toDataURL('image/png') + " name = imageUrl ></a>");    
    console.log($("#webcam>a")[0].click());
    $("#webcam>a").click();
}


$(document).ready(function () {
    $("#askPicture").on("click", askForPicture);
    $("video").on("click", takeSnapShot);
});