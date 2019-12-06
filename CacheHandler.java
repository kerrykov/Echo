package echo;

public class CacheHandler extends ProxyHandler {
	
	private Cache cache;
	
	public CacheHandler() {
		cache = new Cache();
	}
	
	@Override
	public synchronized String response(String msg){
		String request = msg;
		String checkCache = cache.search(request);
		if(checkCache != null) {
			return checkCache;
		}else {
			this.peer.send(request);
			String peerResponse = peer.receive();
			cache.update(request, peerResponse);
			return peerResponse;
		}
	}
}