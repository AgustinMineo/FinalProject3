package Coins;

import java.util.ArrayList;
import java.util.List;

public class CoinBlock{
    private List<Coin> listaCoins =new ArrayList<>();

    private Bitcoin BTC;
    private Cardano ADA;
    private Ethereum ETH;
    private Mana MANA;
    private Ripple XRP;
    private Terra LUNA;
    private TerraVirtua TVK;
    private UtnCoin UTN;

    public CoinBlock() {}

    public List<Coin> getListaCoins() {return listaCoins;}

    public void setListaCoins(List<Coin> listaCoins) {this.listaCoins = listaCoins;}

    public Bitcoin getBTC() {return BTC;}

    public void setBTC(Bitcoin BTC) {this.BTC = BTC;}

    public Cardano getADA() {return ADA;}

    public void setADA(Cardano ADA) {this.ADA = ADA;}

    public Ethereum getETH() {return ETH;}

    public void setETH(Ethereum ETH) {this.ETH = ETH;}

    public Mana getMANA() {return MANA;}

    public void setMANA(Mana MANA) {this.MANA = MANA;}

    public Ripple getXRP() {return XRP;}

    public void setXRP(Ripple XRP) {this.XRP = XRP;}

    public Terra getLUNA() {return LUNA;}

    public void setLUNA(Terra LUNA) {this.LUNA = LUNA;}

    public TerraVirtua getTVK() {return TVK;}

    public void setTVK(TerraVirtua TVK) {this.TVK = TVK;}

    public UtnCoin getUTN() {return UTN;}

    public void setUTN(UtnCoin UTN) {this.UTN = UTN;}

    public void addCoin(Coin coin){listaCoins.add(coin);}

}
