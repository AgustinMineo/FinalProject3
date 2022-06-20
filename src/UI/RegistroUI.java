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
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import Interface.GuardaArchivoUsuarios;
import Interface.UserValidationsRegistro;
import Transfers.Transferencia;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;

public class RegistroUI implements UserValidationsRegistro,GuardaArchivoUsuarios{
	private JFrame frame;
	private JTextField textoEmail;
	private JTextField textoNombre;
	private JTextField textoApellido;
	private JTextField textoDNI;
	private JLabel labelErrorEmail = new JLabel("Email Ingresado ya existe o es invalido.");
	private JLabel labelErrorDNI = new JLabel("DNI ingresado ya existe o es invalido");
	private JLabel lblOblig = new JLabel("*");
	private JLabel lblOblig2 = new JLabel("*");
	private JLabel lblOblig3 = new JLabel("*");
	private JLabel lblInfoOblig = new JLabel("(*) Son campos requeridos");
	private JLabel lblOblig4 = new JLabel("*");
	private JLabel labelPasswordIncorrecta = new JLabel("Password deben ser iguales y mayores a 6 caracteres");;
	private JLabel labelErrorEdad = new JLabel("Edad debe ser mayor a 18");
	private JLabel labelContraseña = new JLabel("Ingrese Contrase\u00F1a");
	private JLabel labelContraseña2 = new JLabel("Repita Contrase\u00F1a");
	private JLabel labelEdad = new JLabel("Ingrese Edad");
	private JLabel lblArroba = new JLabel("@");
	private JPasswordField password2 = new JPasswordField();
	private JPasswordField password1 = new JPasswordField("");
	private JSpinner spinnerEdad;
	private JComboBox comboMailProviders = new JComboBox();
	
	private JButton botonRegistrarse = new JButton("REGISTRARSE");

	public RegistroUI(HashMap<String,Usuario>map,List<Transferencia> listaTransferencias) {
		initialize(map,listaTransferencias);
		frame.setVisible(true);
	}

