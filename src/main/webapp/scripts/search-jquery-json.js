/*
 * This script collects the entered form data and sends them to the JSON-based service.
 * From the (JSON) response the result is extracted and displayed.
 * 
 * In this example two AJAX variants are demonstrated:
 * 1) the parameters are passed as part of the url
 * 2) a JSON-formatted object is sent to the server.
 */
$(document).ready(function() {
	
	// this variant populates the parameters as part of the http body in JSON format.
	$("#search-button").click(function(event){

		var ipAddressValue = $('input[name="ipAddress"]').val();
        var requestData = { ipAddress: ipAddressValue};
        
		$.ajax({
			url: webAppContext + "service/add.json/",
			dataType: 'json',
			async: true, /* If set to non-async, browser shows page as "Loading.."*/
			cache: false,
			timeout: 5000,
			type: 'POST',
			data: $.toJSON( requestData ),
			contentType: 'application/json; charset=utf-8',
			
			success: function(response) {
				if (response.status == "OK") {
					$("#result").text("The result is: " + response.cityName);
				} else {
					// TODO: a more appropriate error handling...
					$("#result").text("Please make sure that entered values are numbers.");	
				} 
			},

			statusCode: {
				400: function() {
					$("#result").text("Please make sure that entered values are numbers.");
				},
				404: function() {
					$("#result").text("Please make sure that the service is properly installed and running.");
				}
			},
			
			error: function(XMLHttpRequest, textStatus, errorThrown){
				$("#result").text("Sorry, we are in trouble... Please make sure the service is running.");
			}
		});

		// prevent the 'default' form submission event:
		event.preventDefault();
	});

});
