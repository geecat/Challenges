<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
</script>
<script>
$(document).ready(function(){
	
  $("#send").click(function(){
	 /*  $.ajax({
			type : "POST",
			url : "movies/add",
			data : { fromDate: fromDate, toDate: toDate },
			success : function(resp) {  alert("The paragraph was clicked.");
			},
			error : function() {
				//This  alert will be moved to property file. 
				alert("Error occured , If Error persists please contact adminstrator.");
				
			}
		}); */
		
		var obj = {
				"name":"Test me dude!!",
				
		};
		abc(obj);
	  
 
  });
  
 var abc =  function(obj){
	  $.ajax({
			type : "POST",
			url : "movies/add",
			data :JSON.stringify(obj),
			contentType: "application/json",
			success : function(resp) {  
				
				alert("The paragraph was clicked."+resp.name);
			},
			error : function() {
				//This  alert will be moved to property file. 
				alert("Error occured , If Error persists please contact adminstrator.");
				
			}
		});
  };
});
</script>
</head>
<body>

<p>Click on this paragraph.</p>
<button id="send">Test Me</button>
</body>
</html>

