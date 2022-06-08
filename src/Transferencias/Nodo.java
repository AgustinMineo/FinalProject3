package Transferencias;

import java.util.UUID;

public class Nodo {
    private UUID codeUserSend;
    private UUID codeUserReceptor;
    private double utnCoins;


    public Nodo(UUID codeUserSend, UUID codeUserReceptor, double utnCoins) {
        this.codeUserSend = codeUserSend;
        this.codeUserReceptor = codeUserReceptor;
        this.utnCoins = utnCoins;
    }

    public UUID getCodeUserSend() {return codeUserSend;}

    public void setCodeUserSend(UUID codeUserSend) {this.codeUserSend = codeUserSend;}

    public UUID getCodeUserReceptor() {return codeUserReceptor;}

    public void setCodeUserReceptor(UUID codeUserReceptor) {this.codeUserReceptor = codeUserReceptor;}

    public double getUtnCoins() {return utnCoins;}

    public void setUtnCoins(double utnCoins) {this.utnCoins = utnCoins;}

}
