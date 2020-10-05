package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import constants.Constants;
import engine.GameEngineCM;
import engine.GameEngineSM;
import engine.GameLoop;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Multiplayer extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	private JButton btnJoin;
	private JButton btnCreateParty;
	private JButton btnPlay;
	public static JTextArea Info_Conection;
	private boolean createParty=false;
	private boolean joinParty=false;
	public static JLabel lblcont;
	public static JLabel lblinfoJoin;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Multiplayer() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnJoin = new JButton("Join Party");
		btnJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				joinParty();
			}
		});
		btnJoin.setForeground(Color.BLACK);
		btnJoin.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
		btnJoin.setBackground(Color.RED);
		btnJoin.setBounds(328, 245, 235, 54);
		contentPane.add(btnJoin);
		
		btnCreateParty = new JButton("Create Party");
		btnCreateParty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				createParty();
			}
		});
		btnCreateParty.setForeground(Color.BLACK);
		btnCreateParty.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
		btnCreateParty.setBackground(Color.RED);
		btnCreateParty.setBounds(15, 16, 235, 54);
		contentPane.add(btnCreateParty);
		
		JButton btnAtras = new JButton("");
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				atras();
			}
		});
		btnAtras.setIcon(new ImageIcon("res/textures/atras_icon.png"));
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setOpaque(false);
		btnAtras.setBorderPainted(false);
		btnAtras.setBounds(15, 328, 50, 50);
		contentPane.add(btnAtras);
		
		textField = new JTextField();
		textField.setBounds(15, 262, 236, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblInsertaLaIp = new JLabel("Inserta la IP del anfitrion");
		lblInsertaLaIp.setBounds(15, 226, 184, 20);
		contentPane.add(lblInsertaLaIp);
		
		
		lblinfoJoin = new JLabel("");
		lblinfoJoin.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
		lblinfoJoin.setBounds(293, 312, 270, 50);
		contentPane.add(lblinfoJoin);
		
		lblcont = new JLabel("");
		lblcont.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblcont.setBounds(535, 17, 28, 29);
		contentPane.add(lblcont);
		
		Info_Conection = new JTextArea();
		Info_Conection.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		Info_Conection.setEditable(false);
		Info_Conection.setBounds(265, 16, 298, 180);
		contentPane.add(Info_Conection);
		
		btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				play();
			}
		});
		btnPlay.setForeground(Color.BLACK);
		btnPlay.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 22));
		btnPlay.setBackground(Color.RED);
		btnPlay.setBounds(15, 119, 236, 50);
		Constants.conectando=true;
	}
	void atras() {
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
		Constants.conectando=false;
		Constants.cliente.close();
	}
	void createParty() {
		if(createParty)return;
		createParty=true;
		Info_Conection.append("Esperando jugadores...\n");
		Constants.server.runHilo();
		contentPane.add(btnPlay);
		update(getGraphics());
	}
	void play() {
		if(Constants.EnemyList.size()>=1) {
			Constants.conectando=false;
			Constants.recivir=true;
			Constants.loadDificult();
			GameEngineSM game = new GameEngineSM();
			new GameLoop(game).start();
			this.dispose();
		}
	}
	void joinParty(){
		if(createParty)return;
		if(joinParty) return;
		joinParty=true;
		Constants.HOST= textField.getText();
		lblinfoJoin.setText("Estableciendo Conexion");
		Constants.cliente.conectar();
		GameEngineCM game = new GameEngineCM();
		new GameLoop(game).start();
		dispose();
	}
}
