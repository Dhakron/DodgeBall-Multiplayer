package engine;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import constants.Assets;
import constants.Constants;
import objects.Barra;
import objects.Bola;
import objects.Enemy;
import view.GameFinish;

public class GameEngine extends JFrame {


	private static final long serialVersionUID = 1L;

	boolean isRunning = true;

	private BufferedImage bufferedImage;
	private InputHandler input;
	
	private Barra barra;
	private Bola bola;
	private Colision move;
	private Enemy enemy;
	private ArrayList<Enemy> enemyList=new ArrayList<Enemy>();

	public GameEngine() {
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
		for(int i=0;i<=7;i++) {
			enemy= new Enemy(i,8);
			enemy.auto();
			Constants.EnemyList.add(enemy);
		}
		move= new Colision(bola, barra, enemyList);
	}
	
	void update() {
		if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			barra.right();
		}
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			barra.left();
		}
		if(input.isKeyDown(KeyEvent.VK_SPACE)&&bola.getSpeedY()==0) {
			bola.setSpeedX(0);
			bola.setSpeedY(-10);
		}	
		move.update();
		if(Constants.countDead>=8) {
			Constants.fin=false;
			GameFinish dialog= new GameFinish();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dispose();
		}
	}

	void draw() {
		
		Graphics g = getGraphics();
		Graphics bbg = bufferedImage.getGraphics();
		// Fondo
		
		bbg.fillRect(0, 0, getWidth(), getHeight());
		bbg.drawImage(Assets.fondo, 0, 0, Constants.MAX_WIDTH, Constants.MAX_HEIGHT, null);
		bbg.drawImage(Assets.fondoArriba,0,25,Constants.MAX_WIDTH,73,null);
		bbg.drawImage(Assets.muro, 0, 0, 42, Constants.MAX_HEIGHT, null);
		bbg.drawImage(Assets.muro, Constants.MAX_WIDTH-42,0, 42, Constants.MAX_HEIGHT, null);
		
		
		
		// draws sprites
		barra.draw(bbg);
		bola.draw(bbg);
		for (Enemy enemy : Constants.EnemyList) {
			enemy.draw(bbg);
		}
		g.drawImage(bufferedImage, 0, 0, this);
	}

}
