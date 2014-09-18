<?php
if (isset($_POST['judul']) && isset($_POST['isi_saran'])&& isset($_POST['nama_user'])) {
    
    $judul = $_POST['judul'];
    $isi_saran = $_POST['isi_saran'];
	$nama_user = $_POST['nama_user'];
	
	include("koneksi.php");
    // mysql inserting a new row
	
    $result = mysql_query("INSERT INTO tbl_saran (judul, isi_saran, nama_user) VALUES('$judul', '$isi_saran', '$nama_user')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "saran successfully created.";

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