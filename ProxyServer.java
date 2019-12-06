package echo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ProxyServer extends Server {
	
	String peerHost;
	int peerPort;
	
	public ProxyServer(int myPort, String service, int peerPort, String peerHost) {
		super(myPort, service);
		this.peerHost = peerHost;
		this.peerPort = peerPort;
	}

	public RequestHandler makeHandler(Socket s) {
		RequestHandler handler = super.makeHandler(s);
		((ProxyHandler)handler).initPeer(peerHost, peerPort);
		handler.setSocket(s);
		return handler;
	}
	
	public static void main(String[] args) {
//		 PrintWriter stdout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
		int port = 5555;
		int peerPort = 6666;
		String peerHost = "localhost";
		String service = "echo.ProxyHandler";
//		stdout.println(service);
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			peerPort = Integer.parseInt(args[1]);
		}
		if (3 <= args.length) {
			port = Integer.parseInt(args[2]);
		}
		if (4 <= args.length) {
			peerPort =  Integer.parseInt(args[3]);
//			peerHost = args[3];
		}
		Server server = new ProxyServer(port, service, peerPort, peerHost);
//		stdout.println(peerHost);
//		stdout.println("server is made");
		server.listen();
//		stdout.println("Server is listening");
	}


}
