import java.util.ArrayList;
/**
 * Un type d'agent sp&eacute;cialis&eacute; dans le fait de d&eacute;placer et ramasser les ressources
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 */
public class Ouvrier extends Agent {
    public static int nbDeRecolte;
    public static int nbDeGeneration;
    private final int capacite =3;
    private int pm;

    /**
     * Constructeur g&eacute;n&eacute;rique
     * genere un Nom al&eacute;atoire
     * mets les points de mouvement &agrave; 50
     */
    public Ouvrier() {
        super(50, -1, -1, Nom.genereNom());
        this.inventaire = new ArrayList<>(capacite);
        this.pm=50;
    }

    /**
     * Se d&eacute;place en v&eacute;rifiant le nombre de d&eacute;placement restant.
     * @param x abscice de la case d'arriv&eacute;e
     * @param y ordonn&eacute;es de la case d'arriv&eacute;e
     */
    @Override
    public void seDeplacer(int x, int y) {
        if(this.pm > Math.abs(this.getX()-x)+Math.abs(this.getX()-y)) {
            this.pm -= (Math.abs(this.getX() - x) + Math.abs(this.getX() - y));
            super.seDeplacer(x, y);
        }
    }

    /**
     * Getter
     * @return les nomre de points mouvements
     */
    public int getPm() {
        return pm;
    }

    /**
     * Ajoute une ressource &agrave; l'inventaire du Ouvrier
     * @param e Une ressource
     */
    public void addRessource(Ressource e) {
        if (inventairePasPlein()) {
            inventaire.add(e);
            Ouvrier.nbDeRecolte+=1;
        }
    }

    /**
     * Vide l'inventaire de l'ouvrier
     */
    public void videInventaire() {
        this.inventaire.clear();
    }

    /**
     * reintialise les points mouvements &agrave; leurs valeurs intial
     */
    public void reinitPm(){
        this.pm= finalMovementPoint;
    }

    /**
     * Transforme tous les m&eacute;taux en &eacute;p&eacute;e
     */
    public void craftAllSword() {
        ArrayList<Ressource> ressourcesList = new ArrayList<>();
        for(Ressource ressource:this.inventaire){
            if(ressource.type.equals(GameData.RESSOURCE_TYPE[1][0])){
                ressourcesList.add(ressource);
            }
        }
        int nbMetal=0;
        if(ressourcesList.size()!=0){
            for (Ressource ressource:ressourcesList){
                nbMetal+=ressource.getQuantite();
            }
        }
        this.inventaire.removeAll(ressourcesList);
        for(int i=0;i<nbMetal/3;i++){
            this.addRessource(new Ressource(GameData.RESSOURCE_TYPE[0][1],1));
            Ouvrier.nbDeGeneration+=1;
        }
    }

    /**
     * Verifie l'&eacute;tat de l'inventaire
     * @return false si l'inventaire est plein
     */
    public boolean inventairePasPlein() {
        return inventaire.size() < capacite;
    }

    /**
     * Tostring pour l'ide
     * @return String
     */
    @Override
    public String toString() {
        return "Ouvrier{" +
                "capacite=" + capacite +
                ", pm=" + pm +
                ", nom='" + nom + '\'' +
                ", inventaire=" + inventaire +
                '}';
    }
}




