<?php

$servername = "localhost";
$username = "davidcgong";
$password = "davidcgong";
$dbname = "Donation_Tracker";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 



?>