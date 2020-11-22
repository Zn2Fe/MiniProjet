import java.util.ArrayList;

/**
 * @author Nicolas Ramy
 */
public class Agent {
    protected final int finalPm;

    private int x;
    private int y;

    protected ArrayList<Ressource> inventaire;

    /**
     *
     * @param fpm Nombre de point de d&eacute;placement par tour de l'agent
     */
    public Agent(int fpm,int x,int y) {
        this.finalPm = fpm;
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     *
     * @param x abscice de la case d'arriv&eacute;e
     * @param y ordonn&eacute;es de la case d'arriv&eacute;e
     */
    public void seDeplacer(int x,int y){
            this.x = x;
            this.y = y;
    }

    public double distance(int x, int y){
        return Math.sqrt((x-this.x)^2+(y-this.y)^2);
    }
}
