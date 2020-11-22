import java.util.ArrayList;

public class Soldat extends Agent {

    public Soldat() {
        super(3, -1, -1,Nom.genereNom());
        this.inventaire = new ArrayList<>();
    }

    public Soldat(Soldat s){
        super(s.finalPm,s.getX(),s.getY(), s.nom);
        this.nom=s.nom;
        this.inventaire= s.inventaire;
    }

    public int getCombatPower(){
        return this.inventaire.size()==0 ? 6:9;
    }

}

