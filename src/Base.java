import java.util.ArrayList;

public class Base extends Agent{
    private ArrayList<Agent> infanterie;
    private final int finalHp= 3;
    private int currentHp;

    public Base(int x, int y) {
        super(0, x ,y);
        this.inventaire = new ArrayList<Ressource>();
        this.infanterie = new ArrayList<Agent>();
        this.currentHp = finalHp;

    }

    public  void stockageAgent(Agent a){
        this.infanterie.add(a);
    }

    public void stockageRessource(Ressource r){
        this.inventaire.add(r);
        }
        public Soldat[] getAllSoldat(){

        }

    public void utilisationRessource(Ressource r){
        this.inventaire.remove(r);
    }

    public void prendreUnCoup() {
        this.currentHp--;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    /*public void destruction(){
        if (this.currentHp == 0)

    }*/


}
