<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
	<meta property="og:url"                content="http://www.nytimes.com/2015/02/19/arts/international/when-great-minds-dont-think-alike.html" />
	<meta property="og:title"              content="IP-based Geolocation" />
	<meta property="og:description"        content="Your current location based on the entered IP-Address" />
	<meta property="og:site_name"          content="GeoLocation" />
	<meta property="og:image"              content="http://static01.nyt.com/images/2015/02/19/arts/international/19iht-btnumbers19A/19iht-btnumbers19A-facebookJumbo-v2.jpg" />
	<meta property="og:article_author"     content="David Keri" />
	<meta property="og:article_publisher"  content="Epam" />
	<meta property="fb:app_id"             content="961883970498028" />
	
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
	
	<script src="<c:url value="scripts/facebookLogin.js" />"></script>
	<!-- <script src="<c:url value="scripts/facebookLoginJQuery.js" />"></script>-->
	
	<script type="text/javascript">
		var webAppContext="<c:url value="/" />";
  	</script>
	
</head>
<body ng-app="geoLocationService">

	<div id="searchContainer" class="container">
			
		<div class="jumbotron">
			
			<div id="header">
				<div id="title" style="width: 450px; display:inline; float: left;">
					<h2>IP-based Geo-Location Service</h2>
				</div>
				<div class="fb-share-button" data-href="http://www.index.hu/" data-layout="button_count" style="float: right; padding-top: 30px; padding-left: 10px"></div>
				<div class="fb-login-button" data-max-rows="1" data-size="small" data-show-faces="false" data-auto-logout-link="true" onlogin="checkLoginState();"
					 style="float: right; padding-top: 30px; padding-left: 10px"></div>
					<div id="status"style="float: right; padding-top: 30px">
				</div>
				
			</div>
	
			<form name="ipsearchForm" class="form-inline" ng-controller="ValidateIpController" style="clear: both;">
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
						<span id="validationErrorMessage" class="errorMessage" ng-show="ipsearchForm.ipAddress.$error.pattern">
							Invalid IP-Address.
						</span>
						<span id="resultErrorMessage" class="errorMessage">
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
	 			<div id="map-canvas" style="height:300px; width:500px">
	 			</div>
	 		</div>
		</div>
	</div>
</body>
</html>
