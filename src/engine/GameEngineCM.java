package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import constants.Assets;
import constants.Constants;
import objects.Barra;
import objects.Bola;
import objects.Enemy;

public class GameEngineCM extends JFrame {


	private static final long serialVersionUID = 1L;

	boolean isRunning = true;
	private float opacity = 0.5F;
	private BufferedImage bufferedImage;
	private InputHandler input;
	
	private Barra barra;
	private Bola bola;
	private int ultimoMovimiento=4;
	private int proximoMovimiento=4;
	private int controlEnvio=0;

	public static void main(String[] args) {
		GameEngineCM game = new GameEngineCM();
		new GameLoop(game).start();
	}

	public GameEngineCM() {
		setTitle("Game Engine");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(Constants.MAX_WIDTH,Constants.MAX_HEIGHT);
		setLocation(0, 0);
		Constants.cliente.runHilo();
		Assets.loadassest();
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		barra = new Barra();
		bola = new Bola();
		for(int i=0;i<=7;i++) {
			Constants.EnemyList.add(new Enemy());
		}
	}
	
	void update() {
			if(!Constants.fin) {
			if(input.isKeyDown(KeyEvent.VK_D)) {
				proximoMovimiento=2;
			}else
			if(input.isKeyDown(KeyEvent.VK_A)) {
				proximoMovimiento=3;
			}else
			if(input.isKeyDown(KeyEvent.VK_W)) {
				proximoMovimiento=0;
			}else
			if(input.isKeyDown(KeyEvent.VK_S)) {
				proximoMovimiento=1;
			}else {
				proximoMovimiento=4;
			}
			if(ultimoMovimiento!=proximoMovimiento||controlEnvio==0) {
				Constants.cliente.transferirUDP(proximoMovimiento);
				ultimoMovimiento=proximoMovimiento;
			}
			controlEnvio=++controlEnvio%20;
			}
			}
	
	void draw() {
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		// Fondo
		bbg.drawImage(Assets.fondo, 0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT, null);
		bbg.drawImage(Assets.fondoArriba,0,25,Constants.MAX_WIDTH,73,null);
		bbg.drawImage(Assets.muro, Constants.AREAPLAYi, 0, 42, Constants.MAX_HEIGHT, null);
		bbg.drawImage(Assets.muro, Constants.AREAPLAYf-42,0, 42, Constants.MAX_HEIGHT, null);
		bbg.setColor(Color.BLACK);
		bbg.fillRect(0, 0, Constants.AREAPLAYi, Constants.AREAPLAYf);
		bbg.setColor(Color.BLACK);
		bbg.fillRect(Constants.AREAPLAYf, 0,Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
		// draws sprites
		barra.draw(bbg);
		bola.draw(bbg);
		for (Enemy e : Constants.EnemyList) {
			e.draw(bbg);
		}
		for(int i=0;i<Constants.VIDAS;i++) {
			bbg.drawImage(Assets.vida, 5, 40+i*50, 56+5, 45+40+i*50, 0, 0, 76, 65, null);
		}
		if(Constants.fin) {
			Color color = new Color(0.010f, 0f, 0f, opacity); 
			bbg.setColor(color);
			bbg.fillRect(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
			bbg.drawImage(Assets.muerteIco, Constants.MAX_WIDTH/2-51, Constants.MAX_HEIGHT/2-42, Constants.MAX_WIDTH/2+51, Constants.MAX_HEIGHT/2+43, 0, 0, 102, 85, null);
		}else if(Constants.VIDAS==0) {
			Color color = new Color(0.010f, 0f, 0f, opacity); 
			bbg.setColor(color);
			bbg.fillRect(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
			bbg.drawImage(Assets.win, Constants.MAX_WIDTH/2-436, Constants.MAX_HEIGHT/2-145, Constants.MAX_WIDTH/2+436, Constants.MAX_HEIGHT/2+145, 0, 0, 872, 290, null);
		}
		g.drawImage(bufferedImage, 0, 0, this);
	}

}
