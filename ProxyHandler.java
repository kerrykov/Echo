package echo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ProxyHandler extends RequestHandler {
	
	 protected Correspondent peer;
	 
	 public ProxyHandler(Socket sock) { super(sock); }
	 public ProxyHandler() { super(); }

	  public void initPeer(String host, int port) {
	     peer = new Correspondent();
	     peer.requestConnection(host, port);
	  }
	  
	  protected String response(String msg) {
//		  PrintWriter stdout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
//		  stdout.println("Sending message");
		  peer.send(msg);
		  return (peer.receive());
	  }	  
}