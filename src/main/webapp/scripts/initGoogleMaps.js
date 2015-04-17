var map;

function initialize(latitude, longitude) {
				
  var mapOptions = {
    zoom: 8,
    center: new google.maps.LatLng(latitude,longitude)
  };
  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
}