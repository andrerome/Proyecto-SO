/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

import rss.logic.RSSProvider;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author joalguzm
 */
public class RSSOptions {
    public ArrayList<RSSProvider> rssAvailible = new ArrayList<RSSProvider>();

    public RSSOptions() throws MalformedURLException {
        rssAvailible.add(new RSSProvider("Tema 1", "http://www.tullyrankin.com/feed/rss"));
        //rssAvailible.add(new RSSProvider("Tema 2","http://www.web-design-talk.co.uk/feed/"));
       // rssAvailible.add(new RSSProvider("Tema 3","http://sports.espn.go.com/espn/rss/oly/news"));
        rssAvailible.add(new RSSProvider("Tema 4","http://www.foxsports.com/feedout/syndicatedContent?categoryId=235"));
    }

    public ArrayList getRssAvailible() {
        return rssAvailible;
    }
    
}
