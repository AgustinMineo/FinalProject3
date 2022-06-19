package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Interface.UserValidationsLogin;
import Transfers.Transferencia;
import Usuarios.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

public class LoginUI implements UserValidationsLogin {

	private JFrame frame;
	private JTextField textoEmail;
	private JTextField textoWallet;
	private JTextField textoPassword;
	private JLabel labelErrorEmail;
	private JLabel labelErrorwalletOpw;
	private JButton botonRegistro;

	public LoginUI(HashMap<String,Usuario>map,List<Transferencia> listaTransferencias) {
		initialize(map,listaTransferencias);
		frame.setVisible(true);
	}

	private void initialize(HashMap<String,Usuario>map,List<Transferencia> listaTransferencias) {  
		frame = new JFrame();
		frame.setBounds(475, 275, 650, 264);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Login - Blockchain v1.0");
		textoEmail = new JTextField();
		textoEmail.setText("Ingrese Email");
		textoEmail.setBounds(135, 24, 96, 19);
		frame.getContentPane().add(textoEmail);
		textoEmail.setColumns(10);
		
		
		JButton botonLogin = new JButton("LOGIN");
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario user=validaEmail(map,textoEmail.getText().toString());
				if(validaPassword(user,textoPassword.getText().toString()) && validaWallet(user,textoWallet.getText().toString())){
					MenuUI ventana=new MenuUI(user,map,listaTransferencias);
					frame.dispose();
				}else{
					labelErrorwalletOpw.setVisible(true);
				}
			}
		});
		botonLogin.setBounds(271, 85, 134, 21);
		frame.getContentPane().add(botonLogin);
		botonLogin.setVisible(false);
		
		JButton botonVerificaEmail = new JButton("VERIFICAR EMAIL");
		botonVerificaEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaEmail(map,textoEmail.getText().toString())!=null) {
					textoEmail.setEnabled(false);
					textoWallet.setVisible(true);
					textoPassword.setVisible(true);
					labelErrorEmail.setVisible(false);
					botonLogin.setVisible(true);
					botonVerificaEmail.setVisible(false);
				}
				else
					labelErrorEmail.setVisible(true);
			}
		});
		botonVerificaEmail.setBounds(253, 23, 241, 21);
		frame.getContentPane().add(botonVerificaEmail);
		
		textoWallet = new JTextField();
		textoWallet.setText("Ingrese Wallet");
		textoWallet.setColumns(10);
		textoWallet.setBounds(135, 55, 280, 19);
		frame.getContentPane().add(textoWallet);
		textoWallet.setVisible(false);
		
		textoPassword = new JTextField();
		textoPassword.setText("Ingrese Password");
		textoPassword.setColumns(10);
		textoPassword.setBounds(135, 86, 123, 19);
		frame.getContentPane().add(textoPassword);
		textoPassword.setVisible(false);
		
		labelErrorEmail = new JLabel("Email ingresado no existe.");
		labelErrorEmail.setForeground(Color.RED);
		labelErrorEmail.setFont(new Font("Tahoma", Font.ITALIC, 10));
		labelErrorEmail.setBounds(167, 132, 322, 13);
		frame.getContentPane().add(labelErrorEmail);
		
		labelErrorwalletOpw= new JLabel("Wallet o Password ingresada es incorrecta.");
		labelErrorwalletOpw.setForeground(Color.RED);
		labelErrorwalletOpw.setFont(new Font("Tahoma", Font.ITALIC, 10));
		labelErrorwalletOpw.setBounds(167, 132, 384, 13);
		frame.getContentPane().add(labelErrorwalletOpw);
		
		botonRegistro = new JButton("REGISTRARSE");
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroUI vent=new RegistroUI(map,listaTransferencias);
				frame.dispose();
			}
		});
		botonRegistro.setBounds(459, 196, 144, 21);
		frame.getContentPane().add(botonRegistro);
		
		labelErrorwalletOpw.setVisible(false);
		labelErrorEmail.setVisible(false);
	}

	@Override
	public Usuario validaEmail(HashMap<String,Usuario>map,String email) {
		Usuario user = new Usuario();
		int i=0;
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext() && i==0) {
			Map.Entry usuario = (Map.Entry) iterator.next();
			if (usuario.getKey().equals(email)) {
				user = map.get(usuario.getKey());
				i=1;
			}
		}
		if (i==0)
			return null;
		else
			return user;
	}

	@Override
	public boolean validaPassword(Usuario user, String password) {
		if(password.equals(user.getPassword()))
			return true;
			else
		return false;
	}

	@Override
	public boolean validaWallet(Usuario user, String wallet) {
		if(wallet.equals(user.getCode64()))
			return true;
		else
		return false;
	}
}