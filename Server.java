package echo;

import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} // catch
	}


	public void listen() {
		while(true) {
			try {
				Socket socket = mySocket.accept();
				RequestHandler handler = makeHandler(socket);
				if (handler == null) {
					System.out.println("no handler created!");
				}
				Thread slave = new Thread(handler);
				slave.start();
			
			} catch (Exception e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
			// accept a connection
			// make handler
			// start handler
		} // while
	}

	public RequestHandler makeHandler(Socket s) {
		RequestHandler handler = null;
		try {
			handler = (RequestHandler) handlerType.newInstance();
			handler.setSocket(s);
			return handler;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		
//		// handler = a new instance of handlerType
//		RequestHandler handler = (RequestHandler) handlerType.newInstance();
//		// set handler's socket to s
//		handler.setSocket(s);
//		// return handler
//		return handler;
	}



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}
