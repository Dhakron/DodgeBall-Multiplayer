package constants;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Assets {
	public static ImageIcon ico= new ImageIcon("res/textures/enemy.png");
	public static Image img=new ImageIcon("res/textures/enemy.png").getImage();
	public static Image muerteIco=new ImageIcon("res/textures/muerte.png").getImage();
	public static Image muro=new ImageIcon("res/textures/muro_lateral.jpg").getImage();
	public static Image fondo=new ImageIcon("res/textures/fondo.jpg").getImage();
	public static Image fondoArriba=new ImageIcon("res/textures/Muro_Fondo.png").getImage();
	public static Image bola=new ImageIcon("res/textures/bola.png").getImage();
	public static Image barra=new ImageIcon("res/textures/barra.png").getImage();
	public static Image win=new ImageIcon("res/textures/win.png").getImage();
	public static Image vida=new ImageIcon("res/textures/vida.png").getImage();
	
	public static void loadassest() {
		ico= new ImageIcon("res/textures/enemy.png");
		img=new ImageIcon("res/textures/enemy.png").getImage();
		muro=new ImageIcon("res/textures/muro_lateral.jpg").getImage();
		fondo=new ImageIcon("res/textures/fondo.jpg").getImage();
		fondoArriba=new ImageIcon("res/textures/Muro_Fondo.png").getImage();
		bola=new ImageIcon("res/textures/bola.png").getImage();
		barra=new ImageIcon("res/textures/barra.png").getImage();
		muerteIco=new ImageIcon("res/textures/muerte.png").getImage();
		win=new ImageIcon("res/textures/win.png").getImage();
		vida=new ImageIcon("res/textures/vida.png").getImage();
	}
	
}
