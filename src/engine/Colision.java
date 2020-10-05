package engine;

import java.util.ArrayList;

import constants.Constants;
import objects.Barra;
import objects.Bola;
import objects.Enemy;

public class Colision {
	private double alpha;
	private Bola bola;
	private Barra barra;
	private int maxY;
	
	public Colision(Bola bola, Barra barra, ArrayList<Enemy> enemyList) {
		super();
		this.maxY=Constants.MAX_HEIGHT+40;
		this.bola = bola;
		this.barra = barra;
	}
	public void update() {
		if(bola.getxBola()>=Constants.AREAPLAYf-20-5&&bola.getSpeedX()>=0) {
			bola.setSpeedX(bola.getSpeedX()*-1);
		}else if(bola.getxBola()<=Constants.AREAPLAYi&&bola.getSpeedX()<=0) {
			bola.setSpeedX(bola.getSpeedX()*-1);	
		}else if(bola.getyBola()+5>=barra.getyBarra()-bola.getdBola()&&
				bola.getyBola()+5<=barra.getyBarra()+10&&
				bola.getxBola()+(bola.getdBola()/2)>=barra.getxBarra()&&
				bola.getxBola()<=barra.getxBarra()+barra.getlBarra())
		{
			alpha= Math.toRadians(barra.colision(bola.getxBola(),bola.getdBola()));
			bola.setSpeedX((int) (Math.cos(alpha)*(Constants.speed+2)));
			bola.setSpeedY((int) (Math.sin(alpha)*(Constants.speed+2)));
			bola.setSpeedY(bola.getSpeedY()*-1);
			bola.setSpeedX(bola.getSpeedX()*-1);
		}else if(bola.getyBola()>=maxY&&bola.getSpeedY()>=0) {
			Constants.VIDAS--;
			if(Constants.VIDAS==0)Constants.fin=true;
			bola.setSpeedY(0);
			bola.setSpeedX(0);
		}else if(bola.getyBola()<=25&&bola.getSpeedY()<=0) {
			bola.setSpeedY(bola.getSpeedY()*-1);
		}
		bola.update(barra);
		for (Enemy enemy : Constants.EnemyList) {
			if(enemy.colision(bola)&&!enemy.isDead()) {
				enemy.setDead(true);
				enemy.setIy(-200);
				enemy.setFy(-200);
				Constants.ClientList.get(enemy.getId()).setMuerte(true);
				Constants.countDead++;
				Constants.speed++;
			}
		}
	}	
}
