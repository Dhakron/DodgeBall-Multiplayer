package constants;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import conection.Client;
import conection.Server;
import objects.Barra;
import objects.Bola;
import objects.Clientes;
import objects.Enemy;

public class Constants {
	public static int MAX_WIDTH= 1280;
	public static int MAX_HEIGHT= 720;
	public static String dificult= "Humilde";
	public static int PORT=9002;
	public static ArrayList<Enemy> EnemyList= new ArrayList<Enemy>();
	public static String USER_NAME="UNKNOW";
	public static boolean conectando=true;
	public static boolean transferir=true;
	public static ArrayList<Clientes> ClientList = new ArrayList<Clientes>();
	public static boolean recivir=false;
	public static Server server=new Server();
	public static Client cliente=new Client();
	public static int ID;
	public static int speed=12;
	public static int moveEnemy=3;
	public static int countDead=0;
	public static int pts=10000;
	public static boolean fin=false;
	public static int AREAPLAYf=MAX_WIDTH;
	public static int AREAPLAYi=0;
	public static int VIDAS=2;
	public static String HOST;
	public static void leerData(byte[] b) {
		StringTokenizer tk= new StringTokenizer(new String(b),",");
		switch (Integer.valueOf(tk.nextToken())) {
		case 1:
			Constants.leerDataEnemy(tk);
			break;
		case 2:
			Bola.getData(tk);
			break;
		case 3:
			Barra.getData(tk);
			break;
		case 4:
			Constants.fin=true;
		default:
			break;
		}
	}
	public static void leerDataEnemy(StringTokenizer st) {
		int pix=Integer.parseInt(st.nextToken());
		int piy=Integer.parseInt(st.nextToken());
		int pfx=Integer.parseInt(st.nextToken());
		int pfy=Integer.parseInt(st.nextToken());
		int ix=Integer.parseInt(st.nextToken());
		int iy=Integer.parseInt(st.nextToken());
		int fx=Integer.parseInt(st.nextToken());
		int fy=Integer.parseInt(st.nextToken());
		int columna=Integer.parseInt(st.nextToken());
		int fila=Integer.parseInt(st.nextToken());
		int frames=Integer.parseInt(st.nextToken());
		int movimiento=Integer.parseInt(st.nextToken());
		int construccion=Integer.parseInt(st.nextToken());
		int id=Integer.parseInt(st.nextToken());
		Constants.EnemyList.get(id).set(pix,piy,pfx,pfy,ix,iy,fx,fy,columna,fila,frames,movimiento,construccion);
	}
	public static void loadhost() {
		try {
			HOST=InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void loadDificult() {
		if(dificult.equals("Demasiado Humilde")) {
			speed=8;
			VIDAS=4;
		}else if(dificult.equals("Humilde")) {
			speed=10;
			VIDAS=3;
		}else if(dificult.equals("Poco Humilde")) {
			speed=12;
			VIDAS=2;
		}else if(dificult.equals("Alex")) {
			speed=14;
			VIDAS=1;
		}
	}
}
