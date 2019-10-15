<style type="text/css">
	table, th, td {
		border: 1px solid black;
		padding: 5px 10px;
	}
</style>

<table>
	<tr>
		<th>No</th>
		<th>Time</th>
		<th>Data Type</th>
		<th>Data</th>
	</tr>

<?php

require_once('conn.php');

$sql = "SELECT * FROM sensor_data order by time desc";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
	$i = 1;
	while($row = $result->fetch_assoc()) {
		echo "<tr><td>".$i."</td><td>".$row["time"]."</td><td>".$row["data_type"]."</td><td>".$row["data"]."</td></tr>";
		$i++;
	}
	echo "</table>";
} else {
	echo "0 results";
}
$conn->close();

?>

</table>