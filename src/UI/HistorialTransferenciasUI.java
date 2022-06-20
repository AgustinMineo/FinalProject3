package UI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import Enums.RejectCause;
import Interface.Exportations;
import Interface.GuardaArchivoTransferencias;
import Interface.GuardaArchivoUsuarios;
import Transfers.Finalizada;
import Transfers.Pending;
import Transfers.Rechazada;
import Transfers.Transferencia;
import Usuarios.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;

public class HistorialTransferenciasUI implements GuardaArchivoTransferencias,GuardaArchivoUsuarios,Exportations{

	private JFrame frame;
	private JRadioButton radioTransferenciasRecibidas = new JRadioButton("Transferencias Recibidas");
	private JRadioButton radioTransfrenciasRealizadas = new JRadioButton("Transferencias Realizadas");
	private JRadioButton radioValidarTransacciones = new JRadioButton("Validar Transferencias");
	private JLabel labelSeleccioneTransferenciaParaValidar = new JLabel("Seleccione una transferencia para validar");
	private JButton botonValidar = new JButton("VALIDAR TRANSFERENCIA");
	private int progreso;
	private String progresoSt;
	private String estado;

	public HistorialTransferenciasUI(Usuario user,HashMap<String,Usuario> map,List<Transferencia>listaTransferencias) {
		initialize(user,map,listaTransferencias);
		frame.setVisible(true);
	}

