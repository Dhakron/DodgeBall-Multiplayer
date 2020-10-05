package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import constants.Constants;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFinish extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public GameFinish() {
		setBounds(100, 100, 659, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("ENHORABUENA "+Constants.USER_NAME+" ! GANASTE!");
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel.setBounds(15, 16, 399, 67);
			contentPanel.add(lblNewLabel);
		}
		{
			JButton okButton = new JButton("Main Menu");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainGame frame = new MainGame();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					dispose();
				}
			});
			okButton.setBackground(Color.RED);
			okButton.setBounds(472, 248, 150, 50);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JLabel lblTuPuntuacionEs = new JLabel("Tu puntuacion es:");
			lblTuPuntuacionEs.setForeground(Color.BLACK);
			lblTuPuntuacionEs.setFont(new Font("Times New Roman", Font.BOLD, 18));
			lblTuPuntuacionEs.setBounds(91, 137, 156, 67);
			contentPanel.add(lblTuPuntuacionEs);
		}
		{
			JLabel lblPts = new JLabel(Integer.toString(Constants.pts));
			lblPts.setForeground(Color.GREEN);
			lblPts.setFont(new Font("Times New Roman", Font.BOLD, 40));
			lblPts.setBounds(250, 129, 164, 67);
			contentPanel.add(lblPts);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new CardLayout(0, 0));
		}
	}

}
