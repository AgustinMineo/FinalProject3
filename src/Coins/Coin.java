package Coins;

import java.io.Serializable;

public abstract class Coin implements Serializable{
    protected String nameCoin; /// Nombre de la moneda
    protected double amountCoin; /// El valor en utnCoins de la moneda.

    public Coin(String nameCoin, double amountCoin) {
        this.nameCoin = nameCoin;
        this.amountCoin = amountCoin;
    }

    public String getNameCoin() {return nameCoin;}

    public void setNameCoin(String nameCoin) {this.nameCoin = nameCoin;}

    public double getAmountCoin() {return amountCoin;}

    public void setAmountCoin(double amountCoin) {this.amountCoin = amountCoin;}

}
