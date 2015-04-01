<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.6/angular.min.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.6/angular-animate.js"></script>
  
  <link rel="stylesheet" href="css/pages/common/ui-elements.css">
  <link rel="stylesheet" href="css/pages/common/shadow.css">
  <link rel="stylesheet" href="css/pages/ipsearch-form.css">
  <link rel="stylesheet" href="css/jquery-ui/smoothness/jquery-ui-1.8.23.custom.css">

  <link href="http://fonts.googleapis.com/css?family=Allan:bold" rel="stylesheet" type="text/css">
  <link href="http://fonts.googleapis.com/css?family=Actor" rel="stylesheet" type='text/css'>

  
  <script src="<c:url value="/scripts/ipsearch-form.js" />"></script>

    <!-- Add a simple title to the page -->
	<title>IP-based Geo-Location Service</title>
</head>
<body ng-app="geoLocationService">
	
	<style>
  		.inputIpAddress.ng-invalid {
    		color:white;
    		background: red;
  		}
	</style>
	
		<div class="container">
		<div class="inputbox drop-shadow curved curved-vt-2">
			<p>
				IP-based Geo-Location Service
			</p>
		<!-- Spring JSP form is displayed which will be presented with the user input.
			 There is a model attribute configured to it: GeoLocationEntity -->
		
		<form:form name="ipsearchForm" commandName="submitForm" method="POST" modelAttribute="geoLocation"
				   acceptCharset="UTF-8" ng-controller="ValidateIpController">
				   
			Enter your IPv4 Address:
			
			<form:input path="ipAddress" ng-model="inputIpAddressValue"
						 ng-pattern='/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/' 
						 name="anim" class="inputIpAddress" size="15" maxlength="15" placeholder="xxx.xxx.xxx.xxx"/>
						 
			<form:button name="submit" class="button" ng-disabled="ipsearchForm.$invalid">Search!</form:button>
		</form:form>
</body>
</html>