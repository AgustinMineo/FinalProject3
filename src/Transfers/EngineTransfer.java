package Transfers;

import Coins.UtnCoin;
import Enums.TransferCause;
import Usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.HashMap;

public class EngineTransfer {
    private UtnCoin coinUTN;
    private Nodo nodo;
    private Pending pending;

    public EngineTransfer(String emisor, String receptor, double cantidad,Enum<TransferCause>sendCause) {
        coinUTN = new UtnCoin("UTNCOIN",cantidad);
        nodo = new Nodo(emisor,receptor,coinUTN);
        pending = new Pending(LocalDateTime.now(),nodo,sendCause);
    }

	public Pending getPending() {return pending;}

    public HashMap<String,Usuario> descargaCoins(Usuario userEmisor ,HashMap<String,Usuario>map){
        map.get(userEmisor.getEmail()).setUtnCoins(userEmisor.getUtnCoins()-nodo.getCoin().getAmountCoin());
        return map;
    }
}
