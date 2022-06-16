package Transfers;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import Enums.TransferCause;

public abstract class Transferencia implements Serializable{
	protected Enum <TransferCause> sendCause;
    protected LocalDateTime fechaDeEnvio;
    protected LocalDateTime fechaDeEstado;
    protected UUID codeIDTransfer;
    protected Nodo nodo;
    

    public Transferencia() {}

    public Transferencia(LocalDateTime fechaDeEnvio, LocalDateTime fechaDeEstado, Nodo nodo, Enum<TransferCause> sendCause) {
        this.fechaDeEnvio = fechaDeEnvio;
        this.fechaDeEstado = fechaDeEstado;
        this.codeIDTransfer = UUID.randomUUID();
        this.nodo = nodo;
        this.sendCause=sendCause;
    }

    public Enum<TransferCause> getSendCause() {return sendCause;}

	public LocalDateTime getFechaDeEnvio() {return fechaDeEnvio;}

    public void setFechaDeEnvio(LocalDateTime fechaDeEnvio) {this.fechaDeEnvio = fechaDeEnvio;}

    public LocalDateTime getFechaDeEstado() {return fechaDeEstado;}

    public void setFechaDeEstado(LocalDateTime fechaDeEstado) {this.fechaDeEstado = fechaDeEstado;}

    public Nodo getNodo() {return nodo;}

    public void setNodo(Nodo nodo) {this.nodo = nodo;}

    public String getCodeIDTransfer() {return codeIDTransfer.toString();}

    public void setCodeIDTransfer(UUID codeIDTransfer) {this.codeIDTransfer = codeIDTransfer;}
}
