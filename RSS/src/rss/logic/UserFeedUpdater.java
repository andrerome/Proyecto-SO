package rss.logic;

import java.util.LinkedList;
import rss.gui.RSSReaderFrame;

public class UserFeedUpdater extends Thread {
    private final LinkedList <RSSData> userFeed;
    private final RSSReaderFrame frame;
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
        
        LinkedList <RSSData> newData = Config.RSS_BUFFER.getData();
        if (newData != null) {        
            for (RSSData new_rss : newData) {
                userFeed.add(0, new_rss);
                count++;
            }
        }
        
        this.lastUpdate = System.currentTimeMillis();
        return count;
    }
    
    @Override
    public void run() {
        boolean threadInterrupted = false;
        
        while (true) {
            while (getElapsedTime() <= Config.USER_FEED_UPDATE_INTERVAL) {
                try {
                    sleep(Math.min(Config.USER_FEED_UPDATE_INTERVAL, 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    threadInterrupted = true;
                }
            }
            
            if (threadInterrupted)
                break;
            
            System.out.println("Update...");

            int newEntries = updateFeed();
            if (newEntries > 0) {
                System.out.println("added to main feed: "+newEntries);
                frame.refreshFeed();
            } else {
                System.out.println("nothing to add.");
            }
        }
    }
}