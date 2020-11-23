import java.util.ArrayList;

/**
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 * Un agent qui ne peut pas se d&eacute;placer, contenant un inventaire de taille infini et avec un nombre de point de vie limit&eacute;
 * Peut contenir d'autres agents
 */
public class Base extends Agent{
    private final ArrayList<Agent> infanterie;
    private int currentHp;

    /**
     * Constructeur, d&eacute;finit le nombre de point de vie a 3 et le nom &agrave; "Base"
     * @param x abscisse de la base
     * @param y ordonn&eacute;es de la base
     */
    public Base(int x, int y) {
        super(0, x ,y," Base");
        this.inventaire = new ArrayList<>();
        this.infanterie = new ArrayList<>();
        this.currentHp = 3;

    }

    /**
     * Stock un agent dans l'infanterie de la base (le d&eacute;place en -1,-1)
     * @param a l'agent &agrave; stocker dans la base
     */
    public void stockageAgent(Agent a){
        a.seDeplacer(-1,-1);
        this.infanterie.add(a);
    }

    /**
     * demande &agrave; tous les ouvriers de la base de transformer leur m&eacute;tal en &eacute;p&eacute;e
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
     * Retire un hp &agrave; la base
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
     * vide l'inventaire de la base tous les matins
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
