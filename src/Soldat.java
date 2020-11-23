import java.util.ArrayList;

/**
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 * Un agent capable de se battre et d'augmenter ses performances avec des items qu'il ramasse
 */
public class Soldat extends Agent {

    /**
     * Constructeur par défaut: génére un noueau soldat
     */
    public Soldat() {
        super(3, -1, -1,Nom.genereNom());
        this.inventaire = new ArrayList<>();
    }

    /**
     * constructeur par copie pour les soldats sortie de la base
     * @param s Soldat à copier
     */
    public Soldat(Soldat s){
        super(s.finalMovementPoint,s.getX(),s.getY(), s.nom);
        this.nom=s.nom;
        this.inventaire= s.inventaire;
    }

    /**
     * retourne la puissance actuelle du soldat
     * @return un entier (la puissance du soldat)
     */
    public int getCombatPower(){
        return this.inventaire.size()==0 ? 6:9;
    }
    /**
     * Tostring pour l'ide
     * @return String
     */
    @Override
    public String toString() {
        return "Soldat{" +
                "finalMovementPoint=" + finalMovementPoint +
                ", nom='" + nom + '\'' +
                ", inventaire=" + inventaire +
                '}';
    }
}

