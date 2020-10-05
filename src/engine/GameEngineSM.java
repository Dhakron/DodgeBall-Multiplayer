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

public class GameEngineSM extends JFrame {


	private static final long serialVersionUID = 1L;

	boolean isRunning = true;

	private BufferedImage bufferedImage;
	private InputHandler input;
	private Barra barra;
	private Bola bola;
	private Colision move;
	private float opacity=0.5f;
	private int mArea=1;
	

	public GameEngineSM() {
		setTitle("Game Engine");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(Constants.MAX_WIDTH,Constants.MAX_HEIGHT);
		setLocation(0, 0);
		bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		input = new InputHandler(this);
		barra = new Barra();
		barra.setPosition();
		bola = new Bola();
		barra.transformData();
		bola.transformData();
		Assets.loadassest();
		Constants.server.runHilo();
		move= new Colision(bola, barra, Constants.EnemyList);
		Constants.server.runHilo();
	}

	void update() {
		if(!Constants.fin) {
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			barra.right();
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			barra.left();
		}
		if(input.isKeyDown(KeyEvent.VK_SPACE)&&bola.getSpeedY()==0) {
			bola.setSpeedX(0);
			bola.setSpeedY(-(Constants.speed+2));
		}
		for (Enemy e : Constants.EnemyList) {
			e.simularMovimiento();
		}
		if(mArea==0) {
			if(Constants.AREAPLAYi<350&&(Constants.MAX_WIDTH-Constants.AREAPLAYf)<350) {
				Constants.AREAPLAYf-=1;
				Constants.AREAPLAYi+=1;	
			}
			
		}
		move.update();
		barra.update();
		mArea=++mArea%25;
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
		barra.transformData();
		bola.transformData();
		barra.draw(bbg);
		bola.draw(bbg);
		for (Enemy enemy : Constants.EnemyList) {
			enemy.transformData();
			enemy.draw(bbg);
		}
		for(int i=0;i<Constants.VIDAS;i++) {
			bbg.drawImage(Assets.vida, 5, 40+i*50, 56+5, 45+40+i*50, 0, 0, 76, 65, null);
		}
		if(Constants.EnemyList.size()==Constants.countDead) {
			Color color = new Color(0.010f, 0f, 0f, opacity); 
			bbg.setColor(color);
			bbg.fillRect(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
			bbg.drawImage(Assets.win, Constants.MAX_WIDTH/2-436, Constants.MAX_HEIGHT/2-145, Constants.MAX_WIDTH/2+436, Constants.MAX_HEIGHT/2+145, 0, 0, 872, 290, null);
		}else if(Constants.fin) {
			Color color = new Color(0.010f, 0f, 0f, opacity); 
			bbg.setColor(color);
			bbg.fillRect(0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT);
			bbg.drawImage(Assets.muerteIco, Constants.MAX_WIDTH/2-51, Constants.MAX_HEIGHT/2-42, Constants.MAX_WIDTH/2+51, Constants.MAX_HEIGHT/2+43, 0, 0, 102, 85, null);
		}
		
		g.drawImage(bufferedImage, 0, 0, this);
	}
}
