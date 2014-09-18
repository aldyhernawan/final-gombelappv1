<?php
$kd = $_GET['kode'];
include("koneksi.php");
$q = mysql_query('SELECT * FROM tbl_komentar_info where info_id="'.$kd.'" Order By waktu DESC');

$response["data"] = array();
while ($a = mysql_fetch_array($q)){
 $output = array();
 $output["info_id"] = $a["info_id"];
 $output["komentar"]=$a["komentar"];
 $output["waktu"]=$a["waktu"];
 $output["nama_user"]=$a["nama_user"];
 
 array_push($response["data"], $output);
}
echo json_encode($response);
?>