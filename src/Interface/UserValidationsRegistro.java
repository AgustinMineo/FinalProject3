package Interface;

import java.time.LocalDateTime;
import java.util.HashMap;

import Usuarios.Usuario;

public interface UserValidationsRegistro {
	boolean validaEmail(HashMap<String,Usuario>map,String email);
	boolean emailValido(String email);
	boolean validaDNI(HashMap<String,Usuario>map, String dni);
	boolean validaPassword(String password,String password2);
	boolean validaEdad(int edad);
	
}
