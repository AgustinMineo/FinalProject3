package UI;

import javax.swing.*;

import Interface.GuardaArchivoTransferencias;
import Interface.GuardaArchivoUsuarios;
import Interface.TransferValidations;
import Transfers.EngineTransfer;
import Transfers.Transferencia;
import Usuarios.Usuario;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.awt.event.ActionEvent;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Enums.TransferCause;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.awt.Font;

public class MenuUI implements TransferValidations,GuardaArchivoTransferencias,GuardaArchivoUsuarios {

	private JFrame frame;
	private JTextField textoReceptor;
	private JLabel labelCantCoins = new JLabel("Ingrese monto:");;
	private JLabel labelReceptor= new JLabel("A quien desea transferir?");
	private JLabel labelCausa= new JLabel("Seleccione causa de transferencia");
	private JLabel labelApellido = new JLabel("APELLIDO:");;
	private JLabel labelNombre = new JLabel("NOMBRE:"); ;
	private JButton botonGuardar = new JButton("GUARDAR CAMBIOS");
	private JButton botonRealizarTransferencia= new JButton(">>");
	private JTextField textoNombre;
	private JTextField textoApellido;
	private TransferCause causa = null;
	private JComboBox comboBox;
	private JSpinner spinnerMonto;
	

	public MenuUI(Usuario user,HashMap<String,Usuario> map,List<Transferencia> listaTransferencias) {
		initialize(user,map,listaTransferencias);
		frame.setVisible(true);
	}

