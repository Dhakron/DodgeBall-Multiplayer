package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import constants.Constants;
import engine.GameEngine;
import engine.GameLoop;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGame extends JFrame {

	private JPanel contentPane;
	private JTextField txtPlayername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}
	

	/**
	 * Create the frame.
	 */
	public MainGame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				play();
			}
		});
		btnPlay.setBackground(Color.RED);
		btnPlay.setForeground(Color.BLACK);
		btnPlay.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
		btnPlay.setBounds(169, 151, 234, 54);
		contentPane.add(btnPlay);
		
		JButton btnMultiplayer = new JButton("Multiplayer");
		btnMultiplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				multiplayer();
			}
		});
		btnMultiplayer.setForeground(Color.BLACK);
		btnMultiplayer.setBackground(Color.RED);
		btnMultiplayer.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		btnMultiplayer.setBounds(169, 221, 234, 54);
		contentPane.add(btnMultiplayer);
		
		JButton btnRecords = new JButton("Records");
		btnRecords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnRecords.setForeground(Color.BLACK);
		btnRecords.setBackground(Color.RED);
		btnRecords.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		btnRecords.setBounds(169, 291, 234, 54);
		contentPane.add(btnRecords);
		
		JButton btnOptions = new JButton("");
		btnOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				options();
			}
		});
		btnOptions.setIcon(new ImageIcon("res/textures/option_icon.png"));
		btnOptions.setBackground(Color.white);
		btnOptions.setOpaque(false);
		btnOptions.setBorderPainted(false);
		btnOptions.setBounds(513, 328, 50, 50);
		contentPane.add(btnOptions);
		
		txtPlayername = new JTextField();
		txtPlayername.setForeground(Color.RED);
		txtPlayername.setBackground(Color.WHITE);
		txtPlayername.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayername.setText("Player Name");
		txtPlayername.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		txtPlayername.setBounds(146, 81, 273, 54);
		contentPane.add(txtPlayername);
		txtPlayername.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(85, 115, 69, 20);
		contentPane.add(label);
		Constants.loadhost();
	}
	void play() {
		Constants.USER_NAME=txtPlayername.getText();
		GameEngine game = new GameEngine();
		new GameLoop(game).start();
		this.dispose();
	}
	void options() {
		DialogOptions dialog= new DialogOptions(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	void multiplayer() {
		Constants.USER_NAME=txtPlayername.getText();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Multiplayer frame = new Multiplayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dispose();
	}
}
