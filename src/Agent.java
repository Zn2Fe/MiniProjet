/**
 * @author Nicolas Ramy
 */
public class Agent {
    private final int finalPm;

    private int x;
    private int y;
    private int pm;

    protected Ressource[] inventaire;

    /**
     *
     * @param fpm Nombre de point de d&eacute;placement par tour de l'agent
     */
    public Agent(int fpm) {
        this.finalPm = fpm;
        this.pm = fpm;
    }

    /**
     *
     * @param x abscice de la case d'arriv&eacute;e
     * @param y ordonn&eacute;es de la case d'arriv&eacute;e
     */
    public void seDeplacer(int x,int y){
        if(this.pm < Math.abs(this.x-x)+Math.abs(this.y-y)){
            this.pm -= (Math.abs(this.x-x)+Math.abs(this.y-y));
            this.x = x;
            this.y = y;
        }
    }

    public double distance(int x, int y){
        return Math.sqrt((x-this.x)^2+(y-this.y)^2);
    }
}
