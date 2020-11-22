import java.util.ArrayList;

public class Ouvrier extends Agent {
    private final int capacite = 3;
    private String nom;

    public Ouvrier() {
        super(5, -1, -1);
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

    public void craftSword(Ressource e) {
        if (e.getQuantite() == 3 && e.type.equals(GameData.RESSOURCE_TYPE[1][0]) && getX() == -1 && getY() == -1)
            new Ressource(GameData.RESSOURCE_TYPE[0][1], 1);
    }

}


