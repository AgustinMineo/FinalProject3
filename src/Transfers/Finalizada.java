package Transfers;

import java.time.LocalDateTime;

import Enums.TransferCause;

public final class Finalizada extends Transferencia{
    private LocalDateTime finishDate;

    public Finalizada(LocalDateTime finishDate,LocalDateTime fechaDeInicio, Nodo nodo, Enum<TransferCause>sendCause) {
    	super(fechaDeInicio, nodo, sendCause);
        this.finishDate = finishDate;
    }

    public LocalDateTime getFinishDate() {return finishDate;}

    public void setFinishDate(LocalDateTime finishDate) {this.finishDate = finishDate;}

    @Override
    public String toString() {
        return super.toString();
    }
}