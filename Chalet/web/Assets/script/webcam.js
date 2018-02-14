/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function takeSnapshot() {
      var img = document.querySelector('img') || document.createElement('img');
      var context;
      var width = video.offsetWidth, 
              height = video.offsetHeight;

      canvas = canvas || document.createElement('canvas');
      canvas.width = width;
      canvas.height = height;

      context = canvas.getContext('2d');
      context.drawImage(video, 0, 0, width, height);

      img.src = canvas.toDataURL('image/png');
      document.body.appendChild(img);
    }

    // use MediaDevices API
    // docs: https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
    if (navigator.mediaDevices) {
      // access the web cam
      navigator.mediaDevices.getUserMedia({video: true})
      // permission granted:
        .then(function(stream) {
          video.src = window.URL.createObjectURL(stream);
          video.addEventListener('click', takeSnapshot);
        })
        // permission denied:
        .catch(function(error) {
          console.log("couldn't access the camera");
          $("#foto").append('Could not access the camera. Error: ' + error.name);
        });
    }


$( document ).ready(function() {
    $("#takePicture").on("click", takeSnapshot);
});