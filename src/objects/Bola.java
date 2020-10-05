package objects;
import java.awt.Graphics;
import java.util.StringTokenizer;

import constants.Assets;
import constants.Constants;

public class Bola {
	private static int xBola;
	private static int yBola;
	private static int speedX=0, speedY=0;
	private static int dBola=20;
	private static byte[] mensaje= new byte[6];
	//Movimiento de la bola.
	public void update(Barra barra) {
		if(speedX==0&&speedY==0) {
			setPosition(barra);
		}
		else {
			xBola+=speedX;
			yBola+=speedY;	
		}	
	}
	//Metodos que transforman la info del enemigo en un array de bytes o viceversa para pasarlos a traves de la red.	
	public void transformData() {
		mensaje= ("2,"+String.valueOf(xBola)+","+
		String.valueOf(yBola)+","+
		String.valueOf(speedX)+","+
		String.valueOf(speedY)+","+
		String.valueOf(dBola)+","+
		String.valueOf(Constants.AREAPLAYi)+","+
		String.valueOf(Constants.AREAPLAYf)+","+
		String.valueOf(Constants.VIDAS)+",").getBytes();
	}
	public static void getData(StringTokenizer st) {
		xBola=Integer.parseInt(st.nextToken());
		yBola=Integer.parseInt(st.nextToken());
		speedX=Integer.parseInt(st.nextToken());
		speedY=Integer.parseInt(st.nextToken());
		dBola=Integer.parseInt(st.nextToken());
		Constants.AREAPLAYi=Integer.parseInt(st.nextToken());
		Constants.AREAPLAYf=Integer.parseInt(st.nextToken());
		Constants.VIDAS=Integer.parseInt(st.nextToken());
	}
	//pintar bola
	public void draw(Graphics g) {
		g.drawImage(Assets.bola,xBola,yBola,dBola,dBola, null);
	}
	//Coloca la bola en posicion de start
	public void setPosition(Barra barra) {
		setxBola(barra.getxBarra()+barra.getlBarra()/2-getdBola()/2);
		setyBola(barra.getyBarra()-30);
	}
	//Por ultimo Getters y setters
	public void set(int xBola, int yBola, int speedX, int speedY, int dBola) {
		this.xBola = xBola;
		this.yBola = yBola;
		this.speedX = speedX;
		this.speedY = speedY;
		this.dBola = dBola;
	}

	public static byte[] getMensaje() {
		return mensaje;
	}

	public void setMensaje(byte[] mensaje) {
		this.mensaje = mensaje;
	}


	public int getxBola() {
		return xBola;
	}

	public void setxBola(int xBola) {
		this.xBola = xBola;
	}

	public int getyBola() {
		return yBola;
	}

	public void setyBola(int yBola) {
		this.yBola = yBola;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getdBola() {
		return dBola;
	}

	public void setdBola(int dBola) {
		this.dBola = dBola;
	}
	
}
