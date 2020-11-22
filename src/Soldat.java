import java.util.ArrayList;

public class Soldat extends Agent {
    private final String nom;

    public Soldat() {
        super(3, -1, -1);
        this.inventaire = new ArrayList<>();
        this.nom = Nom.genereNom();
    }

    public Soldat(Soldat s){
        super(s.finalPm,s.getX(),s.getY());
        this.nom=s.nom;
        this.inventaire= s.inventaire;
    }

    public int getCombatPower(){
        return this.inventaire.size()==0 ? 2:4;
    }

}

