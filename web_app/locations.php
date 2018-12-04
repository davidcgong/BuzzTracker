<!doctype html>

<?php
	include("db/db.php");
// dont let the user come to account.php without logging in first.

  session_start();

  $session_username = "";
  $errors = "";
  $itemTable = "";
  $table = "";

  // basically means the user probably never logged in
  if (!isset($_SESSION['username'])) {
	  header("Location: index.php");
  }
  
  if (isset($_POST['logout'])) {
	header("Location: index.php");
  }
  if (isset($_SESSION['username'])) {
	 $session_username = $_SESSION['username'];
  }


  if (isset($_GET['locationID'])) {
	  $locationID = $_GET['locationID'];
	  $query = "SELECT * FROM locations where location_id = '$locationID'";
	  $result = mysqli_query($conn, $query);
	  $row = @mysqli_fetch_assoc($result);
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

      // get all entries for one location

      $query = "SELECT * FROM item WHERE location_id = '$locationID' ORDER BY timestamp DESC";
      $result = mysqli_query($conn, $query);
      while ($row = @mysqli_fetch_assoc($result)) {

      $timestamp = $row['timestamp'];
      $shortDescription = $row['short_description'];
      $longDescription = $row['long_description'];
      $value = $row['value'];
      $category = $row['category'];

      $itemTable .= "

        <tr>
        
            <td>".$timestamp."</td>
            <td>".$category."</td>
            <td>".$value."</td>
            <td>".$shortDescription."</td>
            <td>".$longDescription."</td>
            
        </tr>

      ";
     }



       // check if user tried to add item


     if (isset($_POST['post_short_description']) && isset($_POST['post_long_description']) && isset($_POST['post_value']) && isset($_POST['post_category'])) {
         if (isset($_POST['post_add_item'])) {
           $shortDescription = mysqli_real_escape_string($conn, $_POST['post_short_description']);
           $longDescription = mysqli_real_escape_string($conn, $_POST['post_long_description']);
           $value = mysqli_real_escape_string($conn, $_POST['post_value']);
           $category = mysqli_real_escape_string($conn, $_POST['post_category']);
           $locationID = $_GET['locationID'];

           $usernameQuery = $conn->query("INSERT INTO item (location_id, location_name, short_description, long_description, value, category) VALUES ('$locationID', '$name', '$shortDescription', '$longDescription', '$value', '$category')");
           //reload after insertion

         }
         header('Refresh: 0');
     } 

  } else {
	  // this is kind of available on the map regardless
	  $query = "SELECT * FROM locations WHERE 1 ORDER BY location_id ASC";
	  $result = mysqli_query($conn, $query);


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
		$table .= "

		
		<tr>
		
			<td><a href='?locationID=$locationID'>".$locationID."</a></td>
			<td><a href='?locationID=$locationID'>".$name."</a></td>
			<td><a href='?locationID=$locationID'>".$city."</a></td>
			<td><a href='?locationID=$locationID'>".$state."</a></td>
			<td><a href='?locationID=$locationID'>".$zip."</a></td>
			<td><a href='?locationID=$locationID'>".$type."</a></td>
			<td><a href='?locationID=$locationID'>".$phone."</a></td>
			
		</tr>

	  ";
  }
  }



  


?>


<html lang="en">
  <head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../../../../favicon.ico">

	<style type="text/css">
	  #map {
		height: 100%;
	  }

	</style>

	<title><?php echo $session_username ?> | Donation_Tracker</title>

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

	<div class="container-fluid">
	  <div class="row">
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
				<a class="nav-link active" href="#">
				  <span data-feather="flag"></span>
				  Locations
				</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="map.php">
				  <span data-feather="map"></span>
				  Map
				</a>
			  </li>
			
		   

		   
		  </div>
		</nav>


		<div id="map"></div>

		<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Locations</h1>
		 
		  </div>

	  <?php if (isset($_GET['locationID'])) { ?>
		<a href="locations.php">View all Locations</a>
		<div class="panel panel-default">
					<?php echo $errors ?>
					<div class="panel-body text-center">
						<h4><?php echo $name ?></h4>
						<div>
						<?php echo $address ?><br />
						<?php echo $city ?><br />
						<?php echo $phone ?><br />
						<?php echo $website ?><br />
						</div>
						<hr />
						<div id="map1" class="map">
						</div>
						<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
				  Add Item
				</button>
					</div>

		</div>

        <table style="margin-top: 2vh" class="table table-striped table-sm">
            <thead>
                <tr>
                    <th>Timestamp</th>
                    <th>Category</th>
                    <th>Value</th>
                    <th>Short Description</th>
                    <th>Long Description</th>
                </tr>
            </thead>
            <tbody>
                <?php echo $itemTable ?>
            </tbody>
            
        </table>

				
	  <?php } ?>

  
  <?php if (!isset($_GET['locationID'])) { ?>
		  <div class="table-responsive">

			<table class="table table-striped table-sm">
			  <thead>
				<tr>
				  <th>Location ID</th>
				  <th>Location Name</th>
				 <!--  <th>Latitude</th>
				  <th>Longitude</th> -->
			   <!--    <th>Address</th> -->
				  <th>City</th>
				  <th>State</th>
				  <th>ZIP</th>
				  <th>Location Type</th>
				  <th>Phone</th>
				<!--   <th>Website</th> -->
				</tr>
			  </thead>
			  <tbody>
				<?php if (!isset($_GET['locationID'])) echo $table; ?>
			  </tbody>
			</table>
		  </div>
		<?php } ?>
		</main>
	  </div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<h5 class="modal-title" id="exampleModalLongTitle">Add Item</h5>
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			  <span aria-hidden="true">&times;</span>
			</button>
		  </div>
		  <div class="modal-body">
			
			<form method="post">

			  <?php echo $errors ?>

			  <div class="form-group">
				<label for="exampleFormControlTextarea1">Short description of item</label>
				<textarea name="post_short_description" class="form-control" id="exampleFormControlTextarea1" placeholder="Ex. Seat warmer" rows="1" required></textarea>
			  </div>


			  <div class="form-group">
				<label for="exampleFormControlTextarea1">Long description of item</label>
				<textarea name="post_long_description" class="form-control" placeholder="Ex. Seat warmer that can heat up to 200 degrees. Best used for the elderly" id="exampleFormControlTextarea1" rows="3" required></textarea>
			  </div>


			  <div class="form-group">
				<label for="exampleFormControlTextarea1">Estimated value ($)</label>
				<textarea name="post_value" class="form-control" id="exampleFormControlTextarea1" placeholder="10.00" rows="1" required></textarea>
			  </div>
		   


			  <div class="form-group">
				<label for="exampleFormControlSelect1">Category</label>
				<select name="post_category" class="form-control" id="exampleFormControlSelect1">
				  <option>N/A</option>
				  <option>Clothing</option>
				  <option>Hat</option>
				  <option>Kitchen</option>
				  <option>Electronics</option>
				  <option>Household</option>
				  <option>Other</option>
				</select>
			  </div>
			
		  


		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		  
			  <input class='btn btn-primary' type="submit" name="post_add_item" value="Save changes">
		 
		  </div>

			 </form>
		</div>
	  </div>
	</div>

	

   

	<!-- Bootstrap core JavaScript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
	<script src="../../assets/js/vendor/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

	<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
	  feather.replace()
	</script>

	<!-- Graphs -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
  

	<script type="text/javascript">
	  document.getElementById("logout").onclick = function() {
		<?php
		  // session_destroy();
		?>
	  }
	</script>
  </body>
</html>
