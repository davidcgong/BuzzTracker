<!doctype html>

<?php

// dont let the user come to account.php without logging in first.

  session_start();

  $session_username = "";

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
                <a class="nav-link active" href="#">
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
                <a class="nav-link" href="map.php">
                  <span data-feather="map"></span>
                  Map
                </a>
              </li>
           
              
            </ul>

           
          </div>
        </nav>


        <div id="map"></div>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">Home</h1>
            
          </div>

          <div>
            <h3>Welcome back, <?php echo $_SESSION['username'] ?>!</h3>
            <br>
            <p class="">On this application, there are a few things you can do.</p>

            <ul>
              <li>Look at the list of locations registered with the application (to donate to or manage)</li>
              <li>Add donations to a location (user)</li>
              <li>View items at a location</li>
              <li>View locations on Google Maps for more details</li>
            </ul>

            <p>Click through the sidebar to navigate through the application!</p>


            <br><br>
            <h5>Created by Team 70 B (David Gong, Abhishek Malle, Kyle Evoy, George Jeno, Akib bin Nizam)</h5>

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
    <script>
      var ctx = document.getElementById("myChart");
      var myChart = new Chart(ctx, {
        type: 'line',
        data: {
          labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
          datasets: [{
            data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
            lineTension: 0,
            backgroundColor: 'transparent',
            borderColor: '#007bff',
            borderWidth: 4,
            pointBackgroundColor: '#007bff'
          }]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: false
              }
            }]
          },
          legend: {
            display: false,
          }
        }
      });
    </script>


    <script type="text/javascript">
      document.getElementById("logout").onclick = function() {
        <?php
          // session_destroy();
        ?>
      }
    </script>
  </body>
</html>
