package Transfers;

import Enums.TransferCause;
import Interface.TransferValidations;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Pending extends Transferencia implements Serializable{
    private int validations;
    private String []userKeys;


    public Pending(LocalDateTime fechaDeInicio, Nodo nodo, Enum<TransferCause>sendCause) {
        super(fechaDeInicio, nodo,sendCause);
        this.validations = 0;
        this.userKeys= new String[3];
    }
    public String[] getUserKeys() {return userKeys;}
    public Pending() {}

    public int getValidations() {return validations;}

    public void setValidations(int validations) {this.validations = validations;}
    
    public void addValidations(String emailUser) {
        userKeys[this.validations]=emailUser;
        this.validations++;
    }
    /// Mejorar el metodo
    public boolean validateUserKeys(String email){
        int i=0;
        while (i<this.validations){
            if(!email.equals(userKeys[i])){
                i++;
            }
            else
                i=5;
        }
        if(i==5){
            return true;
        }else
            return false;
    }
}
