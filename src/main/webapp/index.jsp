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
	 
	<script type="text/javascript">
    	// this variable holds the context of the webpplication
    	// will be used in the static javascript to compose the url of the REST service.
    	var webAppContext="<c:url value="/" />";
  	</script>
	  	
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
	<script type="text/javascript" src="<c:url value="scripts/search-jquery-json.js" />"></script>
	<script src="<c:url value="scripts/ipsearch-form.js" />"></script>

</head>
<body ng-app="geoLocationService">

	<div id="fb-root"></div>
	<script>
	(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/hu_HU/sdk.js#xfbml=1&version=v2.3&appId=482074371939840";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
	</script>

	<div class="container">
		
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
				<div id="result" class="errorMessage" />
			</form>
		</div>
		
		<div class="fb-like"
		 data-href="http://localhost:8080/bench/" 
		 data-layout="button_count" 
		 data-action="recommend" 
		 data-show-faces="true" 
		 data-share="true">
		</div>
		
	</div>
</body>
</html>
