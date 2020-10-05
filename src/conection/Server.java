package conection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.StringTokenizer;
import constants.Constants;
import objects.Barra;
import objects.Bola;
import objects.Clientes;
import objects.Enemy;
import view.Multiplayer;

public class Server implements Runnable{
	ServerSocketChannel server=null;
	SocketChannel sc=null;
	ObjectInputStream inObj;
	ObjectOutputStream outObj;
	DataInputStream in;
	DataOutputStream out;
	private int idEnemy=0;
	private Thread hilo;
	byte[] buff= new byte[10];
	DatagramSocket screcibir;
	private boolean transferirUDP=true;
	public void transferirUDP() {
		try {
			@SuppressWarnings("resource")
			DatagramSocket server= new DatagramSocket();
			DatagramPacket r;
			while(true) {
				for (Clientes c : Constants.ClientList) {
					if(c.isMuerte()) {
						byte[] b= new byte[1];
						b= new String("4,").getBytes();
						r= new DatagramPacket(b, b.length,c.getHost(),c.getPort());
						server.send(r);
					}
					for (Enemy e : Constants.EnemyList) {
						r= new DatagramPacket(e.getMensaje(), e.getMensaje().length,c.getHost(),c.getPort());
						server.send(r);
					}
					r= new DatagramPacket(Bola.getMensaje(), Bola.getMensaje().length,c.getHost(),c.getPort());
					server.send(r);
					r= new DatagramPacket(Barra.getMensaje(), Barra.getMensaje().length,c.getHost(),c.getPort());
					server.send(r);
					Thread.sleep(5);
				}}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void recivirUDP() {
		try {
			screcibir = new DatagramSocket(Constants.PORT+2);
			DatagramPacket respuesta= new DatagramPacket(buff, buff.length);
			int i;
			StringTokenizer s;
			while(true) {	
				screcibir.receive(respuesta);
				s =new StringTokenizer(new String(respuesta.getData()),",");
				i=Integer.valueOf(s.nextToken());
				switch (i%10) {
				case 0:
					Constants.EnemyList.get(i/10).setMovimiento(0);
					break;
				case 1:
					Constants.EnemyList.get(i/10).setMovimiento(1);
					break;
				case 2:
					Constants.EnemyList.get(i/10).setMovimiento(2);
					break;
				case 3:
					Constants.EnemyList.get(i/10).setMovimiento(3);
					break;
				case 4:
					Constants.EnemyList.get(i/10).setMovimiento(4);
					break;	
				default:
					System.out.println("ERROR RECIBIR INFO");
					break;
				}
			}

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void conexion() {
		try {
			server= ServerSocketChannel.open();
			server.configureBlocking(false);
			server.socket().bind(new InetSocketAddress(Constants.PORT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(Constants.conectando) {	
			try {
				sc=server.accept();
				if(sc != null){
					out= new DataOutputStream(sc.socket().getOutputStream());
					out.writeInt(idEnemy);
					in=new DataInputStream(sc.socket().getInputStream());
					String name=in.readUTF();
					Multiplayer.Info_Conection.append(name+" preparado.\n");
					Constants.EnemyList.add(new Enemy(idEnemy, Constants.speed, name));
					Constants.ClientList.add(new Clientes((((InetSocketAddress) sc.getRemoteAddress()).getAddress()), Constants.PORT+4, idEnemy, name));
					Multiplayer.lblcont.setText((idEnemy+1)+"/8");
					idEnemy++;
					if(idEnemy>=7) {
						Constants.conectando=false;
						Multiplayer.Info_Conection.append("Maximo jugadores conectados\n");
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					if(sc != null) {
						out.close();
						in.close();
						sc.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void runHilo() {
		hilo = new Thread(this);
		hilo.start();
	}
	public void close() {
		try {
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		if(Constants.conectando) {
			conexion();
		}else if(transferirUDP) {
			transferirUDP=false;
			transferirUDP();
		}else {
			recivirUDP();
		}
	}

}
