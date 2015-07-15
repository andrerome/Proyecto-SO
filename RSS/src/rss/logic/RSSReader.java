package rss.logic;

import java.util.LinkedList;
import rss.gui.RSSReaderFrame;

public class RSSReader {
    private static final LinkedList <RSSData> feed = new LinkedList <RSSData> ();
    
    public static void main(String[] args) {
        // TODO: Crear threads productores (FeedFetcher)
        
        RSSReaderFrame frame = new RSSReaderFrame(feed);
        
        UserFeedUpdater updater = new UserFeedUpdater(feed, frame);
        updater.start();
    }
}