<?php
$kd = $_GET['id_event'];
include("koneksi.php");
$q = mysql_query('SELECT * FROM tbl_event where id_event="'.$kd.'"');
//$q = mysql_query('SELECT * FROM tbl_info where kode="N01"');

$response["data"] = array();
while ($a = mysql_fetch_array($q)){
 $output = array();
 $output["id_event"] = $a["id_event"];
 $output["judul"]=$a["judul"];
 $output["isi_event"]=$a["isi_event"];
 $output["waktu_event"]=$a["waktu_event"];
 $output["tanggal_event"]=$a["tanggal_event"];
 $output["tempat_event"]=$a["tempat_event"];
 
 array_push($response["data"], $output);
}
echo json_encode($response);
?>