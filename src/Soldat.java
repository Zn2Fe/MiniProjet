import java.util.ArrayList;

public class Soldat extends Agent {
    private final int capacite = 1;
    private String nom;

    public Soldat() {
        super(3);
        this.inventaire = new ArrayList<Ressource>();
        this.nom = this.genereNom();
    }



}