	private void initialize(HashMap<String,Usuario>map,List<Transferencia>listaTransferencias) {
		frame = new JFrame();
		frame.setTitle("Registracion");
		frame.setBounds(475, 275, 580, 310);
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
		
		
		botonRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cont=0;
				if (validaEdad((int)spinnerEdad.getValue())) {
					labelErrorEdad.setVisible(true);
				}
				else {
					labelErrorEdad.setVisible(false);
					cont++;
				}
					
					
				if (validaEmail(map,textoEmail.getText().toString()+"@"+comboMailProviders.getSelectedItem().toString()) || emailValido(textoEmail.getText().toString())) {
					labelErrorEmail.setVisible(true);
				}	
				else {
					labelErrorEmail.setVisible(false);
					cont++;
				}
				
				if (validaDNI(map, textoDNI.getText().toString()) || dniValido(textoDNI.getText().toString())) 
					labelErrorDNI.setVisible(true);
				else {
					labelErrorDNI.setVisible(false);
					cont++;
				}
				
				if (!validaPassword(String.valueOf(password1.getPassword()),String.valueOf(password2.getPassword())))
					labelPasswordIncorrecta.setVisible(true);
				else {
					labelPasswordIncorrecta.setVisible(false);
					cont++;
				}
				
				if (cont==4) {
					String email=textoEmail.getText().toString()+"@"+comboMailProviders.getSelectedItem().toString();
					Usuario nuevo=new Usuario(email,String.valueOf(password1.getPassword()),textoNombre.getText().toString(),textoApellido.getText().toString(),textoDNI.getText().toString());
					map.put(nuevo.getEmail(), nuevo);
					guardaArchivoUsuarios(map);
					MenuUI vent=new MenuUI(nuevo,map,listaTransferencias);
					frame.dispose();
				}
			}
		});
		botonRegistrarse.setBounds(226, 67, 258, 21);
		frame.getContentPane().add(botonRegistrarse);
		
		
		labelErrorEmail.setForeground(Color.RED);
		labelErrorEmail.setBounds(252, 122, 293, 13);
		frame.getContentPane().add(labelErrorEmail);
		
		
		labelErrorDNI.setForeground(Color.RED);
		labelErrorDNI.setBounds(252, 102, 232, 13);
		frame.getContentPane().add(labelErrorDNI);
		
		
		lblOblig.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig.setForeground(Color.RED);
		lblOblig.setBounds(339, 13, 45, 13);
		frame.getContentPane().add(lblOblig);
		
		lblOblig2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig2.setForeground(Color.RED);
		lblOblig2.setBounds(191, 100, 45, 13);
		frame.getContentPane().add(lblOblig2);
		
		lblOblig3.setForeground(Color.RED);
		lblOblig3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig3.setBounds(191, 141, 45, 13);
		frame.getContentPane().add(lblOblig3);
		
		lblInfoOblig.setForeground(Color.RED);
		lblInfoOblig.setBounds(289, 186, 278, 13);
		frame.getContentPane().add(lblInfoOblig);
		
		lblOblig4.setForeground(Color.RED);
		lblOblig4.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOblig4.setBounds(191, 186, 45, 13);
		frame.getContentPane().add(lblOblig4);
		
		
		labelPasswordIncorrecta.setForeground(Color.RED);
		labelPasswordIncorrecta.setBounds(252, 162, 367, 13);
		frame.getContentPane().add(labelPasswordIncorrecta);
		
		JButton botonVolver = new JButton("VOLVER");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BienvenidaUI vent=new BienvenidaUI(map,listaTransferencias);
				vent.setVisible(true);
				frame.dispose();
			}
		});
		botonVolver.setBounds(471, 226, 85, 21);
		frame.getContentPane().add(botonVolver);
		
		comboMailProviders.setBounds(207, 9, 122, 21);
		frame.getContentPane().add(comboMailProviders);
		comboMailProviders.addItem("gmail.com");
		comboMailProviders.addItem("hotmail.com");
		comboMailProviders.addItem("outlook.com");
		comboMailProviders.addItem("yahoo.com");
		
		lblArroba.setBounds(191, 13, 45, 13);
		frame.getContentPane().add(lblArroba);
		
		password1.setToolTipText("");
		password1.setBounds(63, 138, 118, 19);
		frame.getContentPane().add(password1);
		
		labelContraseña.setBounds(63, 126, 134, 13);
		frame.getContentPane().add(labelContraseña);
		
		labelContraseña2.setBounds(63, 170, 134, 13);
		frame.getContentPane().add(labelContraseña2);
		
		password2.setToolTipText("");
		password2.setBounds(63, 183, 118, 19);
		frame.getContentPane().add(password2);
		
		labelEdad.setBounds(63, 212, 118, 13);
		frame.getContentPane().add(labelEdad);
		SpinnerModel value = new SpinnerNumberModel(0,0, 110,1);
		spinnerEdad = new JSpinner(value);
		spinnerEdad.setBounds(63, 227, 54, 20);
		frame.getContentPane().add(spinnerEdad);
		
		labelErrorEdad.setForeground(Color.RED);
		labelErrorEdad.setBounds(252, 142, 258, 13);
		frame.getContentPane().add(labelErrorEdad);
		labelErrorDNI.setVisible(false);
		labelErrorEmail.setVisible(false);
		labelErrorEdad.setVisible(false);
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
	public boolean emailValido(String email) {
		if (email.contains("/") || email.contains("@") || email.contains(" "))
			return true;
		else
			return false;
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
	public boolean validaEdad(int edad) {
		if (edad<18)
			return true;
		else
			return false;
	}

	@Override
	public boolean dniValido(String dni) {
		if (dni.matches(".*[a-z].*") || dni.contains(" "))
			return true;
		else
			return false;
	}
	
	@Override
    public void guardaArchivoUsuarios(HashMap<String,Usuario>map) {
             try {
            	 Writer fileOut=  new OutputStreamWriter(new FileOutputStream("C:\\Users\\lcoluccio\\Desktop\\listaUsuarios.json"),"UTF-8");                 
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