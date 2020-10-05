package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Construccion {
	private int pix;
	private int piy;
	private int width;
	private int height;
	private int count=0;
	public int getCount() {
		return count;
	}

	public void plusCount() {
		
	}
	private int id;
	public Construccion(int id) {
		this.id=id;
	}
	
	private void construir(Enemy e,int i) {
		switch (i) {
		case 0:
			pix=e.getPix()-5;
			piy=e.getPiy()-10;
			width=e.getColumna()+10;
			height=5;
			break;
		case 1:
			pix=e.getPix()-5;
			piy=e.getPiy()+e.getFila()+10;
			width=e.getColumna()+10;
			height=5;
			break;
		case 2:
			pix=e.getPfx()+5;
			piy=e.getPix()-5;
			width=5;
			height=e.getFila()+10;
			break;
		case 3:
			pix=e.getPix()-10;
			piy=e.getPiy()-5;
			width=5;
			height=e.getFila()+10;
			break;
		default:
			break;
		}
	}
	private void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(pix, piy, width, height);
	}
	public boolean colision(Bola bola) {
		Rectangle bolaA= new Rectangle(bola.getxBola(),bola.getyBola(),bola.getdBola(),bola.getdBola());
		Rectangle cArea= new Rectangle(pix,piy,width,height);
		return bolaA.intersects(cArea);
	}
	public int getPix() {
		return pix;
	}
	public void setPix(int pix) {
		this.pix = pix;
	}
	public int getPiy() {
		return piy;
	}
	public void setPiy(int piy) {
		this.piy = piy;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
