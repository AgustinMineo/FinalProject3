package Transfers;

import Enums.RejectCause;

import java.io.Serializable;
import java.time.LocalDateTime;

public final class Rechazada implements Serializable{
    Enum<RejectCause> cause;
    private Pending pendingReject;
    private LocalDateTime rejectTime;

    public Rechazada(Enum<RejectCause> cause, Pending pendingReject, LocalDateTime rejectTime) {
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
