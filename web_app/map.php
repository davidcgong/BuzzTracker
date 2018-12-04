<!DOCTYPE html >
	<?php 

	session_start(); 

	include("db/db.php");

	$session_username = "";
	if (isset($_SESSION['username'])) {
		$session_username = $_SESSION['username'];
	}

	// Start XML file, create parent node

	$dom = new DOMDocument("1.0");
	$node = $dom->createElement("markers");
	$parnode = $dom->appendChild($node);

	// Opens a connection to a MySQL server


	// Set the active MySQL database

	// Select all the rows in the markers table

	$query = "SELECT * FROM locations WHERE 1";
	$result = mysqli_query($conn, $query);

	if (!$result) {
	  die('Invalid query: ' . mysql_error());
	}

	$locations = array();
	while ($row = @mysqli_fetch_assoc($result)) {
	  $locationID = $row['location_id'];
	  $name = $row['name'];
	  $latitude = $row['latitude'];
	  $longitude = $row['longitude'];
	  $address = $row['address'];
	  $city = $row['city'];
	  $state = $row['state'];
	  $zip = $row['zip'];
	  $type = $row['type'];
	  $phone = $row['phone'];
	  $website = $row['website'];
	  $locations[] = array('name' => $name, 'lat' => $latitude, 'lng' => $longitude);
	}
	$markers = json_encode($locations);

	?>
  <head>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<title>Using MySQL and PHP with Google Maps</title>
	<style>
	  /* Always set the map height explicitly to define the size of the div
	   * element that contains the map. */
	  #map {
		height: 100%;
	  }
	  /* Optional: Makes the sample page fill the window. */
	  html, body {
		height: 100%;
		margin: 0;
		padding: 0;
	  }
	</style>
	<!-- Bootstrap core CSS -->
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="css/dashboard.css" rel="stylesheet">
  </head>

  <body>

  	<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Hi <?php echo $session_username; 
       ?>!</a>
      <input class="form-control form-control-dark w-100" name="logout" type="text" placeholder="Search" aria-label="Search">
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a id="logout" class="nav-link" href="index.php">Log Out</a>
        </li>
      </ul>
    </nav>
  

	<nav class="col-md-2 d-none d-md-block bg-light sidebar">
		  <div class="sidebar-sticky">
			<ul class="nav flex-column">
			  <li class="nav-item">
				<a class="nav-link" href="account.php">
				  <span data-feather="home"></span>
				  Home <span class="sr-only">(current)</span>
				</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="locations.php">
				  <span data-feather="flag"></span>
				  Locations
				</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link active" href="map.php">
				  <span data-feather="map"></span>
				  Map
				</a>
			  </li>
			 

		  
		  </div>
		</nav>
		

	<div id="map"></div>
  </div></div>

	<script>
	  var customLabel = {
		restaurant: {
		  label: 'R'
		},
		bar: {
		  label: 'B'
		}
	  };

		function initMap() {
				var atl = new google.maps.LatLng(33.75, -84.386);
						var myOptions = {
							zoom: 10,
							center: atl,
							mapTypeId: google.maps.MapTypeId.ROADMAP,
							mapTypeControl: false
						};

						var map = new google.maps.Map(document.getElementById("map"),myOptions);
						var markerData = <?php echo $markers; ?>;

							var locations = [
						    ['KEEP NORTH FULTON BEAUTIFUL', 33.96921, -84.3688, '(770) 555 - 7321', 'www.knfb.org'],
						    ['PAVILION OF HOPE INC', 33.80129, -84.25537, '(404) 555 - 8765', 'www.pavhope.org'],
						    ['AFD Station 4', 33.75416, -84.37742, '(404) 555 - 3456', 'www.afd04.atl.ga'],
						    ['D&D CONVENIENCE STORE', 33.71747, -84.2521000000, '(404) 555 - 9876', 'www.ddconv.com'],
						    ['PATHWAY UPPER ROOM CHRISTIAN MINISTRIES', 33.70866, -84.41853, '(404) 555 - 5432', 'www.pathways.org'],
						    ['BOYS & GILRS CLUB W.W. WOOLFOLK', 33.73182, -84.43971, '(404) 555 - 1234', 'www.bgc.wool.ga']
						  ];
						  var marker, i, content;

				

						  for (i = 0; i < locations.length; i++) {

						  	  

						  	  content = '<div id="content">'+
						            '<div id="siteNotice">'+
						            '</div>'+
						            '<h3 id="firstHeading" class="firstHeading">'+locations[i][0]+'</h3>'+
						            '<div id="bodyContent">'+
						            '<p>'+ locations[i][3] +'<br>'
						            	+ locations[i][4]
						            '</p>'+
						           
						            '</div>'+
						      
						            '</div>';

						  

						      marker = new google.maps.Marker({
						          position: new google.maps.LatLng(locations[i][1], locations[i][2]),
						          map: map,
						          title: locations[i]['name']
						      });
						        var infowindow = new google.maps.InfoWindow()

						      google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){ 
						              return function() {
						                 infowindow.setContent(content);
						                 infowindow.open(map,marker);
						              };
						          })(marker,content,infowindow)); 
						  }
		}



	  function downloadUrl(url, callback) {
		var request = window.ActiveXObject ?
			new ActiveXObject('Microsoft.XMLHTTP') :
			new XMLHttpRequest;

		request.onreadystatechange = function() {
		  if (request.readyState == 4) {
			request.onreadystatechange = doNothing;
			callback(request, request.status);
		  }
		};

		request.open('GET', url, true);
		request.send(null);
	  }

	  function doNothing() {}
	</script>
	<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBnZHH6-PSm9ETR1Xszp8bVuZ43RpmPEU&callback=initMap">
	</script>
	<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
	  feather.replace()
	</script>
  </body>
</html>