package Transferencias;

import java.time.LocalDateTime;

public abstract class Transferencia {
    protected LocalDateTime fechaDeEnvio;
    protected LocalDateTime fechaDeEstado;
    protected Nodo nodo;
}
