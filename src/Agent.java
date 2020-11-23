import java.util.ArrayList;

/**
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 * Classe g&eacute;n&eacute;rique repr&eacute;sentant une entit&eacute; pouvant se d&eacute;placer et ramasser des ressources.
 * A une
 * Poss&eacute;de des coordonn&eacute;es, un nom et un inventaire
 */
public class Agent {
    protected final int finalMovementPoint;
    private int x;
    private int y;
    protected String nom;
    protected ArrayList<Ressource> inventaire;

    /**
     * Constructeur d'agent, tous les champs sont requis et suffisant
     * @param fpm Distance maximal de déplacement de l'agent
     * @param x abscisse de l'agent
     * @param y ordonnées de l'agent
     * @param nom nom de l'agent
     */
    public Agent(int fpm,int x,int y,String nom) {
        this.finalMovementPoint = fpm;
        this.x=x;
        this.y=y;
        this.nom=nom;
    }

    /**
     * Getter
     * @return abscisse de l'agent
     */
    public int getX() {
        return x;
    }

    /**
     * Getter
     * @return ordonnés de l'agent
     */
    public int getY() {
        return y;
    }

    /**
     * Permet de déplacer l'agent aux coordonées indiqués
     * @param x abscice de la case d'arriv&eacute;e
     * @param y ordonn&eacute;es de la case d'arriv&eacute;e
     */
    public void seDeplacer(int x,int y){
            this.x = x;
            this.y = y;
    }
    /**
     * Retourne la distance euclidienne entre le point de coordonnées (x,y) et l'agent
     * @param x abscisse
     * @param y ordonnées
     * @return une distance
     */
    public double distance(int x, int y){
        return Math.sqrt((x-this.x)^2+(y-this.y)^2);
    }

    /**
     * To String (pour l'ide)
     * @return String
     */
    @Override
    public String toString() {
        return "Agent{" +
                "x=" + x +
                ", y=" + y +
                ", nom='" + nom + '\'' +
                ", inventaire=" + inventaire +
                '}';
    }
}
