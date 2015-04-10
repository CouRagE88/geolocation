<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
	<title>Geo-Location Service</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Spring Rest demo application" />

	<script type="text/javascript">
    	// this variable holds the context of the webpplication
    	// will be used in the static javascript to compose the url of the REST service.
    	var webAppContext="<c:url value="/" />";
  	</script>

	<script type="text/javascript" src="<c:url value="scripts/lib/jquery.js" />"></script>
	<script type="text/javascript" src="<c:url value="scripts/lib/jquery.json-2.3.min.js" />"></script>
	
	<script type="text/javascript" src="<c:url value="scripts/search-jquery-json.js" />"></script>

</head>
<body>
	<div class="container">

		<h2>Geo-Location Service!</h2>

		<form>
			<div>
				<label for="ipAddress">Enter your IPv4 Address:</label> <input name="ipAddress" size="10" />
			</div>
			<input type="submit" id="search-button" value="Search" />
			<div id="result" />
		</form>
	</div>
</body>
</html>
