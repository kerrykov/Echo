package echo;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

public class Cache {
	
	static HashMap<String, String> cacheMap = new HashMap<String, String>();
	
	public String search(String request) {
		String result = null;
		if (cacheMap.containsKey(request)) {
			result = cacheMap.get(request);
			  PrintWriter stdout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
			  stdout.println("Result found in the cache");
		}
		return result;
	}
	
	public void update(String request, String response) {
		synchronized(cacheMap) {
			cacheMap.put(request, response);
		}
	}

}