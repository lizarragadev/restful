<?php

$nombres = $_POST['nombres'];
$apellidos = $_POST['apellidos'];
$carnet = $_POST['carnet'];
$correo = $_POST['correo'];
$usuario = $_POST['usuario'];
$contrasenia = $_POST['contrasenia'];

require_once 'funciones_bd.php';
$db = new funciones_BD();
	if($db->isUserExist($usuario)){
		echo json_encode(array('status_add'=>'0'));	
	}else{
		$resAddUser = $db->addUser($usuario, $contrasenia);
		if($resAddUser != "") {
			$resAddPerson = $db->addPerson($resAddUser, $nombres, $apellidos, $carnet, $correo);
			echo json_encode(array('status_add'=>'1'));
		}else{
			echo json_encode(array('status_add'=>'-1'));
		}		
	}?>

