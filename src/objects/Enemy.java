package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import constants.Assets;
import constants.Constants;
//ESTA CLASE ALBERGA LOS PERSONAJES DIRIGIDOS POR LOS JUGADORES
public class Enemy implements Runnable{
	private int pix;
	private int piy;
	private int pfx;
	private int pfy;
	private int ix;
	private int iy;
	private int fx;
	private int fy;
	private int fila;
	private int columna;
	private int frames=0;
	private int speed;
	private int iyoriginal;
	private int fyoriginal;
	private boolean dead=false;
	private Thread hilo;
	private String userName;
	private int id;
	private int movimiento=4;
	private int muerte;
	private int construccion;
	private byte[] mensaje= new byte[75];
	private int mE= 0;
	
	//CONSTRUCTORES:
	public Enemy() {
	}
	
	public Enemy( int muñeco, int speed, String User_Name) {
		this.id=muñeco;
		this.userName= User_Name;
		columna= Assets.ico.getIconWidth()/12;
		fila= Assets.ico.getIconHeight()/8;
		this.speed=speed;
		if(muñeco<4) {
			ix=0+((columna*3)*muñeco);
			iy=0;
			fx=ix+columna;
			fy=iy+fila;
		}else {
			ix=0+((columna*3)*(muñeco-4));
			iy=fila*4;
			fx=ix+columna;
			fy=iy+fila;
		}
		iyoriginal=iy;
		fyoriginal=fy;
		pix=(int)(Math.random()*(Constants.MAX_WIDTH-200))+50;
		piy=(int)(Math.random()*(Constants.MAX_HEIGHT-200))+50;
		pfx=pix+columna;
		pfy=piy+fila;
		transformData();
	}
	
