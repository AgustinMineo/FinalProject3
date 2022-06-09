package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Transfers.Transferencia;
import Usuarios.Usuario;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.awt.event.ActionEvent;

public class BienvenidaUI extends JDialog {

	public BienvenidaUI(HashMap<String,Usuario>map,List<Transferencia> listaTransferencias) {
		setTitle("Bienvenido a Blockchain - v1.0");
		setBounds(475, 275, 432, 203);
		getContentPane().setLayout(null);
		
		JButton botonLogin = new JButton("LOGIN");
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI vent=new LoginUI(map,listaTransferencias);
				dispose();
			}
		});
		botonLogin.setBounds(0, 0, 211, 166);
		getContentPane().add(botonLogin);
		
		JButton botonRegistro = new JButton("REGISTRARSE");
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroUI vent=new RegistroUI(map,listaTransferencias);
				dispose();
			}
		});
		botonRegistro.setBounds(207, 0, 211, 166);
		getContentPane().add(botonRegistro);
		
		
	}

}
