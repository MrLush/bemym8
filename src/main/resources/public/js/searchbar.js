$(document).ready(function(){
    $('#searchButton').click(function(){
       if(document.getElementById("searchRequest").value){
               window.location.href = "https://bemym8.herokuapp.com/search/" + document.getElementById("searchRequest").value;
             }
             else{
               window.location.href = "https://bemym8.herokuapp.com";
             }
    });
});