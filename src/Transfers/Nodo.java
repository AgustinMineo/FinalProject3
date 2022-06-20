package Transfers;

import Coins.UtnCoin;
import Usuarios.Usuario;

import java.io.Serializable;
import java.util.UUID;

public class Nodo implements Serializable{
    private String codeUserSend;
    private String codeUserReceptor;
    //private Object coin;  Implementar multiples coins
    private UtnCoin coin;
    private int status;

    public Nodo(String codeUserSend, String codeUserReceptor, UtnCoin coin) {
        this.codeUserSend = codeUserSend;
        this.codeUserReceptor = codeUserReceptor;
        this.coin = coin;
        this.status=1;
    }

    public int getStatus() {return status;}

    public void setStatus(int status) {this.status = status;}

    public String getCodeUserSend() {return codeUserSend.toString();}

    public String getCodeUserReceptor() {return codeUserReceptor.toString();}

    public UtnCoin getCoin() {return coin;}

    public void setCoin(UtnCoin coin) {this.coin = coin;}

    public boolean validaSaldo(Usuario user){
        if(user.getUtnCoins()>=coin.getAmountCoin()){
            return true;
        }else
            return false;
    }
}
