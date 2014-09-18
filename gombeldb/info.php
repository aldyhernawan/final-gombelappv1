<?php
include("koneksi.php");
$q = mysql_query("select * from tbl_info order by waktu ");
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