	public Enemy( int muñeco, int speed) {
		columna= Assets.ico.getIconWidth()/12;
		fila=Assets.ico.getIconHeight()/8;
		this.speed=speed;
		if(muñeco<4) {
			ix=0+((columna*3)*muñeco);
			iy=0;
			fx=ix+columna;
			fy=iy+fila;
		}else {
			ix=0+((columna*3)*(muñeco-4));
			iy=fila*4;
			fx=ix+columna;
			fy=iy+fila;
		}
		iyoriginal=iy;
		fyoriginal=fy;
		pix=(int)(Math.random()*(Constants.MAX_WIDTH-200))+50;
		piy=(int)(Math.random()*(Constants.MAX_HEIGHT-200))+50;
		pfx=pix+columna;
		pfy=piy+fila;
	}
	//Este metodo permite ahorrar transferencia de datos.
	//En caso de que una tecla se pulse consecutamente,se evita enviar paquetes
	//con la misma informacion y este metodo reproduce el ultimo movimiento recivido.
	public void simularMovimiento() {
		if(mE==0){
		switch (movimiento) {
		case 0:
			moveUp();
			break;
		case 1:
			moveDown();
			break;
		case 2:
			moveRight();
			break;
		case 3:
			moveLeft();
			break;
		case 4:
			dontMove();
			break;
		default:
			break;
		}
		}
		mE=++mE%3;
	}
	//Hilo que realiza un movimiento aleatorio del enemigo.
	//Exclusivo del offline, pero se puede implementar en el online
	public void auto() {
		hilo = new Thread(this);
		hilo.start();
	}
	@Override
	public void run() {
		for(;;){
			int vueltas= ((int)(Math.random()*4)+1)*10;
			int mov=((int)(Math.random()*5)+1); 
			for(int i=vueltas;i>0;i--) {
				switch (mov) {
				case 1:
					moveLeft();
					break;
				case 2:
					moveRight();
					break;
				case 3:
					moveUp();
					break;
				case 4:
					moveDown();
					break;
				default:
					dontMove();
					break;
				}
				try {
					Thread.sleep(70);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	//Metodos que transforman la info del enemigo en un array de bytes para pasarlos a traves de la red.
	public void transformData() {
		mensaje= ("1,"+
		String.valueOf(pix)+","+
		String.valueOf(piy)+","+
		String.valueOf(pfx)+","+
		String.valueOf(pfy)+","+
		String.valueOf(ix)+","+
		String.valueOf(iy)+","+
		String.valueOf(fx)+","+
		String.valueOf(fy)+","+
		String.valueOf(columna)+","+
		String.valueOf(fila)+","+
		String.valueOf(frames)+","+
		String.valueOf(movimiento)+","+
		String.valueOf(construccion)+","+
		String.valueOf(id)+",").getBytes();
	}
	//Metodo que pinta el enemigo
	public void draw(Graphics g) {
		if(!dead)
		g.drawImage(Assets.img, pix, piy, pfx, pfy, ix+(columna*frames), iy, fx+(columna*frames), fy, null);		
	}
	//Metodos de movimiento del enemigo
	public int moveUp() {
		if(isDead())return 0;
		if(piy<=42) return dontMove();
		frames=++frames%3;
		if(frames==1)frames=2;
		piy-=speed;
		pfy-=speed;
		iy=iyoriginal+(fila*3);
		fy=fyoriginal+(fila*3);
		return 0;
	}
	public int moveRight(){
		if(isDead())return 0;
		if(pfx>=Constants.AREAPLAYf-42) return dontMove();
		frames=++frames%3;
		if(frames==1)frames=2;
		pix+=speed;
		pfx+=speed;
		iy=iyoriginal+(fila*2);
		fy=fyoriginal+(fila*2);
		return 0;
	}
	public int moveDown() {
		if(isDead())return 0;
		if(pfy>=Constants.MAX_HEIGHT-100) return dontMove();
		frames=++frames%3;
		if(frames==1)frames=2;
		piy+=speed;
		pfy+=speed;
		iy=iyoriginal;
		fy=fyoriginal;
		return 0;
	}

	public int moveLeft(){
		if(isDead())return 0;
		if(pix<=Constants.AREAPLAYi+42) return dontMove();
		frames=++frames%3;
		if(frames==1)frames=2;
		pix-=speed;
		pfx-=speed;
		iy=iyoriginal+(fila*1);
		fy=fyoriginal+(fila*1);
		return 0;
	}
	public int dontMove(){
		if(isDead())return 0;
		if(pix<Constants.AREAPLAYi+42) {
			pix=Constants.AREAPLAYi+42;
			pfx=Constants.AREAPLAYi+42+columna;
		}
		if(pfx>Constants.AREAPLAYf-42) {
			pix=Constants.AREAPLAYf-42-columna;
			pfx=Constants.AREAPLAYf-42;
		}
		frames=1;
		iy=iyoriginal;
		fy=fyoriginal;
		return 0;
	}
	//Metodo de de area de colision del enemigo
	public boolean colision(Bola bola) {
		Rectangle bolaA= new Rectangle(bola.getxBola(),bola.getyBola(),bola.getdBola(),bola.getdBola());
		Rectangle enemyA= new Rectangle(pix+10,piy+10,columna-10,fila-10);
		return bolaA.intersects(enemyA);
	}

	//por ultimo GETTERS Y SETTERS
	public void set(int pix,int piy,int pfx,int pfy,int ix,	int iy,	int fx,	int fy,	int columna,int fila,int frames,int movimiento, int construccion) {
		this.pix=pix;
		this.piy=piy;
		this.pfx=pfx;
		this.pfy=pfy;
		this.ix=ix;
		this.fx=fx;
		this.iy=iy;
		this.fy=fy;
		this.fila=fila;
		this.columna=columna;
		this.frames=frames;
		this.movimiento=movimiento;
		this.construccion=construccion;
		
	}
	public int getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}
	public int getMuerte() {
		return muerte;
	}
	public void setMuerte(int muerte) {
		this.muerte = muerte;
	}
	public int getConstruccion() {
		return construccion;
	}
	public void setConstruccion(int construccion) {
		this.construccion = construccion;
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
	public int getPfx() {
		return pfx;
	}
	public void setPfx(int pfx) {
		this.pfx = pfx;
	}
	public int getPfy() {
		return pfy;
	}
	public void setPfy(int pfy) {
		this.pfy = pfy;
	}
	public int getIx() {
		return ix;
	}
	public void setIx(int ix) {
		this.ix = ix;
	}
	public int getIy() {
		return iy;
	}
	public void setIy(int iy) {
		this.iy = iy;
	}
	public int getFx() {
		return fx;
	}
	public void setFx(int fx) {
		this.fx = fx;
	}
	public int getFy() {
		return fy;
	}
	public void setFy(int fy) {
		this.fy = fy;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public int getFrames() {
		return frames;
	}
	public void setFrames(int frames) {
		this.frames = frames;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getFyoriginal() {
		return fyoriginal;
	}
	public void setFyoriginal(int fyoriginal) {
		this.fyoriginal = fyoriginal;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public byte[] getMensaje() {
		return mensaje;
	}
	public void setMensaje(byte[] mensaje) {
		this.mensaje = mensaje;
	}
}
