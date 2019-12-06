package echo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MathHandler extends RequestHandler {
	
	public MathHandler() { super(); }
	public MathHandler(Socket sock) { super(sock); }
	
	private String execute(String op, double[] args) {
		double result = 0.0;
		if (op.equalsIgnoreCase("add")) {
			for (int i = 0; i < args.length; i++) {
				result += args[i];
			} 
		} else if (op.equalsIgnoreCase("mul")) {
			result = 1.0;
			for (int i = 0; i < args.length; i++) {
				result *= args[i];
			}
		} else if (op.equalsIgnoreCase("sub")) {
			result = args[0];
			for (int i = 1; i < args.length; i++) {
				result -= args[i];
			}
		} else if (op.equalsIgnoreCase("div")) {
			result = args[0];
			for (int i = 1; i < args.length; i++) {
				result /= args[i];
			}
		}
		return "" + result;
	}
	
	protected String response(String request) { 
			String[] tokens = request.split("\\s+");
			double[] args = new double[tokens.length - 1];
			try {
				for (int i = 0; i < args.length; i++) {
					args[i] = Double.parseDouble(tokens[i+1]);
				}
			} catch(NumberFormatException e) {
				return "arguments must be numeric";
			}
			 PrintWriter stdout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
			 stdout.println("Sending message");
			return execute(tokens[0], args);
	}
}
