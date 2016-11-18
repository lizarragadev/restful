<?php
 
class funciones_BD {
    private $db;
    
    function __construct() {
        require_once 'connectbd.php';
        $this->db = new DB_Connect();
        $this->db->connect();
    }
 	
 	function __destruct() {
 
    }
    
    public function getPersona($id){
    	$result = mysql_query("SELECT * FROM persona WHERE id = $id");
        return $result;
    } 
   	
 
   public function login($user,$pass){
	$result=mysql_query("SELECT * FROM usuario WHERE usuario='$user' AND contrasenia='".md5($pass)."' "); 
	return $result;
   }
 
 
   public function isUserExist($username) {
        $result = mysql_query("SELECT usuario from user WHERE usuario = '$username'");
        $num_rows = mysql_num_rows($result);
        if ($num_rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public function addPerson($id, $nombres, $apellidos, $carnet, $correo) {
        $result = mysql_query("INSERT INTO persona(id_user, apellidos, nombres, ci, correo) VALUES('$id', '$nombres', '$apellidos', '$carnet','$correo')") or die (mysql_error());
        if ($result) {
            return true;
        } else {
            return "";
        }
    }

   public function addUser($username, $password) {
	$encodepass = md5($password);
        $result = mysql_query("INSERT INTO usuario(usuario, contrasenia) VALUES('$username', '$encodepass')");
    	if ($result) {
            return mysql_insert_id();
        } else {
            return false;
        }
    }
  
}
 
?>