<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>IP-based Geo-Location Service</title>
	
	<!-- Angular imports-->  
  	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.6/angular.min.js"></script>
  	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.6/angular-animate.js"></script>
    
  	<!-- Bootstrap imports -->
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	 
	 <!-- Google JS API import -->
	 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
	  	
	<!-- CSS imports -->
  	<style>
  		.form-control.ng-invalid {
    		color:white;
    		background: #C80000;
  		}
	</style>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/errorMessages.css" />" />
	
	<!-- jQuery imports -->
	<script type="text/javascript" src="<c:url value="scripts/lib/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="scripts/lib/jquery.json-2.3.min.js" />"></script>
	
	<!-- Private JS imports -->
	<script src="<c:url value="scripts/search-jquery-json.js" />"></script>
	<script src="<c:url value="scripts/validateIpAngular.js" />"></script>
	<script src="<c:url value="scripts/initGoogleMaps.js" />"></script>
	
	<script type="text/javascript">
		var webAppContext="<c:url value="/" />";
  	</script>
	
</head>
<body ng-app="geoLocationService">

	<div id="searchContainer" class="container">
		
		<div class="jumbotron">
			<h2>IP-based Geo-Location Service</h2>
	
			<form name="ipsearchForm" class="form-inline" ng-controller="ValidateIpController">
				<div class="form-group">		
					<label for="ipAddress">Enter your IPv4 Address:</label>
					
					<input id="ipAddress" name="ipAddress" class="form-control" ng-model="inputIpAddressValue"
									size="15" maxlength="15" placeholder="XXX.XXX.XXX.XXX"
									ng-pattern='/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/'/>		
					
					<label for="search-button" class="btn btn-info" ng-disabled="ipsearchForm.$invalid">
						<span class="glyphicon glyphicon-search"></span> Search!</label>
    				<input type="submit" id="search-button" class="hidden"/>
				</div>
				<div style="padding-left: 180px;">
						<span class="errorMessage" ng-show="ipsearchForm.ipAddress.$error.pattern">
							Invalid IP-Address.
						</span>
				</div>
			</form>
		</div>
	</div>
	<div id="resultContainer" class="container" style="display:none">
		<div id="result">		
			<div style="border-radius: 25px; border: 2px solid #8AC007; padding: 20px;">
				<h2>GeoLocation:</h2>
				<table class="table table-hover" 
					>
				    <tr>
				        <td>Country Name: </td>
				        <td id=countryName></td>
				    </tr>
				    <tr>
				        <td>City Name: </td>
				        <td id="cityName"></td>
				    </tr>
				    <tr>
				        <td>Latitude: </td>
				        <td id="latitude"></td>
				    </tr>
				    <tr>
				        <td>Longitude: </td>
				        <td id="longitude"></td>
				    </tr>
				</table>
			</div>
			<!-- Insert the Google Maps -->
			<br/>
			<br/>
	 		<div id="googleMap" class="jumbotron">
	 			<div id="map-canvas" style="height:300px; width:500px"></div>
	 		</div>
		</div>
	</div>
</body>
</html>
