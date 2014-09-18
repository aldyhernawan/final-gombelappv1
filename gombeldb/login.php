<?php
include "koneksi.php";

$alamat_email    = $_GET["alamat_email"];
$password_user = $_GET["password_user"];

$query = "select * from tbl_user where alamat_email='$alamat_email' and password_user='$password_user' ";

$hasil = mysql_query($query);
if (mysql_num_rows($hasil) > 0) {
$response = array();
$response["login"] = array();
while ($data = mysql_fetch_array($hasil))
{
$h['kode']    		= $data['kode'] ;
$h['alamat_email']  = $data['alamat_email'] ;
$h['password_user'] = $data['password_user'];
$h['nama_user']  	= $data['nama_user'] ;
 array_push($response["login"], $h);
}
	$response["success"] = "1";
	echo json_encode($response);
}
else {
    $response["success"] = "0";
    $response["message"] = "Tidak ada data";
	echo json_encode($response);
}
?>