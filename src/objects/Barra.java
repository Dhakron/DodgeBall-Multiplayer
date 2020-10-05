package objects;
import java.awt.Graphics;
import java.util.StringTokenizer;

import constants.Assets;
import constants.Constants;

public class Barra {
	private static int xBarra;
	private static int lBarra=149;
	private static int yBarra;
	private static int maxX;
	private static byte[] mensaje=new byte[5];
	
	public void transformData() {
		mensaje= ("3,"+String.valueOf(xBarra)+","+
		String.valueOf(yBarra)+","+
		String.valueOf(lBarra)+",").getBytes();
	}
	public static void getData(StringTokenizer st) {
		xBarra=Integer.parseInt(st.nextToken());
		yBarra=Integer.parseInt(st.nextToken());
		lBarra=Integer.parseInt(st.nextToken());
	}
	public static byte[] getMensaje() {
		return mensaje;
	}
	public void setMensaje(byte[] mensaje) {
		this.mensaje = mensaje;
	}
	public void setPosition() {
		maxX=Constants.MAX_WIDTH;
		yBarra=Constants.MAX_HEIGHT-20;
		xBarra=Constants.MAX_WIDTH/2-lBarra/2;
	}
	public void set(int xBarra, int lBarra, int yBarra) {
		this.xBarra = xBarra;
		this.lBarra = lBarra;
		this.yBarra = yBarra;
	}
	public void draw(Graphics g) {
		g.drawImage(Assets.barra, xBarra, yBarra, lBarra, 29, null);
	}
	public double colision(int xBola, int dBola) {
		int posicion=(xBola+(dBola/2))-getxBarra();
		if(posicion>=0&&posicion<=lBarra*0.23) {
			return 30;
		}else if(posicion>=lBarra*0.23+1&&posicion<=lBarra*0.46) {
			return 60;
		}else if(posicion>=lBarra*0.46+1&&posicion<=lBarra*0.54) {
			return 90;
		}else if(posicion>=lBarra*0.54+1&&posicion<=lBarra*0.77) {
			return 120;
		}else if(posicion>=lBarra*0.77+1&&posicion<=lBarra*1) {
			return 150;
		}else {
			return 150;
		}
	}

	public void left(){
		if(!(xBarra<5+Constants.AREAPLAYi))xBarra-=Constants.speed+2;
	}
	public void update() {
		if(xBarra<Constants.AREAPLAYi)xBarra=Constants.AREAPLAYi;
		if(xBarra+lBarra>Constants.AREAPLAYf)xBarra=Constants.AREAPLAYf-lBarra;
	}
	public void right(){
		if(!(xBarra+lBarra>Constants.AREAPLAYf-5))xBarra+=Constants.speed+2;
	}
	public void setxBarra(int xBarra) {
		this.xBarra = xBarra;
	}
	public int getlBarra() {
		return lBarra;
	}
	public void setlBarra(int lBarra) {
		this.lBarra = lBarra;
	}
	public int getyBarra() {
		return yBarra;
	}
	public void setyBarra(int yBarra) {
		this.yBarra = yBarra;
	}
	public int getxBarra() {
		return xBarra;
	}
}
