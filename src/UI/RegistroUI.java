package UI;

import java.awt.EventQueue;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import Usuarios.Usuario;
import javax.swing.JTextField;

import Interface.GuardaArchivoUsuarios;
import Interface.UserValidationsRegistro;
import Transfers.Transferencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;


public class RegistroUI implements UserValidationsRegistro,GuardaArchivoUsuarios{

	private JFrame frame;
	private JTextField textoEmail;
	private JTextField textoNombre;
	private JTextField textoApellido;
	private JTextField textoDNI;
	private JTextField textoPassword;
	private JLabel labelErrorEmail;
	private JLabel labelErrorDNI;
	private JLabel lblOblig;
	private JLabel lblOblig2;
	private JLabel lblOblig3;
	private JLabel lblInfoOblig;
	private JTextField textoPassword2;
	private JLabel lblOblig4;
	private JLabel labelPasswordIncorrecta;

	public RegistroUI(HashMap<String,Usuario>map,List<Transferencia> listaTransferencias) {
		initialize(map,listaTransferencias);
		frame.setVisible(true);
	}

	private void initialize(HashMap<String,Usuario>map,List<Transferencia>listaTransferencias) {
		frame = new JFrame();
		frame.setTitle("Registracion");
		frame.setBounds(475, 275, 580, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textoEmail = new JTextField();
		textoEmail.setText("Ingrese Email");
		textoEmail.setBounds(63, 10, 118, 19);
		frame.getContentPane().add(textoEmail);
		textoEmail.setColumns(10);
		
		textoNombre = new JTextField();
		textoNombre.setText("Ingrese Nombre");
		textoNombre.setColumns(10);
		textoNombre.setBounds(63, 39, 118, 19);
		frame.getContentPane().add(textoNombre);
		
		textoApellido = new JTextField();
		textoApellido.setText("Ingrese Apellido");
		textoApellido.setColumns(10);
		textoApellido.setBounds(63, 68, 118, 19);
		frame.getContentPane().add(textoApellido);
		
		textoDNI = new JTextField();
		textoDNI.setText("Ingrese DNI");
		textoDNI.setColumns(10);
		textoDNI.setBounds(63, 97, 118, 19);
		frame.getContentPane().add(textoDNI);
		
		textoPassword = new JTextField();
		textoPassword.setText("Ingrese Password");
		textoPassword.setColumns(10);
		textoPassword.setBounds(63, 126, 118, 19);
		frame.getContentPane().add(textoPassword);
		
		JButton botonRegistrarse = new JButton("REGISTRARSE");
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cont=0;
				if (validaEmail(map,textoEmail.getText().toString())) 
					labelErrorEmail.setVisible(true);		
				else {
					labelErrorEmail.setVisible(false);
					cont++;
				}
				if (validaDNI(map, textoDNI.getText().toString())) 
					labelErrorDNI.setVisible(true);
				else {
					labelErrorDNI.setVisible(false);
					cont++;
				}
				if (!validaPassword(textoPassword.getText().toString(),textoPassword2.getText().toString()))
					labelPasswordIncorrecta.setVisible(true);
				else {
					labelPasswordIncorrecta.setVisible(false);
					cont++;
				}
				if (cont==3) {
					Usuario nuevo=new Usuario(textoEmail.getText().toString(),textoPassword.getText().toString(),textoNombre.getText().toString(),textoApellido.getText().toString(),textoDNI.getText().toString(),LocalDateTime.now());
					map.put(nuevo.getEmail(), nuevo);

					guardaArchivoUsuarios(map);
					MenuUI vent=new MenuUI(nuevo,map,listaTransferencias);
					frame.dispose();
				}
			}
		});
		botonRegistrarse.setBounds(226, 67, 258, 21);
		frame.getContentPane().add(botonRegistrarse);
		
		labelErrorEmail = new JLabel("EMAIL ingresado ya existe");
		labelErrorEmail.setForeground(Color.RED);
		labelErrorEmail.setBounds(263, 126, 293, 13);
		frame.getContentPane().add(labelErrorEmail);
		
		labelErrorDNI = new JLabel("DNI ingresado ya existe");
		labelErrorDNI.setForeground(Color.RED);
		labelErrorDNI.setBounds(263, 100, 157, 13);
		frame.getContentPane().add(labelErrorDNI);
		
		lblOblig = new JLabel("*");
		lblOblig.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig.setForeground(Color.RED);
		lblOblig.setBounds(191, 13, 45, 13);
		frame.getContentPane().add(lblOblig);
		
		lblOblig2 = new JLabel("*");
		lblOblig2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig2.setForeground(Color.RED);
		lblOblig2.setBounds(191, 100, 45, 13);
		frame.getContentPane().add(lblOblig2);
		
		lblOblig3 = new JLabel("*");
		lblOblig3.setForeground(Color.RED);
		lblOblig3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig3.setBounds(191, 129, 45, 13);
		frame.getContentPane().add(lblOblig3);
		
		lblInfoOblig = new JLabel("(*) Son campos requeridos");
		lblInfoOblig.setForeground(Color.RED);
		lblInfoOblig.setBounds(178, 183, 278, 13);
		frame.getContentPane().add(lblInfoOblig);
		
		textoPassword2 = new JTextField();
		textoPassword2.setText("Repita Password");
		textoPassword2.setColumns(10);
		textoPassword2.setBounds(63, 154, 118, 19);
		frame.getContentPane().add(textoPassword2);
		
		lblOblig4 = new JLabel("*");
		lblOblig4.setForeground(Color.RED);
		lblOblig4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig4.setBounds(191, 152, 45, 13);
		frame.getContentPane().add(lblOblig4);
		
		labelPasswordIncorrecta = new JLabel("Password deben ser iguales y mayores a 6 caracteres");
		labelPasswordIncorrecta.setForeground(Color.RED);
		labelPasswordIncorrecta.setBounds(226, 160, 330, 13);
		frame.getContentPane().add(labelPasswordIncorrecta);
		
		JButton botonVolver = new JButton("VOLVER");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BienvenidaUI vent=new BienvenidaUI(map,listaTransferencias);
				vent.setVisible(true);
				frame.dispose();
			}
		});
		botonVolver.setBounds(453, 179, 85, 21);
		frame.getContentPane().add(botonVolver);
		labelErrorDNI.setVisible(false);
		labelErrorEmail.setVisible(false);
		labelPasswordIncorrecta.setVisible(false);

		/*Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				
			}
		}));*/
	}

	@Override
	public boolean validaEmail(HashMap<String,Usuario> map, String email) {
		int i=0;
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext() && i==0) {
			Map.Entry usuario = (Map.Entry) iterator.next();
			if (usuario.getKey().equals(email)) 
				i=1;
		}
		if (i==0)
			return false;
		else
			return true;
	}
	@Override
	public boolean validaDNI(HashMap<String,Usuario> map, String dni) {
		int i=0;
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext() && i==0) {
			Map.Entry usuario = (Map.Entry) iterator.next();
			if (map.get(usuario.getKey()).getDni().equals(dni))
				i=1;
		}
		if (i==0)
			return false;
		else
			return true;
	}

	@Override
	public boolean validaPassword(String password,String password2) {
		if (password.length()>6 && password2.length()>6) {
			if (password2.equals(password))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public void guardaArchivoUsuarios(HashMap<String,Usuario>map) {
			 try {
		         FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\Agustin\\Desktop\\Cambios\\TP FINAL\\listaUsuarios.json");
				 //FileOutputStream fileOut=  new FileOutputStream("C:\\Users\\lcoluccio\\Desktop\\TP FINAL\\listaUsuarios.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
				 Gson gson = new GsonBuilder().setPrettyPrinting().create();
				 String gsonString = gson.toJson(map);
		         out.writeObject(gsonString);

				 //fileOut.flush();
		         //out.close();
		         //fileOut.close();
				/* JSONObject jsonObject = new JSONObject();
				 jsonObject.put(user.getEmail(),user.toString());
				 FileWriter file = new FileWriter("C:\Users\lcoluccio\Desktop\TP FINAL\listaUsuarios.json");
				 file.write(jsonObject.toJSONString());
				 file.close();*/
		      } catch (IOException i) {
		         i.printStackTrace();
		     } catch (JsonIOException e){
				 e.printStackTrace();
			 }
	}
}


