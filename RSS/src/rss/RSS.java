/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rss;

/**
 *
 * @author Andres
 */
public class RSS {

    /**
     * @param args the command line arguments
     */


public static void main(String[] args) {

    Feed feed = new Feed();
    Productor p = new Productor(feed);
    Consumidor c = new Consumidor(feed);
    p.start();
    c.start();

}
public static class Feed {
    private int valor;
    private boolean disponible=false;
    public synchronized void meter(int nv) {
        if (disponible) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        valor = nv;
        disponible=true;
        System.out.println("Produce: "+valor);
        notify();
    }
    public synchronized void sacar() {
        if (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        System.out.println("Consume: "+valor);
        valor=0;
        disponible=false;
        notify();
    }
}
public static class Productor extends Thread {
    Feed f;
    public Productor(Feed nc) {
        f = nc;
    }
    public void run() {
        int i;
        for (i=1; i<=10; i++)
            f.meter(i);
    }
}

public static class Consumidor extends Thread {
    //Comentario random
    Feed f;
    public Consumidor(Feed nc) {
        f = nc;
    }
    public void run() {
        int i;
        for (i=1; i<=10; i++)
            f.sacar();
    }
}

}
