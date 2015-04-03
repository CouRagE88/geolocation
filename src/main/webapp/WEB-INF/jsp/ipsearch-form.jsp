<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <!-- Angular imports-->  
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.6/angular.min.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.6/angular-animate.js"></script>
    
  <!-- Bootstrap imports -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
  
  <script src="<c:url value="/scripts/ipsearch-form.js" />"></script>
  
  <style>
  		.form-control.ng-invalid {
    		color:white;
    		background: #C80000;
  		}
	</style>

    <!-- Add a simple title to the page -->
	<title>IP-based Geo-Location Service</title>
</head>
<body ng-app="geoLocationService">
	
		<!-- Bootstrap main container -->
		<div class="container">
			<!-- Bootstrap jumbotron: grey highlight -->
			<div class="jumbotron">
				<h2>IP-based Geo-Location Service</h2>
				
				<!-- Spring JSP form is displayed which will be presented with the user input.
					 There is a model attribute configured to it: GeoLocationEntity -->
				<form:form class="form-inline" role="form" name="ipsearchForm" commandName="submitForm" method="POST" modelAttribute="geoLocation"
						   acceptCharset="UTF-8" ng-controller="ValidateIpController">
					<!--  arrange elements inside the form -->
					<div class="form-group">
						<label for="ipAddress">Enter your IPv4 Address:</label>
						
						<!-- Input field: styled by bootstrap, validated by AngularJS' ng-pattern -->
						<form:input id="ipAddress" name="ipAddress" class="form-control" path="ipAddress" ng-model="inputIpAddressValue"
									size="15" maxlength="15" placeholder="XXX.XXX.XXX.XXX"
									ng-pattern='/\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b/'/>
					</div>
					<!-- Search button with a search icon on it -->
					<form:button type="submit" name="submit" class="btn btn-info" ng-disabled="ipsearchForm.$invalid">
					<span class="glyphicon glyphicon-search"></span> Search!</form:button>
					
					<div style="padding-left: 180px;">
						<span style="color:#C80000"
							  ng-show="ipsearchForm.ipAddress.$error.pattern"
						 	  >
							Invalid IP-Address.
						</span>
					</div>
				</form:form>
			</div>
		</div>
</body>
</html>