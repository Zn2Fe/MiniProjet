import java.util.ArrayList;

public class Ouvrier extends Agent {
    private final int capacite = 5;
    private String nom;
    private int pm;

    public Ouvrier() {
        super(50, -1, -1);
        this.inventaire = new ArrayList<>(capacite);
        this.nom = Nom.genereNom();
    }

    @Override
    public void seDeplacer(int x, int y) {
        if(this.pm < Math.abs(this.getX()-x)+Math.abs(this.getX()-y)) {
            this.pm -= (Math.abs(this.getX() - x) + Math.abs(this.getX() - y));
            super.seDeplacer(x, y);
        }
    }

    public int getPm() {
        return pm;
    }

    public void ramasse(Ressource e) {
        if (inventairePlein())
            inventaire.add(e);
    }

    public void drop(Ressource e) {
        if (!inventaire.isEmpty())
            inventaire.remove(e);
    }

    public void craftAllSword() {
        ArrayList<Ressource> ressources = new ArrayList<>();
        for(Ressource ressource:this.inventaire){
            if(ressource.type.equals(GameData.RESSOURCE_TYPE[1][0])){
                ressources.add(ressource);
            }
        }
        if(ressources.size()!=0){
            int nbMetal=0;
            for (Ressource ressource:ressources){
                nbMetal+=ressource.getQuantite();
            }
            for(int i=0;i<nbMetal%3;i++){
                this.ramasse(new Ressource(GameData.RESSOURCE_TYPE[0][1],1));
            }
        }
        for(Ressource ressource:this.inventaire){
            if(ressource.type.equals(GameData.RESSOURCE_TYPE[1][0])){
                this.drop(ressource);
            }
        }
    }

    public boolean inventairePlein() {
        return inventaire.size() < capacite;
    }


    }




