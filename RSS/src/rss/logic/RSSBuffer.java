package rss.logic;

import java.util.LinkedList;

public class RSSBuffer {
    private LinkedList <RSSData> data = new LinkedList <RSSData> ();
    
    private boolean read_ready = false;
    private boolean write_ready = true;
    
    public synchronized LinkedList <RSSData> getData() {
        while (!read_ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        write_ready = false;
        
        LinkedList <RSSData> toReturn = new LinkedList <RSSData> ();
        for (RSSData rss_data : data)
            toReturn.add(rss_data);
        
        System.out.println("get data from buffer: "+toReturn);
        
        data.clear();
        
        write_ready = true;
        read_ready = false;
        
        notifyAll();
        
        return toReturn;
    }
    
    public synchronized void addData(LinkedList <RSSData> newData) {
        while (!write_ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        write_ready = false;
        
        System.out.println("add data to buffer: "+newData+". count: "+newData.size());
        for(RSSData rss_data : newData)
            data.add(rss_data);
        
        write_ready = true;
        read_ready = true;
        
        notifyAll();
    }
}