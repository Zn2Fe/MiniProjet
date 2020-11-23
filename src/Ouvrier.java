import java.util.ArrayList;

public class Ouvrier extends Agent {
    public static int nbDeRecolte;
    public static int nbDeGeneration;
    private final int capacite =3;
    private int pm;

    public Ouvrier() {
        super(50, -1, -1, Nom.genereNom());
        this.inventaire = new ArrayList<>(capacite);
        this.pm=50;
    }

    @Override
    public void seDeplacer(int x, int y) {
        if(this.pm > Math.abs(this.getX()-x)+Math.abs(this.getX()-y)) {
            this.pm -= (Math.abs(this.getX() - x) + Math.abs(this.getX() - y));
            super.seDeplacer(x, y);
        }
    }

    public int getPm() {
        return pm;
    }

    public void addRessource(Ressource e) {
        if (inventairePasPlein()) {
            inventaire.add(e);
            Ouvrier.nbDeRecolte+=1;
        }
    }

    public void reinitPm(){
        this.pm=finalPm;
    }
    public void craftAllSword() {
        ArrayList<Ressource> ressources = new ArrayList<>();
        for(Ressource ressource:this.inventaire){
            if(ressource.type.equals(GameData.RESSOURCE_TYPE[1][0])){
                ressources.add(ressource);
            }
        }
        int nbMetal=0;
        if(ressources.size()!=0){
            for (Ressource ressource:ressources){
                nbMetal+=ressource.getQuantite();
            }
        }
        this.inventaire.removeAll(ressources);
        for(int i=0;i<nbMetal/3;i++){
            this.addRessource(new Ressource(GameData.RESSOURCE_TYPE[0][1],1));
            Ouvrier.nbDeGeneration+=1;
        }
    }

    public boolean inventairePasPlein() {
        return inventaire.size() < capacite;
    }


    }




