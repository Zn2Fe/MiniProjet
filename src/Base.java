import java.util.ArrayList;

/**
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 * Un agent qui ne peut pas se déplacer, contenant un inventaire de taille infini et avec un nombre de point de vie limité
 * Peut contenir d'autres agents
 */
public class Base extends Agent{
    private final ArrayList<Agent> infanterie;
    private int currentHp;

    /**
     * Constructeur, définit le nombre de point de vie a 3 et le nom à "Base"
     * @param x abscisse de la base
     * @param y ordonnées de la base
     */
    public Base(int x, int y) {
        super(0, x ,y," Base");
        this.inventaire = new ArrayList<>();
        this.infanterie = new ArrayList<>();
        this.currentHp = 3;

    }

    /**
     * Stock un agent dans l'infanterie de la base (le déplace en -1,-1)
     * @param a l'agent à stocker dans la base
     */
    public void stockageAgent(Agent a){
        a.seDeplacer(-1,-1);
        this.infanterie.add(a);
    }

    /**
     * demande à tous les ouvriers de la base de transformer leur métal en épée
     */
    public void craftAll(){
        for(Agent a:this.infanterie){
            if(a instanceof Ouvrier){
                ((Ouvrier) a).craftAllSword();
                for(Ressource ressource:a.inventaire){
                    if(ressource.type.equals(GameData.RESSOURCE_TYPE[0][1])){
                        this.inventaire.add(ressource);
                    }
                }
            }
        }

    }

    /**
     * Obtient une copie des soldats de la base
     * @return Une liste contenant une copie des soldats de la base
     */
    public ArrayList<Soldat> getAllSoldat(){
        ArrayList<Soldat> soldat = new ArrayList<>();
        for(Agent s:infanterie){
            if(s instanceof Soldat){
                soldat.add(new Soldat((Soldat) s));
            }
        }
        return soldat;
    }

    /**
     * Retourne les ouvriers contenu dans la base
     * @return la liste de tous les ouvriers
     */
    public ArrayList<Ouvrier> getAllOuvrier(){
        ArrayList<Ouvrier> ouvriers = new ArrayList<>();
        for(Agent o:infanterie){
            if(o instanceof Ouvrier){
                ouvriers.add((Ouvrier) o);
            }
        }
        infanterie.removeAll(ouvriers);
        return ouvriers;
    }

    /**
     * Retire un hp à la base
     */
    public void prendreUnCoup() {
        this.currentHp--;
        System.out.println("Votre base à pris un coup");
    }

    /**
     * Acesseurs
     * @return les points de vie actuel de la base
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * vide l'iinventaire de la base tous les matins
     */
    public void videInventaire(){
        inventaire.clear();
    }

    /**
     * Tostring pour l'ide
     * @return String
     */
    @Override
    public String toString() {
        return "Base{" +
                "infanterie=" + infanterie +
                ", currentHp=" + currentHp +
                '}';
    }
}
