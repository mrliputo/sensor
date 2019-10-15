<?php

require_once('conn.php');

$data_type = $_POST['data_type'];
$data = $_POST['data'];
$query = "insert into sensor_data (time, data_type, data) values (NOW(), '$data_type', '$data')";

if($conn->query($query)) {
	echo "Insert successful";
}else{
	echo "Error: " . $query . "<br>";
}

$conn->close();