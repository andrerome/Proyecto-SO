package rss.logic;

public class FeedFetcher extends Thread {
    private String url = null;
    private long lastFetch = 0;
    
    public FeedFetcher(String url) {
        this.url = url;
    }
    
    private void pull() {
        RSSData data = new RSSData(); // TODO: Pedir datos reales del RSS
        Config.RSS_BUFFER.add(data);
        
        lastFetch = System.currentTimeMillis();
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.lastFetch;
    }
    
    @Override
    public void run() {
        while (true) {
            while (getElapsedTime() <= Config.TIME_TO_WAIT) {
                if (Config.FORCE_PULL) {
                    break;
                }
            };

            // TODO: Esperar a que liberen el buffer
            pull();
            // TODO: Notificar que se dejó de usar el buffer
        }
    }
}