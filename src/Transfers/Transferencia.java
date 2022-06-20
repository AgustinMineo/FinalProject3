package Transfers;

import java.time.LocalDateTime;
import java.util.UUID;

import Enums.TransferCause;

public abstract class Transferencia {
	protected Enum <TransferCause> sendCause;
    protected LocalDateTime fechaDeInicio;
    protected UUID codeIDTransfer;
    protected Nodo nodo;
    
    public Transferencia() {}

    public Transferencia(LocalDateTime fechaDeInicio, Nodo nodo, Enum<TransferCause> sendCause) {
        this.fechaDeInicio = fechaDeInicio;
        this.codeIDTransfer = UUID.randomUUID();
        this.nodo = nodo;
        this.sendCause=sendCause;
    }

    public Enum<TransferCause> getSendCause() {return sendCause;}

	public LocalDateTime getFechaDeInicio() {return fechaDeInicio;}

    public void setFechaDeInicio(LocalDateTime fechaDeInicio) {this.fechaDeInicio = fechaDeInicio;}


    public Nodo getNodo() {return nodo;}

    public void setNodo(Nodo nodo) {this.nodo = nodo;}

    public String getCodeIDTransfer() {return codeIDTransfer.toString();}

    public void setCodeIDTransfer(UUID codeIDTransfer) {this.codeIDTransfer = codeIDTransfer;}
    
    @Override
    public String toString() {
    	return nodo.getCodeUserSend()+"\t"+nodo.getCodeUserReceptor()+"\t"+nodo.getCoin().getAmountCoin()+"\t"+this.getSendCause();
    }
}
