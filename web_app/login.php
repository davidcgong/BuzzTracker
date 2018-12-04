<!DOCTYPE html>

<?php
  include("db/db.php");
  // query for the username

  $errors = "";

  if (isset($_POST['login_username']) && isset($_POST['login_password'])) {

      $username = mysqli_real_escape_string($conn, $_POST['login_username']);
      $password = mysqli_real_escape_string($conn, $_POST['login_password']);
      
      $sql = "SELECT username, password FROM users WHERE username='$username' AND password='$password'";
      $loginQuery = mysqli_query($conn, $sql) or trigger_error("Query Failed! SQL: $sql - Error: ".mysqli_error(db_conx), E_USER_ERROR);;
      if (mysqli_num_rows($loginQuery) > 0) {
        session_start();
        $_SESSION['username'] = $username;
        header('Location: account.php');
      } else {
        $errors .= "<div class='alert alert-danger'>One of your account credentials was wrong. Please try again.</div>";
      }

  } else {
    $errors .= "<div class='alert alert-danger'>Please fill out all fields.</div>";
  }

  // change to submit instead
  if (!isset($_POST['login_submit'])) {
      $errors = "";
  }



?>




<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Login | Donation Tracker</title>

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
                <a class="nav-link" href="index.php">Home</a>
                <a id="login" class="nav-link active" href="login.php">Login</a>
                <a id="register" class="nav-link" href="register.php">Register</a>
              </nav>
            </div>
          </div>

     

      
         <form method="post" class="form-signin">
           <h2 class="form-signin-heading">Sign In</h2>
           <?php echo $errors; ?>
           <label for="inputEmail" class="sr-only">Username</label>
           <input type="name" name="login_username" id="inputEmail" class="form-control" placeholder="Username" autofocus>
           <label for="inputPassword"  class="sr-only">Password</label>
           <input type="password" name="login_password" id="inputPassword" class="form-control" placeholder="Password" required>
           <button name="login_submit" class="btn btn-lg btn-primary btn-block" type="submit" value="submit">Login</button>
         </form>
              

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
