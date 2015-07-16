package rss.logic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import javax.swing.UIManager;
import rss.gui.RSSReaderFrame;

public class RSSReader {
    
    private static final LinkedList <RSSData> feed = new LinkedList <RSSData> ();
    
    public static void main(String[] args) throws Exception {
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());} catch (Exception ex) { } // Native SO Look and Feel
        
        File configFile = new File("config.txt");
        if (configFile.exists()) {
            BufferedReader in = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream("config.txt"))));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }

                String tokens[] = line.split(" ");
                if (tokens[0].equals("TIME_TO_WAIT:")) {
                    System.out.println("TIME_TO_WAIT: " + tokens[1]);
                    Config.TIME_TO_WAIT = Long.parseLong(tokens[1]);
                }
            }
        }
        
        RSSReaderFrame frame = new RSSReaderFrame(feed);
        frame.setVisible(true);
    }
}