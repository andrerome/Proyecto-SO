package rss.logic;

public class RSSData {
    // TODO: Implementar una clase genérica para los datos de una entrada RSS
    public String title="";
    public String link="";
    public String description="";
    public String language="";
    public String copyright="";
    public String pubDate="";

   //final List<FeedMessage> entries = new ArrayList<FeedMessage>();

  public RSSData(String title, String link, String description, String language,
      String copyright, String pubDate) {
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

  public String getPubDate() {
    return pubDate;
  }

  @Override
  public String toString() {
    return "Feed [copyright=" + copyright + ", description=" + description
        + ", language=" + language + ", link=" + link + ", pubDate="
        + pubDate + ", title=" + title + "]";
  }
}