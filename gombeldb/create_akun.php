<?php
if (isset($_POST['alamat_email']) && isset($_POST['password_user']) && isset($_POST['nama_user'])) {
    
    $alamat_email = $_POST['alamat_email'];
    $password_user = $_POST['password_user'];
    $nama_user = $_POST['nama_user'];

    include("koneksi.php");

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO tbl_user (alamat_email, password_user, nama_user, status) VALUES('$alamat_email', '$password_user', '$nama_user', 'client')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "account successfully created.";

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