package Interface;

import java.util.HashMap;
import java.util.UUID;

import Usuarios.Usuario;

public interface UserValidationsLogin {
    
    /// correo exista
    /// ingrese un minimo de 2 caracteres en el nombre
    /// ingrese un minimo de 2 caracteres en el apellido
    /// ingrese un dni
    /// ingrese una password de mas de 8 caracteres


    /// que la password sea igual (login)
    /// que el code64 sea igual
    /// que el correo sea igual
	
	Usuario validaEmail(HashMap<String,Usuario>map,String email);
	boolean validaPassword(Usuario user, String password);
	boolean validaWallet(Usuario user, String wallet);
}

	 
