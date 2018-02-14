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
    var width = video.offsetWidth,
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

// use MediaDevices API
// docs: https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
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
            // permission denied:
            .catch(function (error) {
                console.log(error);
                $("#foto").append('Could not access the camera. Error: ' + error.name);
            });
}

function takeSnapShot() {
    state = true;
    $("#v").remove();
    $("#webcam").append("<img class='rounded-circle' src=" + canvas.toDataURL('image/png') + ">");
    dataURItoBlob(canvas.toDataURL('image/png'));

}


function dataURItoBlob(dataURI, callback) {
    // convert base64 to raw binary data held in a string
    // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
    var byteString = atob(dataURI.split(',')[1]);

    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

    // write the bytes of the string to an ArrayBuffer
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }

    // write the ArrayBuffer to a blob, and you're done
    var bb = new Blob([ab]);
    console.log(bb);


    $.ajax({
        type: 'POST',
        url: '../upload',
        data: bb,
        processData: false
    }).done(function (data) {
        console.log(data);
    });

    return bb;

}

$(document).ready(function () {
    $("video").on("click", takeSnapShot);
    //takeSnapshot()
});