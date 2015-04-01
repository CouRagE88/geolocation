<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<!-- Set the title to the located city's name -->
    <title>Location: ${cityName}</title>
    
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
	<h2>GeoLocation:</h2>
	   <table>
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
	 <div id="map-canvas" style="height:300px; width:500px"></div>

	<!-- Link to the home page so that the user can immediately start a new search -->
	<a href="<c:url value="/ipsearch.html"/>">Start a new Search</a>
</body>
</html>