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
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import constants.Constants;
import view.Multiplayer;

public class Client implements Runnable{
	ObjectInputStream inObj;
	ObjectOutputStream outObj;
	DataInputStream in;
	DataOutputStream out;
	SocketChannel sc;
	private Thread hilo;
	private boolean conectar=true;
	DatagramSocket scenvio ;
	DatagramPacket respuesta;
	byte[] buff = new byte[10];
	public static int ID=0;
	public Client() {
				
	}
	public void transferirUDP(int mv) {
		try {
			buff= String.valueOf(ID*10+mv+",").getBytes();
			scenvio= new DatagramSocket();
			respuesta= new DatagramPacket(buff, buff.length,new InetSocketAddress(Constants.HOST, Constants.PORT+2));
			scenvio.send(respuesta);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void recivirUDP() {
		DatagramSocket server;
		DatagramPacket respuesta;
		try {
		server = new DatagramSocket(Constants.PORT+4);
		while(true) {
		byte[] buffer=new byte [75];
		respuesta= new DatagramPacket(buffer, buffer.length);
		server.receive(respuesta);
		Constants.leerData(respuesta.getData());
		}
		
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void conectar() {
		try {
			
			sc=SocketChannel.open();
			sc.connect(new InetSocketAddress(Constants.HOST, Constants.PORT));
			in= new DataInputStream(sc.socket().getInputStream());
			ID=in.readInt();
			out=new DataOutputStream(sc.socket().getOutputStream());
			out.writeUTF(Constants.USER_NAME);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				sc.close();
				conectar=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Multiplayer.lblinfoJoin.setText("Conexion establecida. Espera al resto");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void runHilo() {
		hilo = new Thread(this);
		hilo.start();
	}
	@Override
	public void run() {
		if(conectar)conectar();
		else recivirUDP();
	}
	public void close() {
		try {
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
