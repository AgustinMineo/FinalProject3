package Coins;

import java.io.Serializable;

public abstract class Coin implements Serializable{
    protected String nameCoin; /// Nombre de la moneda
   /* protected String About; /// Descripcion de la moneda
    protected double MarketCap; /// multiplicar la cantidad de coins por el valor de un dolar (200)
    protected double Volume;  //// Volumen en las ultimas 24hs (Sumatoria de transfernecias en un periodo de tiempo)
    protected double CirculationSupply;/// Cantidad de coins en circulación
    protected long TotalSupply; /// La cantidad total de monedas que se van a crear. 1 millon
    protected double priceUSD; /// El valor en USD de la moneda.*/
    protected double amountCoin; /// El valor en utnCoins de la moneda.

    public Coin(String nameCoin, double amountCoin) {
        this.nameCoin = nameCoin;
        this.amountCoin = amountCoin;
    }

    public String getNameCoin() {return nameCoin;}

    public void setNameCoin(String nameCoin) {this.nameCoin = nameCoin;}

    public double getAmountCoin() {return amountCoin;}

    public void setAmountCoin(double amountCoin) {this.amountCoin = amountCoin;}
    /*
    public Coin(String nameCoin, String about, double marketCap, double volume, double circulationSupply, long totalSupply, double priceUSD, double priceUTN) {
        this.nameCoin = nameCoin;
        About = about;
        MarketCap = marketCap;
        Volume = volume;
        CirculationSupply = circulationSupply;
        TotalSupply = totalSupply;
        this.priceUSD = priceUSD;
        this.priceUTN = priceUTN;
    }

    public String getNameCoin() {
        return nameCoin;
    }

    public void setNameCoin(String nameCoin) {
        this.nameCoin = nameCoin;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public double getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(double marketCap) {
        MarketCap = marketCap;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public double getCirculationSupply() {
        return CirculationSupply;
    }

    public void setCirculationSupply(double circulationSupply) {
        CirculationSupply = circulationSupply;
    }

    public long getTotalSupply() {
        return TotalSupply;
    }

    public void setTotalSupply(long totalSupply) {
        TotalSupply = totalSupply;
    }

    public double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public double getPriceUTN() {
        return priceUTN;
    }

    public void setPriceUTN(double priceUTN) {
        this.priceUTN = priceUTN;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "nameCoin='" + nameCoin + '\'' +
                ", About='" + About + '\'' +
                ", MarketCap=" + MarketCap +
                ", Volume=" + Volume +
                ", CirculationSupply=" + CirculationSupply +
                ", TotalSupply=" + TotalSupply +
                ", priceUSD=" + priceUSD +
                ", priceUTN=" + priceUTN +
                '}';
    }*/

}
