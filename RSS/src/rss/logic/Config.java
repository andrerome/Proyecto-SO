package rss.logic;

import java.util.LinkedList;

public abstract class Config {
    
    /**
     * Tiempo a esperar para vaciar el buffer y pasar las nuevas entradas al feed principal
     */
    public final static long USER_FEED_UPDATE_INTERVAL = 1000; // 1 segundo

    /**
     * Tiempo a esperar entre pulls
     * Nota: 1 minute = 60000 milliseconds
     */
    public final static long TIME_TO_WAIT = 60000l;
    
    /**
     * Bandera para que los hilos sepan si se debe forzar un pull mientras esperan
     * TODO: Esta bandera debe ser seteada a false cuando se sepa que todos los hilos productores recibieron el mensaje
     */
    public final static boolean FORCE_PULL = false;
    
    /**
     * Buffer compartido en donde los hilos productores colocarán la información y el hilo consumidor los agregará al listado principal del usuario y limpiará el buffer
     */
    public final static LinkedList <RSSData> RSS_BUFFER = new LinkedList <RSSData> ();
}