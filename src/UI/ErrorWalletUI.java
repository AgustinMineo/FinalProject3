package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorWalletUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel labelError = new JLabel("No es posible realizar transferencia a Wallet.");
	private JLabel labelErrorSiMismo = new JLabel("Error: No es posible realizar transferencia a si mismo.");
	private JLabel labelWalletIngresadaNoExiste = new JLabel("Error: Wallet ingresada no existe");
	private JLabel labelTransfOK = new JLabel("Transferencia realizada correctamente");
	private JLabel labelCambiosGuardadosOK = new JLabel("Se han guardado los cambios exitosamente.");
	private JLabel labelValidacionOK = new JLabel("Se ha validado correctamente la transferencia");
	private JLabel labelErrorSinSeleccionTransf = new JLabel("Error: No selecciono ninguna transferencia para validar");
	private JLabel labelExportacionOK = new JLabel("Se ha realizado la exportacion correctamente.");
	private JLabel labelErrorExportacion = new JLabel("No se ha podido realizar la exportacion.");
	
	public ErrorWalletUI(int i) {
		setTitle("ERROR");
		setBounds(475, 275, 374, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		labelError.setBounds(21, 10, 360, 32);
		contentPanel.add(labelError);

		labelWalletIngresadaNoExiste.setBounds(21, 44, 329, 13);
		contentPanel.add(labelWalletIngresadaNoExiste);
	
		labelErrorSiMismo.setBounds(21, 67, 329, 13);
		contentPanel.add(labelErrorSiMismo);

		labelTransfOK.setBounds(21, 44, 329, 13);
		contentPanel.add(labelTransfOK);
		
		labelCambiosGuardadosOK.setBounds(21, 44, 329, 13);
		contentPanel.add(labelCambiosGuardadosOK);
		
		labelValidacionOK.setBounds(21, 44, 329, 13);
		contentPanel.add(labelValidacionOK);
		
		if (i==0) {
			labelError.setVisible(true);
			labelWalletIngresadaNoExiste.setVisible(false);
			labelErrorSiMismo.setVisible(true);
			labelTransfOK.setVisible(false);
			labelValidacionOK.setVisible(false);
			labelCambiosGuardadosOK.setVisible(false);
			labelErrorSinSeleccionTransf.setVisible(false);
			labelExportacionOK.setVisible(false);
			labelErrorExportacion.setVisible(false);
		}
		else
			if (i==1) {
				labelErrorSiMismo.setVisible(false);
				labelTransfOK.setVisible(false);
				labelCambiosGuardadosOK.setVisible(false);
				labelValidacionOK.setVisible(false);
				labelWalletIngresadaNoExiste.setVisible(true);
				labelError.setVisible(true);
				labelErrorSinSeleccionTransf.setVisible(false);
				labelExportacionOK.setVisible(false);
				labelErrorExportacion.setVisible(false);
			}
			else
				if (i==2) {
					labelError.setVisible(false);
					labelWalletIngresadaNoExiste.setVisible(false);
					labelErrorSiMismo.setVisible(false);
					labelCambiosGuardadosOK.setVisible(false);
					labelValidacionOK.setVisible(false);
					labelTransfOK.setVisible(true);
					labelErrorSinSeleccionTransf.setVisible(false);
					labelExportacionOK.setVisible(false);
					labelErrorExportacion.setVisible(false);
				}
				else
					if (i==3) {
						labelError.setVisible(false);
						labelWalletIngresadaNoExiste.setVisible(false);
						labelErrorSiMismo.setVisible(false);
						labelTransfOK.setVisible(false);
						labelValidacionOK.setVisible(false);
						labelCambiosGuardadosOK.setVisible(true);
						labelExportacionOK.setVisible(false);
						labelErrorSinSeleccionTransf.setVisible(false);
						labelErrorExportacion.setVisible(false);
						
					}
					else
						if (i==4) {
							labelError.setVisible(false);
							labelWalletIngresadaNoExiste.setVisible(false);
							labelErrorSiMismo.setVisible(false);
							labelTransfOK.setVisible(false);
							labelCambiosGuardadosOK.setVisible(false);
							labelValidacionOK.setVisible(true);
							labelErrorSinSeleccionTransf.setVisible(false);
							labelExportacionOK.setVisible(false);
							labelErrorExportacion.setVisible(false);
						}
						else
							if (i==5) {
								labelErrorSinSeleccionTransf.setVisible(true);
								labelError.setVisible(false);
								labelWalletIngresadaNoExiste.setVisible(false);
								labelErrorSiMismo.setVisible(false);
								labelTransfOK.setVisible(false);
								labelCambiosGuardadosOK.setVisible(false);
								labelValidacionOK.setVisible(false);
								labelExportacionOK.setVisible(false);
								labelErrorExportacion.setVisible(false);
							}
							else
								if (i==6) {
									labelErrorSinSeleccionTransf.setVisible(false);
									labelError.setVisible(false);
									labelWalletIngresadaNoExiste.setVisible(false);
									labelErrorSiMismo.setVisible(false);
									labelTransfOK.setVisible(false);
									labelCambiosGuardadosOK.setVisible(false);
									labelValidacionOK.setVisible(false);
									labelExportacionOK.setVisible(true);
									labelErrorExportacion.setVisible(false);
								}
								else
									if (i==7) {
										labelErrorSinSeleccionTransf.setVisible(false);
										labelError.setVisible(false);
										labelWalletIngresadaNoExiste.setVisible(false);
										labelErrorSiMismo.setVisible(false);
										labelTransfOK.setVisible(false);
										labelCambiosGuardadosOK.setVisible(false);
										labelValidacionOK.setVisible(false);
										labelExportacionOK.setVisible(false);
										labelErrorExportacion.setVisible(true);
									}
			
		
		
		JButton botonOK = new JButton("OK");
		botonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonOK.setBounds(105, 98, 85, 21);
		contentPanel.add(botonOK);
		labelErrorSinSeleccionTransf.setBounds(21, 67, 329, 13);
		
		contentPanel.add(labelErrorSinSeleccionTransf);
		
		
		labelExportacionOK.setBounds(21, 52, 329, 13);
		contentPanel.add(labelExportacionOK);
		labelErrorExportacion.setBounds(21, 52, 329, 13);
		
		contentPanel.add(labelErrorExportacion);
		
		
		
		
		
		
	}
}
