<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <link rel="stylesheet" href="css/common/ui-elements.css">

  <!-- JS ip-address check: quite n00bish, it shan't be used -->
  <script type="text/javascript">
	  function validateIPaddress() {
	  	var ipformat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;

	  	var input = document.forms["form"]["submitForm"].value;
	  		if(input.match(ipformat)) {
	  		document.form1.text1.focus();
	  		return true;
	  	} else {
	  		alert("You have entered an invalid IP address!");  
	  		document.form1.text1.focus();
	  		return false;
	  	}  
	  }
  </script>
    <!-- Add a simple title to the page -->
	<title>IP-based Geo-Location Service</title>
</head>
<body>
	<div id="main">
		<!-- Spring JSP form is displayed which will be presented with the user input.
			 There is a model attribute configured to it: GeoLocationEntity -->
		<form:form id="form" modelAttribute="geoLocation">	<!-- onsubmit="return validateIPaddress()"> -->
			Enter your IPv4 Address: 
			<form:input
				id="submitForm" 
				name="submitForm"
				path="ipAddress" 
				size="50" 
				maxlength="15" 
				placeholder="xxx.xxx.xxx.xxx" 
				required="required"
			/>
			<input type="submit" value="Save" id="ipaddress"/>
		</form:form>
	</div>
</body>
</html>