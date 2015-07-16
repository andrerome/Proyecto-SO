package rss.logic;

import java.net.MalformedURLException;
import java.util.LinkedList;
import javax.swing.UIManager;
import rss.gui.RSSReaderFrame;

public class RSSReader {
    
    private static final LinkedList <RSSData> feed = new LinkedList <RSSData> ();
    
    public static void main(String[] args) throws MalformedURLException {
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} catch (Exception ex) { } // Native SO Look and Feel
        
        RSSReaderFrame frame = new RSSReaderFrame(feed);
        frame.setVisible(true);
    }
}