<?php
include("koneksi.php");  

$q = mysql_query("select * from tbl_saran order by waktu ");
$response["data"] = array();
while ($a = mysql_fetch_array($q)){
 $output = array();
 $output["id_saran"] = $a["id_saran"];
 $output["judul"]=$a["judul"];
 $output["isi_saran"]=$a["isi_saran"];
 $output["waktu"]=$a["waktu"];
 $output["nama_user"]=$a["nama_user"];
 array_push($response["data"], $output);
}
echo json_encode($response);
?>