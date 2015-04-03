<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta charset="utf-8">
	<!-- Set the title to the located city's name -->
    <title>Location: ${cityName}</title>
    
    <!-- Bootstrap -->
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    
    <!-- Third party script to display the location on the Google Maps -->
    <script
    	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
	<script>
		var map;
		var latitude = ${latitude};
		var longitude = ${longitude};
		
		function initialize() {
						
		  var mapOptions = {
		    zoom: 8,
		    center: new google.maps.LatLng(latitude,longitude)
		  };
		  map = new google.maps.Map(document.getElementById('map-canvas'),
		      mapOptions);
		}
		google.maps.event.addDomListener(window, 'load', initialize);
	</script>    
</head>
<body>
	<!-- Simple table format to list the output
		 values come from the Model returned by the Controller -->
		 
	<div class="container">
		<h2>GeoLocation:</h2>
			<table class="table table-hover">
			    <tr>
			        <td>Country Name: </td>
			        <td>${countryName}</td>
			    </tr>
			    <tr>
			        <td>City Name: </td>
			        <td>${cityName}</td>
			    </tr>
			    <tr>
			        <td>Latitude: </td>
			        <td>${latitude}</td>
			    </tr>
			    <tr>
			        <td>Longitude: </td>
			        <td>${longitude}</td>
			    </tr>
		</table>

	 <!-- Insert the Google Maps -->
	 <div class="jumbotron">
	 	 <div id="map-canvas" style="height:300px; width:500px"></div>
	 </div>

	<div>
	<!-- Link to the home page so that the user can immediately start a new search -->
	<!-- <a href="<c:url value="/ipsearch.html"/>">Start a new Search</a>-->
	<a href="<c:url value="/ipsearch.html"/>" class="btn btn btn-info btn-lg" role="button"><span class="glyphicons glyphicons-star-empty"> Again?</a>
	
	<button type="submit" name="lucky" class="btn btn btn-info btn-lg"><span class="glyphicons glyphicons-star-empty"></span>I'm feeling lucky!</button>

	</div>
	
	</div>
</body>
</html>