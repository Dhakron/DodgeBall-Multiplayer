package objects;

import java.net.InetAddress;
//ESTA CLASE ALBERGA LOS JUGADORES QUE SE CONECTAN Al SERVIDOR
public class Clientes {
	private InetAddress host;
	private int port;
	private int id;
	private String userName;
	private boolean muerte;
	public Clientes(InetAddress host, int port, int id, String userName) {
		super();
		this.host = host;
		this.port = port;
		this.id = id;
		this.userName = userName;
		this.muerte=false;
	}
	
	public InetAddress getHost() {
		return host;
	}
	public boolean isMuerte() {
		return muerte;
	}
	public void setMuerte(boolean muerte) {
		this.muerte = muerte;
	}
	public void setHost(InetAddress host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
