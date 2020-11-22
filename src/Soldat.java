import java.util.ArrayList;

public class Soldat extends Agent {
    private final int capacite = 1;
    private String nom;

    public Soldat() {
        super(3, -1, -1);
        this.inventaire = new ArrayList<Ressource>();
        this.nom = Nom.genereNom();
    }

    public void ramasse(Ressource e) {
        if (inventaire.size() < capacite)
            inventaire.add(e);
    }

    public void drop(Ressource e) {
        if (!inventaire.isEmpty())
            inventaire.remove(e);
    }

}

