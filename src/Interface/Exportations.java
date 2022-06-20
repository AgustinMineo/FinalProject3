package Interface;

import java.io.IOException;
import java.util.List;

import Transfers.Transferencia;

public interface Exportations {
	 public void exportacionTxRealizadas(String path,List<Transferencia> listaTransferencias,String codEmisor)throws IOException;
	 public void exportacionTxRecibidas(String path,List<Transferencia> listaTransferencias,String codReceptor)throws IOException;
}
