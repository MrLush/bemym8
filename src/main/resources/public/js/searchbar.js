window.onload = function() {
  document.getElementById('btn').onclick = function()
  {
      if(document.getElementById("searchRequest").value){
        window.location.href = "https://bemym8.herokuapp.com/search?searchRequest=" + document.getElementById("searchRequest").value
      }
      else{
        window.location.href = "https://bemym8.herokuapp.com/"
      }
  };
}