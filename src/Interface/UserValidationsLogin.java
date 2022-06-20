package Interface;

import java.util.HashMap;
import java.util.UUID;

import Usuarios.Usuario;

public interface UserValidationsLogin {
	Usuario validaEmail(HashMap<String,Usuario>map,String email);
	boolean validaPassword(Usuario user, String password);
	boolean validaWallet(Usuario user, String wallet);
}

	 
