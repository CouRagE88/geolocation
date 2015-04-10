/*
 * This script collects the entered form data and sends them to the JSON-based service.
 * From the (JSON) response the result is extracted and displayed.
 */
$(document).ready(function() {
	
	$("#search-button").click(function(event){

		var ipAddressValue = $('input[name="ipAddress"]').val();
        var requestData = { ipAddress: ipAddressValue};
  	        
		$.ajax({
			url: webAppContext + "service/search.json/",
			dataType: 'json',
			async: true,
			cache: false,
			timeout: 5000,
			type: 'POST',
			data: $.toJSON( requestData ),
			contentType: 'application/json; charset=utf-8',
			
			success: function(response) {
				if (response.status == "OK") {
					$("#result").text("The result is: " + response.cityName);
				} else if (response.status == "LOCATION_DOES_NOT_EXIST_EXCEPTION") {
					$("#result").text("Sorry, the specified IP-Address is not yet mapped to any location around the globe.");	
				} else if (response.status == "INVALID_IPADDRESS_EXCEPTION") {
					$("#result").text("IP-based localization could not be performed due to invalid IP Address.");	
				} else {
					$("#result").text("TODO - weird error");
				} 
			},

			statusCode: {
				404: function() {
					$("#result").text("Please make sure that the service is properly installed and running.");
				}
			},
			
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				$("#result").text("Oops, something went wrong! Our service is currently unavailable, please check back later!");
			}
		});
		event.preventDefault();
	});

});