	private void initialize(Usuario user,HashMap<String,Usuario> map,List<Transferencia>listaTransferencias) {
		frame = new JFrame();
		frame.setTitle("Historial de transacciones de "+user.getCode64());
		frame.setBounds(125, 225, 1201, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1187, 27);
		frame.getContentPane().add(menuBar);
		JMenu preferencias=new JMenu("Preferencias");
		JMenuItem cerrarSesion=new JMenuItem("Cerrar Sesion");
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BienvenidaUI vent=new BienvenidaUI(map,listaTransferencias);
				vent.setVisible(true);
				frame.dispose();
			}
		});
		JMenuItem salir=new JMenuItem("Salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		JMenu exportaciones=new JMenu("Exportar");
		JMenuItem exportarTxRealizadas=new JMenuItem("Exportar transferencias Realizadas");
		exportarTxRealizadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Elegir donde guardar");   
				int userSelection = fileChooser.showSaveDialog(frame);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    String path=fileToSave.getAbsolutePath();
				    try {
						exportacionTxRealizadas(path,listaTransferencias,user.getCode64().toString());
						ErrorWalletUI error = new ErrorWalletUI(6);
		    			error.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
						ErrorWalletUI error = new ErrorWalletUI(7);
		    			error.setVisible(true);
					}
				}
			}
		});
		JMenuItem exportarTxRecibidas=new JMenuItem("Exportar transferencias Recibidas");
		exportarTxRecibidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Elegir donde guardar");   
				int userSelection = fileChooser.showSaveDialog(frame);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    String path=fileToSave.getAbsolutePath();
				    try {
						exportacionTxRecibidas(path,listaTransferencias,user.getCode64().toString());
						ErrorWalletUI error = new ErrorWalletUI(6);
		    			error.setVisible(true);
					} catch (IOException e1) {
						e1.printStackTrace();
						ErrorWalletUI error = new ErrorWalletUI(7);
		    			error.setVisible(true);
					}
				}
			}
		});
		
		menuBar.add(preferencias);
		preferencias.add(cerrarSesion);
		preferencias.add(salir);
		menuBar.add(exportaciones);
		exportaciones.add(exportarTxRealizadas);
		exportaciones.add(exportarTxRecibidas);
		//Codigo Transferencias Realizadas
		String[] columnNames = {"ID TRANSFERENCIA","FECHA DE TRANSFERENCIA","WALLET RECEPTOR","MONTO","CAUSA ENVIO","ESTADO","PROGRESO/CAUSA RECHAZO"};
	    DefaultTableModel model = new DefaultTableModel(null, columnNames);
	    frame.getContentPane().setLayout(null);
	    JTable table = new JTable(model);
	    table.setBorder(null);
	    table.setFillsViewportHeight(true);
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.getContentPane().add(scrollPane);
	    scrollPane.setBounds(20, 85, 1123,324 ); 
	    
	    if (listaTransferencias.size()>0)
	    	for (Transferencia i: listaTransferencias) {
	    		if (i instanceof Pending) {
	    		progreso=((Pending)i).getValidations();
	    		if (progreso==0) {
	    			progresoSt="0%";
	    			estado="Pendiente";
	    		}
	    		else
	    		if (progreso==1) {
	    			progresoSt="33%";
	    			estado="Pendiente";
	    		}
	    		else
	    		if (progreso==2) {
	    			progresoSt="66%";
	    			estado="Pendiente";
	    		}
	    		}
	    		else
	    			if (i instanceof Finalizada) {
	    			 progresoSt="FINALIZADA";
	    			 estado="FINALIZADA";
	    			}else {
						if(i instanceof Rechazada){
							progresoSt=((Rechazada) i).getCause().toString();
							estado= "RECHAZADA";
						}
					}
	    		if (i.getNodo().getCodeUserSend().equals(user.getCode64())){
 	    			Object o[]=new Object[] {i.getCodeIDTransfer(),i.getFechaDeInicio(),i.getNodo().getCodeUserReceptor(),i.getNodo().getCoin().getAmountCoin(),i.getSendCause(),estado,progresoSt};
 	    			model.addRow(o);
 	    		}
	    	}
	    table.setVisible(false);
	    //frame.getContentPane().remove(scrollPane);
	    
	    //Codigo Transferencias Recibidas
	    String[] columnNames2 = {"ID TRANSFERENCIA","FECHA DE TRANSFERENCIA","WALLET EMISOR","MONTO","CAUSA ENVIO","ESTADO","PROGRESO/CAUSA RECHAZO"};
	    DefaultTableModel model2 = new DefaultTableModel(null, columnNames2);
	    frame.getContentPane().setLayout(null);
	    JTable table2 = new JTable(model2);
	    table2.setBorder(null);
	    table2.setFillsViewportHeight(true);
	    JScrollPane scrollPane2 = new JScrollPane(table2);
	    frame.getContentPane().add(scrollPane2);
	    scrollPane2.setBounds(20, 85, 1123,324 ); 
	    if (listaTransferencias.size()>0)
	    	for (Transferencia i: listaTransferencias) {
	    		if (i instanceof Pending) {
	    		progreso=((Pending)i).getValidations();
	    		if (progreso==0) {
	    			progresoSt="0%";
	    			estado="Pendiente";
	    		}
	    		else
	    		if (progreso==1) {
	    			progresoSt="33%";
	    			estado="Pendiente";
	    		}
	    		else
	    		if (progreso==2) {
	    			progresoSt="66%";
	    			estado="Pendiente";
	    		}
	    		}
	    		else
	    			if (i instanceof Finalizada) {
	    				progresoSt="FINALIZADA";
	    				estado="FINALIZADO";
	    			}else {
						if(i instanceof Rechazada){
							progresoSt=((Rechazada) i).getCause().toString();
							estado= "RECHAZADA";
						}
					}

	    		if (i.getNodo().getCodeUserReceptor().equals(user.getCode64())){
	    			Object o2[]=new Object[] {i.getCodeIDTransfer(),i.getFechaDeInicio(),i.getNodo().getCodeUserSend(),i.getNodo().getCoin().getAmountCoin(),i.getSendCause(),estado,progresoSt};
	    			model2.addRow(o2);
	    		}
	    	}
	    table2.setVisible(false);
	   
	    //Codigo Transferencias a Validar
	    String[] columnNames3 = {"ID TRANSFERENCIA","FECHA DE TRANSFERENCIA","WALLET EMISOR","WALLET RECEPTOR","ESTADO","PROGRESO/CAUSA RECHAZO"};
	    DefaultTableModel model3 = new DefaultTableModel(null, columnNames3);
	    frame.getContentPane().setLayout(null);
	    JTable table3 = new JTable(model3);
	    table3.setBorder(null);
	    table3.setFillsViewportHeight(true);
	    JScrollPane scrollPane3 = new JScrollPane(table3);
	    frame.getContentPane().add(scrollPane3);
	    scrollPane3.setBounds(20, 85, 1123,324 ); 
	    if (listaTransferencias.size()>0)
	    	for (Transferencia i: listaTransferencias) {
	    		if (i instanceof Pending) {
	    		progreso=((Pending)i).getValidations();
	    		if (progreso==0) 
	    			progresoSt="0%";
	    		else
	    		if (progreso==1)
	    			progresoSt="33%";
	    		else
	    		if (progreso==2)
	    			progresoSt="66%";
				if(!((Pending)i).validateUserKeys(user.getEmail())){
	    			if (!i.getNodo().getCodeUserReceptor().equals(user.getCode64()) && !i.getNodo().getCodeUserSend().equals(user.getCode64())  && (((Pending) i).getValidations()<3)){
	    			Object o3[]=new Object[] {i.getCodeIDTransfer(),i.getFechaDeInicio(),i.getNodo().getCodeUserSend(),i.getNodo().getCodeUserReceptor(),"Pendiente",progresoSt};
	    			model3.addRow(o3);
	    			}
				}
	    	}
	    	}
		table3.setVisible(false);
		
	    
	    ButtonGroup grupoRadios = new ButtonGroup();
	    grupoRadios.add(radioValidarTransacciones);
	    grupoRadios.add(radioTransfrenciasRealizadas);
	    grupoRadios.add(radioTransferenciasRecibidas);
	    
	    JButton botonVolver = new JButton("VOLVER");
	    botonVolver.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		frame.dispose();
	    		MenuUI vent=new MenuUI(user,map,listaTransferencias);
	    	}
	    });
	    botonVolver.setBounds(1047, 442, 85, 21);
	    frame.getContentPane().add(botonVolver);
	    radioTransferenciasRecibidas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		scrollPane.setVisible(false);
	    		table.setVisible(false);
	    		scrollPane2.setVisible(true);
	    		table2.setVisible(true);
	    		scrollPane3.setVisible(false);
	    		table3.setVisible(false);
	    		labelSeleccioneTransferenciaParaValidar.setVisible(false);
	    		botonValidar.setVisible(false);
	    	}
	    });
	    
	    
	    radioTransferenciasRecibidas.setBounds(403, 44, 211, 21);
	    frame.getContentPane().add(radioTransferenciasRecibidas);
	    radioTransfrenciasRealizadas.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		scrollPane.setVisible(true);
	    		table.setVisible(true);
	    		scrollPane2.setVisible(false);
	    		table2.setVisible(false);
	    		scrollPane3.setVisible(false);
	    		table3.setVisible(false);	  
	    		labelSeleccioneTransferenciaParaValidar.setVisible(false);
	    		botonValidar.setVisible(false);
	    	}
	    });

	    radioTransfrenciasRealizadas.setBounds(135, 44, 232, 21);
	    frame.getContentPane().add(radioTransfrenciasRealizadas);
	    radioValidarTransacciones.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		scrollPane.setVisible(false);
	    		table.setVisible(false);
	    		scrollPane2.setVisible(false);
	    		table2.setVisible(false);
	    		scrollPane3.setVisible(true);
	    		table3.setVisible(true);
	    		labelSeleccioneTransferenciaParaValidar.setVisible(true);
	    		botonValidar.setVisible(true);
	    		}
	    });
	    
	    
	    radioValidarTransacciones.setBounds(657, 44, 275, 21);
	    frame.getContentPane().add(radioValidarTransacciones);
	    
	    labelSeleccioneTransferenciaParaValidar.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    labelSeleccioneTransferenciaParaValidar.setBounds(323, 445, 404, 13);
	    frame.getContentPane().add(labelSeleccioneTransferenciaParaValidar);
	    labelSeleccioneTransferenciaParaValidar.setVisible(false);
	    
	    botonValidar.setVisible(false);
		/// Boton validar transferencia
	    botonValidar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (table3.getSelectedRow()==-1) {
	    			ErrorWalletUI error = new ErrorWalletUI(5);
	    			error.setVisible(true);
	    		}
	    		else {
	    		int pos=searchNodo(listaTransferencias,table3.getValueAt(table3.getSelectedRow(), 0).toString());
	    		if (((Pending) listaTransferencias.get(pos)).getValidations()<2) {
					//String [] aux = ((Pending) listaTransferencias.get(pos)).getUserKeys());
					if(((Pending) listaTransferencias.get(pos)).validateUserKeys(user.getEmail())) {
						System.out.println("Error, usuario ya valido la transferencia");
					}else{

						if(((Pending) listaTransferencias.get(pos)).getFechaDeInicio().isAfter(LocalDateTime.now().minusMinutes(1))){
	    			((Pending) listaTransferencias.get(pos)).addValidations(user.getEmail());
	    			guardaArchivoTransferencias(listaTransferencias);
	    			frame.dispose();
		    		HistorialTransferenciasUI vent=new HistorialTransferenciasUI(user,map,listaTransferencias);
	    			ErrorWalletUI error = new ErrorWalletUI(4);
					error.setVisible(true);
						} else {
							Rechazada rechazo = new Rechazada(((Pending) listaTransferencias.get(pos)).getFechaDeInicio(),listaTransferencias.get(pos).getNodo(),listaTransferencias.get(pos).getSendCause(),RejectCause.timeOut,((Pending) listaTransferencias.get(pos)),LocalDateTime.now());
							listaTransferencias.add(rechazo);
							listaTransferencias.remove(pos);
							guardaArchivoTransferencias(listaTransferencias);
							frame.dispose();
							HistorialTransferenciasUI vent=new HistorialTransferenciasUI(user,map,listaTransferencias);
						}

					}
	    		}
	    		else
		    		{
	    				Finalizada nueva=new Finalizada(LocalDateTime.now(),listaTransferencias.get(pos).getFechaDeInicio(),listaTransferencias.get(pos).getNodo(),listaTransferencias.get(pos).getSendCause());
	    				listaTransferencias.remove(pos);
	    				listaTransferencias.add(nueva);
	    				guardaArchivoTransferencias(listaTransferencias);
	    				map.get(searchUser(map,table3.getValueAt(table3.getSelectedRow(), 3).toString())).agregaSaldo(nueva.getNodo().getCoin().getAmountCoin());
	    				guardaArchivoUsuarios(map);
	    				frame.dispose();
			    		HistorialTransferenciasUI vent=new HistorialTransferenciasUI(user,map,listaTransferencias);
	    				ErrorWalletUI error = new ErrorWalletUI(4);
						error.setVisible(true);
		    		}
	    		}
	    	}
	    });
	    botonValidar.setBounds(670, 442, 199, 21);
	    frame.getContentPane().add(botonValidar);
	}
	
	/// Migrar a transferValidations
	public int searchNodo(List<Transferencia> listaTransferencias, String codeTransfer) {
        int i = 0;
        int pos=-1;
        while (listaTransferencias.size()>i || pos==-1) {
                if(codeTransfer.equals(listaTransferencias.get(i).getCodeIDTransfer().toString())){
                   pos=i;
                }
                i++;
        }
        return pos;
    }
	public String searchUser(HashMap<String,Usuario>map,String codeUser) {
		int i=0;
		String key= new String();
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext() && i==0) {
			Map.Entry usuario = (Map.Entry) iterator.next();
			if (map.get(usuario.getKey()).getCode64().equals(codeUser)) 
				key=map.get(usuario.getKey()).getEmail();
		}
		return key;
	}

	@Override
	public void guardaArchivoTransferencias(List<Transferencia> listaTransferencias) {
		try {
	         //FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\Agustin\\Desktop\\TP FINAL LAST\\TP FINAL\\listaTransferencias.ser");
			 FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\lcoluccio\\Desktop\\GIT\\FinalProject3\\listaTransferencias.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(listaTransferencias);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	     }
		
	}

	@Override
    public void guardaArchivoUsuarios(HashMap<String,Usuario>map) {
             try {
            	 Writer fileOut=  new OutputStreamWriter(new FileOutputStream("C:\\Users\\lcoluccio\\Desktop\\GIT\\FinalProject3\\listaUsuarios.json"),"UTF-8");                 
            	 //Writer fileOut=  new OutputStreamWriter(new FileOutputStream("C:\\Users\\Agustin\\Documents\\GitHub\\FinalProject3\\listaUsuarios.json"),"UTF-8");
				 Gson gson = new GsonBuilder().setPrettyPrinting().create();
				 String gsonString = gson.toJson(map);
				 fileOut.write(gsonString);
				 fileOut.flush();
				 fileOut.close();
              } catch (IOException i) {
                 i.printStackTrace();
             } catch (JsonIOException e){
                 e.printStackTrace();
             }
    }

	@Override
	public void exportacionTxRealizadas(String path,List<Transferencia> listaTransferencias,String codEmisor) throws IOException {
		File archivo;
		//Consulto si el usuario digito .txt, ya que sino, agrego la extension.
		if (path.contains(".txt"))
		   archivo = new File(path);
		else
			archivo= new File(path+".txt");
		    archivo.createNewFile();

		try {
			FileWriter writer = new FileWriter(archivo);
			//Hearders de exportacion
			writer.write("Emisor \t Receptor \t Cantidad \t Causa Envio \t Fecha Y Hora \t Estado");
			writer.write(System.getProperty( "line.separator" ));
			for (Transferencia i:listaTransferencias) {
				if (i.getNodo().getCodeUserSend().equals(codEmisor)) {
					if (i instanceof Pending) 
						estado="Pendiente";
					else if (i instanceof Finalizada)
						estado="Finalizada";
					else if (i instanceof Rechazada)
						estado="Rechazada";
					writer.write(i.toString()+"\t"+i.getFechaDeInicio()+"\t"+estado);
					writer.write(System.getProperty( "line.separator" ));
				}
			}
			writer.close();	   
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	   }

	@Override
	public void exportacionTxRecibidas(String path, List<Transferencia> listaTransferencias, String codReceptor)throws IOException {
		File archivo;
		String estado = new String();
		//Consulto si el usuario digito .txt, ya que sino, agrego la extension.
		if (path.contains(".txt"))
		   archivo = new File(path);
		else
			archivo= new File(path+".txt");
		   archivo.createNewFile();

		try {
			FileWriter writer = new FileWriter(archivo);
			//Hearders de exportacion
			writer.write("Emisor \t Receptor \t Cantidad \t Causa Envio \t Fecha Y Hora \t Estado");
			writer.write(System.getProperty( "line.separator" ));
			for (Transferencia i:listaTransferencias) {
				if (i.getNodo().getCodeUserReceptor().equals(codReceptor)) {
					if (i instanceof Pending) 
						estado="Pendiente";
					else if (i instanceof Finalizada)
						estado="Finalizada";
					else if (i instanceof Rechazada)
						estado="Rechazada";
					writer.write(i.toString()+"\t"+i.getFechaDeInicio()+"\t"+estado);
					writer.write(System.getProperty( "line.separator" ));
				}
			}
			writer.close();	   
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
