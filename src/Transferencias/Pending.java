package Transfers;

import Enums.TransferCause;
import Interface.TransferValidations;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pending extends Transferencia implements Serializable{
    private int validations;
    

    public Pending(LocalDateTime fechaDeEnvio, LocalDateTime fechaDeEstado, Nodo nodo,Enum<TransferCause>sendCause) {
        super(fechaDeEnvio, fechaDeEstado, nodo,sendCause);
        this.validations = 0;
    }
    
    public Pending() {}

    public int getValidations() {return validations;}

    public void setValidations(int validations) {this.validations = validations;}
    
    public void addValidations() {this.validations++;}
}
