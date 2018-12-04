<!DOCTYPE html>
<?php session_start(); ?>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Donation Tracker</title>

    <!-- Bootstrap core CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/cover.css" rel="stylesheet">
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">Donation Tracker</h3>
              <nav class="nav nav-masthead">
                <a class="nav-link active" href="index.php">Home</a>
                <a id="login" class="nav-link" href="login.php">Login</a>
                <a id="register" class="nav-link" href="register.php">Register</a>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Donation Tracker</h1>
            <p class="lead">Simple and intelligent tracking for your donations.</p>
            <p class="lead">
              <a href="register.php" class="btn btn-lg btn-secondary">Register</a>
              <?php if (isset($_SESSION['username'])) { 
                echo '<a href="account.php" class="btn btn-lg btn-secondary">Continue</a>';
              }
               ?>
            </p>
          </div>

      
              

          <div class="mastfoot">
            <div class="inner">
              <a href=""><p>David Gong, Abhishek Malle, Kyle Evoy, George Jeno, Akib bin Nizam</p></a>
            </div>
          </div>

        </div>

      </div>

    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script type="text/javascript">
      
      $('#login').click(function(){
       $("#form-signin").fadeIn('slow');        
      })

  
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </body>
</html>
