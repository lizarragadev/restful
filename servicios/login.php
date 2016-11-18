<?php
class objeto{
	var $status;
	var $id;
	var $usuario;
	var $nombres;
	var $apellidos;
	var $ci;
	var $correo;
}

if($_SERVER['REQUEST_METHOD']=='POST') {
 	$user = $_POST['usuario'];
	$pass = $_POST['contrasenia'];
	require_once 'funciones_bd.php';
	$db = new funciones_BD();		
	$result = $db->login($user,$pass);
	$arr = array();

	if(mysql_num_rows($result)>0){
		while($row=mysql_fetch_row($result)){
			$obj = new objeto();
			$obj->status="success";
			$obj->id=$row[0];
			$obj->usuario=$row[1];
			$us=$db->getPersona($row[0]);
			if(mysql_num_rows($us)>0){
				while($ro=mysql_fetch_row($us)){
					$obj->nombres= $ro[2];
					$obj->apellidos= $ro[3];
					$obj->ci= $ro[4];
					$obj->correo= $ro[5];
				}
			}
			$aux = json_encode(array('user'=>$obj));
		}
	}else{
		$obj = new objeto();
		$obj->status="error";
		$obj->id="";
		$obj->usuario="";
		$obj->nombres="";
		$obj->apellidos="";
		$obj->ci="";
		$obj->correo="";
		$aux = json_encode(array('user'=>$obj));
	}
	echo $aux;
}else{
	echo "error";
} 

?>