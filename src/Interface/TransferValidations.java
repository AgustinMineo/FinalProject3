package Interface;

import Usuarios.Usuario;

import java.util.HashMap;

import Transfers.Pending;

public interface TransferValidations {
        /// que los utncoins sean mayores a 0
    //void totalCoins();
        /// que tenga mas de 3 usuarios en validaciones (contador tipo short int)
        /// Opcional si agregamos mas monedas (enum de redes de envio) y que valide el tipo de moneda a enviar con el enum. Red UTNCOINS
        /// Que el balance del usuario luego de enviar sea mayor a 0.
        ///
        ///
        ///
    boolean validateCoins(Usuario user, double cantidad);  ///  Tiene que ser mayor a 0 el resto de coins.
    boolean validateWallet(String receptor,String emisor); /// Valida si estoy intentando transferirme a mi mismo
    boolean validaCode64(String receptor, HashMap<String,Usuario> map); //Valida si la wallet existe, si existe retorna User, sino existe retorna null
}
