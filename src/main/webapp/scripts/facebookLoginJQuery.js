$(document).ready(function() {
	
	$.ajaxSetup({ cache: true });
  	$.getScript('//connect.facebook.net/en_US/sdk.js', function() {
    	
  		FB.init({
  			appId      : '961883970498028',
  		    cookie     : true,  // enable cookies to allow the server to access the session
  		    xfbml      : true,  // parse social plugins on this page
  		    version    : 'v2.3' // or v2.2. v2.1 ...
    	});
    	
    	//$('#loginbutton').removeAttr('disabled');
    	//FB.getLoginStatus(response);
  		
  		FB.getLoginStatus(function(response) {
  			statusChangeCallback(response);
  		});
  		
  	  //This is called with the results from from FB.getLoginStatus().
  	  function statusChangeCallback(response) {
  		  console.log('statusChangeCallback');
  	  	  console.log(response);
  	  	  // The response object is returned with a status field that lets the app know the current login status of the person.
  	  	  // Full docs on the response object can be found in the documentation for FB.getLoginStatus().
  	  	  if (response.status === 'connected') {
  	  	    // Logged into your app and Facebook.
  	  	    testAPI();
  	  	  } else if (response.status === 'not_authorized') {
  	  	    // The person is logged into Facebook, but not your app.
  	  	    document.getElementById('status').innerHTML = 'Please log into this app.';
  	  	  } else {
  	  	    // The person is not logged into Facebook, so we're not sure if they are logged into this app or not.
  	  	    document.getElementById('status').innerHTML = 'Please log into Facebook.';
  	  	  }
  	  }

  	  // Here we run a very simple test of the Graph API after login is
  	  // successful.  See statusChangeCallback() for when this call is made.
  	  function testAPI() {
  		  console.log('Welcome!  Fetching your information.... ');
  		  FB.api('/me', function(response) {
  			  console.log('Successful login for: ' + response.name);
  			  document.getElementById('status').innerHTML = 'Thanks for logging in, ' + response.name + '!';
  		  });
  	  }
  		
  	  //This function is called when someone finishes with the Login
  	  //Button.  See the onlogin handler attached to it in the sample
  	  //code below.
  	  function checkLoginState() {
  	  	FB.getLoginStatus(function(response) {
  	  		statusChangeCallback(response);
  	  	});
  	  }
	});
});