	private void initialize(Usuario user,HashMap<String,Usuario> map,List<Transferencia> listaTransferencias) {
		frame = new JFrame();
		frame.setTitle("Menu Principal - Bienvenido: "+user.getCode64());
		frame.setBounds(475, 275, 757, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel labelInfo = new JLabel("Bienvenid@ "+user.getApellido()+", "+user.getNombre());
		labelInfo.setBounds(10, 42, 623, 13);
		frame.getContentPane().add(labelInfo);

		JLabel labelSaldoActual = new JLabel("Saldo Actual: "+user.getUtnCoins());
		labelSaldoActual.setBounds(540, 42, 190, 13);
		frame.getContentPane().add(labelSaldoActual);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 754, 30);
		frame.getContentPane().add(menuBar);
		JMenu preferencias=new JMenu("Preferencias");
		JMenu transferencias=new JMenu("Transferencias");
		JMenuItem verEditar=new JMenuItem("Ver / Editar Perfil");
		verEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoReceptor.setVisible(false);
				labelReceptor.setVisible(false);
				labelCantCoins.setVisible(false);
				botonRealizarTransferencia.setVisible(false);
				labelCausa.setVisible(false);
				comboBox.setVisible(false);
				spinnerMonto.setVisible(false);
				textoNombre.setVisible(true);
				textoApellido.setVisible(true);
				labelNombre.setVisible(true);
				labelApellido.setVisible(true);
				botonGuardar.setVisible(true);
			}
		});
		
		JMenuItem verTransf=new JMenuItem("Ver Historial Transferencias");
		verTransf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				HistorialTransferenciasUI vent=new HistorialTransferenciasUI(user,map,listaTransferencias);
				frame.dispose();
			}
		});
		JMenuItem realizarTransf=new JMenuItem("Realizar una transferencia");
		realizarTransf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoNombre.setVisible(false);
				textoApellido.setVisible(false);
				labelNombre.setVisible(false);
				labelApellido.setVisible(false);
				botonGuardar.setVisible(false);
				textoReceptor.setVisible(true);
				labelReceptor.setVisible(true);
				labelCantCoins.setVisible(true);
				botonRealizarTransferencia.setVisible(true);
				labelCausa.setVisible(true);
				comboBox.setVisible(true);
				spinnerMonto.setVisible(true);
				spinnerMonto.setValue(user.getUtnCoins());

			}
		});
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
		preferencias.add(verEditar);
		preferencias.add(cerrarSesion);
		preferencias.add(salir);
		transferencias.add(verTransf);
		transferencias.add(realizarTransf);
		menuBar.add(preferencias);
		menuBar.add(transferencias);

		textoReceptor = new JTextField();
		textoReceptor.setBounds(36, 153, 257, 19);
		frame.getContentPane().add(textoReceptor);
		textoReceptor.setColumns(10);
		textoReceptor.setVisible(false);

		labelReceptor.setBounds(36, 130, 220, 13);
		frame.getContentPane().add(labelReceptor);
		labelReceptor.setVisible(false);

		
		labelCantCoins.setBounds(33, 179, 203, 13);
		frame.getContentPane().add(labelCantCoins);
		labelCantCoins.setVisible(false);

		comboBox = new JComboBox();
		comboBox.setBounds(33, 253, 96, 21);
		frame.getContentPane().add(comboBox);


		comboBox.addItem(causa.Cuota);
		comboBox.addItem(causa.Haberes);
		comboBox.addItem(causa.AporteDeCapital);
		comboBox.addItem(causa.Facturas);
		comboBox.addItem(causa.Honorarios);
		comboBox.addItem(causa.Prestamos);
		comboBox.addItem(causa.Seguros);
		comboBox.addItem(causa.Varios);

		
		botonRealizarTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateCoins(user,(double)spinnerMonto.getValue())){
					try {
						if (!validateWallet(user.getCode64(), textoReceptor.getText().toString()))
							throw new Exception ();
						else {
							try {
								if(!validaCode64(textoReceptor.getText().toString(),map))
									throw  new Exception();
									else {
									EngineTransfer motor = new EngineTransfer(user.getCode64(),textoReceptor.getText().toString() , (double) spinnerMonto.getValue(),(TransferCause)comboBox.getSelectedItem());
									listaTransferencias.add(motor.getPending());
									guardaArchivoTransferencias(listaTransferencias);
									guardaArchivoUsuarios(motor.descargaCoins(user,map));
									labelSaldoActual.setText("Saldo Actual: "+user.getUtnCoins());
									ErrorWalletUI error = new ErrorWalletUI(2);
									error.setVisible(true);
									
								}
							}catch(NullPointerException e2){
								ErrorWalletUI error=new ErrorWalletUI(1);
								error.setVisible(true);
							}
						}
					}catch (Exception e1){
						ErrorWalletUI error=new ErrorWalletUI(0);
						error.setVisible(true);
					}
				}
			}
		});
		botonRealizarTransferencia.setBounds(203, 201, 53, 21);
		frame.getContentPane().add(botonRealizarTransferencia);
		
		labelCausa.setBounds(33, 235, 278, 13);
		frame.getContentPane().add(labelCausa);

		//spinnerMonto = new JSpinner();
		//new JSpinner(new SpinnerNumberModel(0,0,user.getUtnCoins()));
		SpinnerModel value = new SpinnerNumberModel(0,0, user.getUtnCoins(),0.01);
		spinnerMonto = new JSpinner(value);

		spinnerMonto.setBounds(33, 202, 106, 20);
		frame.getContentPane().add(spinnerMonto);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(141, 127, 80, 19);
		frame.getContentPane().add(textoNombre);
		textoNombre.setColumns(10);
		textoNombre.setVisible(false);
		textoNombre.setText(user.getNombre());
		
		textoApellido = new JTextField();
		textoApellido.setColumns(10);
		textoApellido.setBounds(141, 150, 80, 19);
		frame.getContentPane().add(textoApellido);
		textoApellido.setVisible(false);
		textoApellido.setText(user.getApellido());
		
	
		labelNombre.setBounds(46, 130, 82, 13);
		frame.getContentPane().add(labelNombre);
		
		labelApellido.setBounds(46, 153, 83, 13);
		frame.getContentPane().add(labelApellido);
		
		
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.get(user.getEmail()).setApellido(textoApellido.getText().toString());
				map.get(user.getEmail()).setNombre(textoNombre.getText().toString());
				guardaArchivoUsuarios(map);
				ErrorWalletUI error = new ErrorWalletUI(3);
				error.setVisible(true);
				labelInfo.setText("Bienvenid@ "+user.getApellido()+", "+user.getNombre());
			}
		});
		botonGuardar.setFont(new Font("Tahoma", Font.PLAIN, 8));
		botonGuardar.setBounds(226, 135, 127, 21);
		frame.getContentPane().add(botonGuardar);
		botonRealizarTransferencia.setVisible(false);
		labelCausa.setVisible(false);
		comboBox.setVisible(false);
		spinnerMonto.setVisible(false);
		labelApellido.setVisible(false);
		labelNombre.setVisible(false);
		botonGuardar.setVisible(false);

	}

	@Override
	public boolean validateCoins(Usuario user, double cantidad) {
		if(user.getUtnCoins()>=cantidad)
			return true;
		else
			return false;
	}

	@Override
	public boolean validaCode64(String receptor, HashMap<String,Usuario> map) {
		int i=0;
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext() && i==0) {
			Map.Entry usuario = (Map.Entry) iterator.next();
			if (map.get(usuario.getKey()).getCode64().equals(receptor)) {
				i=1;
			}
		}
		if (i==0)
			return false;
		else
			return true;
	}

	@Override
	public boolean validateWallet(String receptor, String emisor) {
		if (receptor.equals(emisor))
			return false;
		else
			return true;
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
	      } catch (JsonIOException e){
              e.printStackTrace();
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
}
