<?php
$kd = $_GET['kode'];
include("koneksi.php");
$q = mysql_query('SELECT * FROM tbl_info where kode="'.$kd.'"');
//$q = mysql_query('SELECT * FROM tbl_info where kode="N01"');

$response["data"] = array();
while ($a = mysql_fetch_array($q)){
 $output = array();
 $output["kode"] = $a["kode"];
 $output["judul"]=$a["judul"];
 $output["isi"]=$a["isi"];
 $output["waktu"]=$a["waktu"];
 array_push($response["data"], $output);
}
echo json_encode($response);
?>