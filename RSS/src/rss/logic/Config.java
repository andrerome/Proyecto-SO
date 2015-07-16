package rss.logic;

public abstract class Config {
    // TODO: Leer configuración desde archivo
    
    /**
     * Tiempo a esperar para vaciar el buffer y pasar las nuevas entradas al feed principal
     */
    public final static long USER_FEED_UPDATE_INTERVAL = 1000; // 1 segundo

    /**
     * Tiempo a esperar entre pulls
     * Nota: 1 minute = 60000 milliseconds
     */
    public static long TIME_TO_WAIT = 5000; // 5 segundos
    
    /**
     * Buffer compartido en donde los hilos productores colocarán la información y de donde el hilo
     * consumidor sacará posts para agregarlos al listado principal del usuario y limpiará el buffer
     */
    public final static RSSBuffer RSS_BUFFER = new RSSBuffer();
}