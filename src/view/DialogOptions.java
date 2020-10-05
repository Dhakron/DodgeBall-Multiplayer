package view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import constants.Constants;
import java.awt.Choice;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogOptions extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Choice choice;

	public DialogOptions(JFrame padre) {
		super(padre);
		setBounds(100, 100, 366, 172);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 329, 123);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblDificultad = new JLabel("Dificultad: ");
			lblDificultad.setBounds(23, 16, 109, 54);
			contentPanel.add(lblDificultad);
			lblDificultad.setForeground(Color.RED);
			lblDificultad.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		}
		
		choice = new Choice();
		choice.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		choice.setBackground(Color.ORANGE);
		choice.setBounds(138, 33, 191, 26);
		choice.add("Demasiado Humilde");
		choice.add("Humilde");
		choice.add("Poco Humilde");
		choice.add("Alex");
		contentPanel.add(choice);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(176, 84, 153, 39);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ok();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	void ok() {
		Constants.dificult=choice.getSelectedItem();
		dispose();
	}
}
