package Transfers;

import java.io.Serializable;
import java.time.LocalDateTime;

import Enums.TransferCause;

public final class Finalizada extends Transferencia implements Serializable{
    private LocalDateTime finishDate;

    public Finalizada(LocalDateTime finishDate,LocalDateTime fechaDeEnvio,LocalDateTime fechaDeEstado, Nodo nodo, Enum<TransferCause>sendCause) {
    	super(fechaDeEnvio, fechaDeEstado, nodo, sendCause);
        this.finishDate = finishDate;
    }

    public LocalDateTime getFinishDate() {return finishDate;}

    public void setFinishDate(LocalDateTime finishDate) {this.finishDate = finishDate;}

    @Override
    public String toString() {
        return "Finalizada{" +
                "pending=" + "aca iba pending" +
                ", finishDate=" + finishDate +
                '}';
    }
}
