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

    context = canvas.getContext('2d');
    context.drawImage(video, 0, 0, width, height);


    //img.src = canvas.toDataURL('image/png');
    //document.body.appendChild(img);
}

function askForPicture(){
    $(".picture").slideToggle("slow");
    $(".specialForm").css("height", "450px");
    $(".specialFormTwo").css("height", "620px");
    $('html, body').animate({
		scrollTop: $("footer").offset().top
	},1000);
     
    if (navigator.mediaDevices) {
    // access the web cam
    navigator.mediaDevices.getUserMedia({video: true})
            // permission granted:
            .then(function (stream) {
                video.srcObject = stream;
                setInterval(function () {
                    if (!state)
                        showVideo();
                }, 10);
            })
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
    
    var naam = $("#naam").text();
    if (naam == "") naam = $("#naam").val();

    $("#webcam").append("<a download="+naam+".png href="+canvas.toDataURL('image/png')+" title='profilePic' id='download' ><img class='rounded-circle' src=" + canvas.toDataURL('image/png') + " name = imageUrl ></a>");
    $("#webcam a")[0].click();
}


$(document).ready(function () {
    $("#askPicture").on("click", askForPicture);
    $("video").on("click", takeSnapShot);
});