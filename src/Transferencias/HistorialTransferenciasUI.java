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
import javax.swing.table.DefaultTableModel;

import Interface.GuardaArchivoTransferencias;
import Interface.GuardaArchivoUsuarios;
import Transfers.Finalizada;
import Transfers.Pending;
import Transfers.Transferencia;
import Usuarios.Usuario;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;

public class HistorialTransferenciasUI implements GuardaArchivoTransferencias,GuardaArchivoUsuarios{

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
		frame.setBounds(75, 275, 1201, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Codigo Transferencias Realizadas
		String[] columnNames = {"ID TRANSFERENCIA","FECHA DE TRANSFERENCIA","WALLET RECEPTOR","MONTO","CAUSA ENVIO","ESTADO","PROGRESO/CAUSA RECHAZO"};
	    DefaultTableModel model = new DefaultTableModel(null, columnNames);
	    frame.getContentPane().setLayout(null);
	    JTable table = new JTable(model);
	    table.setBorder(null);
	    table.setFillsViewportHeight(true);
	    JScrollPane scrollPane = new JScrollPane(table);
	    frame.getContentPane().add(scrollPane);
	    scrollPane.setBounds(20, 46, 1123,324 ); 
	    
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
	    			}
	    		if (i.getNodo().getCodeUserSend().equals(user.getCode64())){
 	    			Object o[]=new Object[] {i.getCodeIDTransfer(),i.getFechaDeEnvio(),i.getNodo().getCodeUserReceptor(),i.getNodo().getCoin().getAmountCoin(),i.getSendCause(),estado,progresoSt};
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
	    scrollPane2.setBounds(20, 46, 1123,324 ); 
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
	    			}
	    		if (i.getNodo().getCodeUserReceptor().equals(user.getCode64())){
	    			Object o2[]=new Object[] {i.getCodeIDTransfer(),i.getFechaDeEnvio(),i.getNodo().getCodeUserSend(),i.getNodo().getCoin().getAmountCoin(),i.getSendCause(),estado,progresoSt};
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
	    scrollPane3.setBounds(20, 46, 1123,324 ); 
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
	    		
	    		if (!i.getNodo().getCodeUserReceptor().equals(user.getCode64()) && !i.getNodo().getCodeUserSend().equals(user.getCode64())  && (((Pending) i).getValidations()<3)){
	    			Object o3[]=new Object[] {i.getCodeIDTransfer(),i.getFechaDeEnvio(),i.getNodo().getCodeUserSend(),i.getNodo().getCodeUserReceptor(),"Pendiente",progresoSt};
	    			model3.addRow(o3);
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
	    botonVolver.setBounds(1047, 395, 85, 21);
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
	    
	    
	    radioTransferenciasRecibidas.setBounds(401, 6, 211, 21);
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
	    
	    
	    radioTransfrenciasRealizadas.setBounds(133, 6, 232, 21);
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
	    
	    
	    radioValidarTransacciones.setBounds(655, 6, 275, 21);
	    frame.getContentPane().add(radioValidarTransacciones);
	    
	    labelSeleccioneTransferenciaParaValidar.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    labelSeleccioneTransferenciaParaValidar.setBounds(323, 398, 404, 13);
	    frame.getContentPane().add(labelSeleccioneTransferenciaParaValidar);
	    labelSeleccioneTransferenciaParaValidar.setVisible(false);
	    
	    botonValidar.setVisible(false);
	    botonValidar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (table3.getSelectedRow()==-1) {
	    			ErrorWalletUI error = new ErrorWalletUI(5);
	    			error.setVisible(true);
	    		}
	    		else {
	    		int pos=searchNodo(listaTransferencias,table3.getValueAt(table3.getSelectedRow(), 0).toString());
	    		if (((Pending) listaTransferencias.get(pos)).getValidations()<2) {
	    			((Pending) listaTransferencias.get(pos)).addValidations();
	    			guardaArchivoTransferencias(listaTransferencias);
	    			frame.dispose();
		    		HistorialTransferenciasUI vent=new HistorialTransferenciasUI(user,map,listaTransferencias);
	    			ErrorWalletUI error = new ErrorWalletUI(4);
					error.setVisible(true);
	    		}
	    		else
		    		{
		    			//eliminar nodo de pending en lista transfrencia
	    				//crear instancia de finalizado 
    					//agregar a lista transfrencia la instancia finalizada
	    				//guardar archivo de transfrencias
	    				//dar saldo a cuenta receptora
	    				//guardar archivo de usuarios
	    				Finalizada nueva=new Finalizada(LocalDateTime.now(),listaTransferencias.get(pos).getFechaDeEnvio(),LocalDateTime.now(),listaTransferencias.get(pos).getNodo(),listaTransferencias.get(pos).getSendCause());
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
	    botonValidar.setBounds(670, 395, 199, 21);
	    frame.getContentPane().add(botonValidar);
	}
	
	
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
	         //FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\Agustin\\Desktop\\TP FINAL\\listaTransferencias.ser");
			 FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\lcoluccio\\Desktop\\TP FINAL\\listaTransferencias.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(listaTransferencias);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	     }
		
	}

	@Override
	public void guardaArchivoUsuarios(HashMap<String, Usuario> map) {
		try {
	         //FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\Agustin\\Desktop\\TP FINAL\\listaUsuarios.ser");
			 FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\lcoluccio\\Desktop\\TP FINAL\\listaUsuarios.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(map);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	     }
}
}
