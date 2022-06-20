package Transfers;

import Enums.RejectCause;
import Enums.TransferCause;
import java.time.LocalDateTime;

public final class Rechazada extends Transferencia{
    Enum<RejectCause> cause;
    private Pending pendingReject;
    private LocalDateTime rejectTime;

    public Rechazada(LocalDateTime fechaDeInicio, Nodo nodo, Enum<TransferCause> sendCause, Enum<RejectCause> cause, Pending pendingReject, LocalDateTime rejectTime) {
        super(fechaDeInicio, nodo, sendCause);
        this.cause = cause;
        this.pendingReject = pendingReject;
        this.rejectTime = rejectTime;
    }

    public Enum<RejectCause> getCause() {return cause;}

    public void setCause(Enum<RejectCause> cause) {this.cause = cause;}

    public Pending getPendingReject() {return pendingReject;}

    public void setPendingReject(Pending pendingReject) {this.pendingReject = pendingReject;}

    public LocalDateTime getRejectTime() {return rejectTime;}

    public void setRejectTime(LocalDateTime rejectTime) {this.rejectTime = rejectTime;}


}
