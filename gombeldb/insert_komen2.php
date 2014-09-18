<?php
if (isset($_POST['info_id']) && isset($_POST['komentar'])&& isset($_POST['nama_user'])) {
    
    $kode = $_POST['info_id'];
    $komentar = $_POST['komentar'];
	$nama_user = $_POST['nama_user'];
	include("koneksi.php");
    // mysql inserting a new row
	
    $result = mysql_query("INSERT INTO tbl_komentar_info (info_id, komentar, nama_user) VALUES('$kode', '$komentar', '$nama_user')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Komentar successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>