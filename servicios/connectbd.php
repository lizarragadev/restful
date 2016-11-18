<?php 
class DB_Connect {
    function __construct() {
    }
    function __destruct() {
    }
 
    public function connect() {
        require_once 'config.php';
        $con = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD) or die("No se pudo conectar a MySQL");
        $db = mysql_select_db(DB_DATABASE,$con) or die("No se pudo acceder a la base de datos.");
        return $db;
    }
 
    public function close() {
        mysql_close();
    }
}
 
?>