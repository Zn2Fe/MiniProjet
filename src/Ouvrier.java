import java.util.ArrayList;

public class Ouvrier extends Agent {
    private final int capacite = 5;
    private String nom;

    public Ouvrier() {
        super(50, -1, -1);
        this.inventaire = new ArrayList<Ressource>();
        this.nom = Nom.genereNom();
    }

    public Ouvrier(Ouvrier o) {
        super(o.finalPm,o.getX(), o.getY());
        this.nom = o.nom;
        this.inventaire = o.inventaire;
    }

    public void ramasse(Ressource e) {
        if (!inventairePlein())
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

    public boolean inventairePlein() {
        if (inventaire.size() < capacite)
            return false;
        else
            return true;
    }


    }




