package rss.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class RSSData {
    // TODO: Implementar una clase genérica para los datos de una entrada RSS
    public String title="";
    public String link="";
    public String description="";
    public String language="";
    public String copyright="";
    public Date pubDate;

   //final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public RSSData(String title, String link, String description, String language,
        String copyright, Date pubDate) {
          this.title = title;
          this.link = link;
          this.description = description;
          this.language = language;
          this.copyright = copyright;
          this.pubDate = pubDate;
    }

    /*
    public List<FeedMessage> getMessages() {
      return entries;
    }
    */
  
 
    public String getTitle() {
      return title;
    }

    public String getLink() {
      return link;
    }

    public String getDescription() {
      return description;
    }

    public String getLanguage() {
      return language;
    }

    public String getCopyright() {
      return copyright;
    }

    public Date getPubDate() {
      return pubDate;
    }

    @Override
    public String toString() {
      return "Feed [copyright=" + copyright + ", description=" + description
          + ", language=" + language + ", link=" + link + ", pubDate="
          + pubDate + ", title=" + title + "]";
    }


    public RSSData(Element item) {
        this.title = RSSReader.getValue(item, "title");
        this.copyright = "Copyright"; //TODO: cambiar por tag real
        this.link = RSSReader.getValue(item, "link");  
        this.description = RSSReader.getValue(item, "description");

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy  HH:mm:ss");
        try {
           this.pubDate = formatter.parse(RSSReader.getValue(item, "pubDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }                      
    }
}