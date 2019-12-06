package echo;

import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {
	public RequestHandler(Socket s) { super(s); }
	public RequestHandler() { }
	// override in a subclass:
	protected String response(String msg) {
		return "echo: " + msg;
	}
	public void run() {
		while(true) {
			String request = receive();
			if (request == null) continue;
			if (request.contentEquals("quit")) break;
			String response = response(request);
			send(response);
			Thread.yield();
			
			
//			String msg = response(receive());
//			if (msg == "quit") {
//				break;
//			}
			// receive request
			// send response
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			// sleep
//			send(msg);
		}
		// close
		close();
	}
}