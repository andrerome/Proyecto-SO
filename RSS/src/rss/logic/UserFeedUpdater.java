package rss.logic;

import java.util.LinkedList;
import rss.gui.RSSReaderFrame;

public class UserFeedUpdater extends Thread {
    private LinkedList <RSSData> userFeed;
    private RSSReaderFrame frame;
    private long lastUpdate = 0;
    
    public UserFeedUpdater(LinkedList <RSSData> feed, RSSReaderFrame frame) {
        this.userFeed = feed;
        this.frame = frame;
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - this.lastUpdate;
    }
    
    private int updateFeed() {
        int count = 0; // Cantidad de entradas que se agregaron
        
        // TODO: Esperar a que desbloqueen el buffer
        // TODO: Obtener entradas RSS del buffer y agregarlos al userFeed
        // TODO: Desbloquear el buffer
        
        this.lastUpdate = System.currentTimeMillis();
        return count;
    }
    
    @Override
    public void run() {
        while (true) {
            while (getElapsedTime() <= Config.USER_FEED_UPDATE_INTERVAL) {
                // Do nothing...
            }
            System.out.println("Update...");

            int newEntries = updateFeed();
            if (newEntries > 0) {
                frame.refreshFeed();
            }
        }
    }
}