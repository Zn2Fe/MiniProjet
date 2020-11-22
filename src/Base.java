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
    public ArrayList<Soldat> getAllSoldat(){
        ArrayList<Soldat> soldat = new ArrayList<>();
        for(Agent s:infanterie){
            if(s instanceof Soldat){
                soldat.add(new Soldat((Soldat) s));
            }
        }
        return soldat;
    }
    public ArrayList<Ouvrier> getAllOuvrier(){
        ArrayList<Ouvrier> ouvriers = new ArrayList<>();
        for(Agent o:infanterie){
            if(o instanceof Ouvrier){
                ouvriers.add(new Ouvrier((Ouvrier) o));
            }
        }
        return ouvriers;
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


}
