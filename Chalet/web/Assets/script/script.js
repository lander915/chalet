/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var redirect = function (){
    window.location.href = "index.jsp";
};

$( document ).ready(function() {
    $("header").on("click", redirect);
    
});