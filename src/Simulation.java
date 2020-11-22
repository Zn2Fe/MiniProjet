import java.util.ArrayList;

public class Simulation {
    public Terrain terrain;
    public ArrayList<Ressource> ressources;
    public Plateau plateau;
    public Base base;

    public Simulation(int nbLignes,int nbColones,int baseX,int baseY){

        if(nbLignes<5||nbColones<5){
            nbLignes=20;
            nbColones=20;
        }

        int nbMonstre = (nbLignes*nbColones)/GameData.MONSTER_DENSITY;
        int nbRessources = (nbLignes*nbColones)/GameData.RESSOURCE_DENSITY;
        int nbAgent = (nbLignes*nbColones)/GameData.AGENT_DENSITY;

        this.terrain=new Terrain(nbLignes,nbColones);
        this.plateau=new Plateau(nbLignes,nbColones);
        this.ressources = new ArrayList<Ressource>();
        this.base = new Base();
        this.plateau.Deplacer(baseX,baseY,this.base);

        for (int i=0;i<nbRessources;i++){
            ressources.add(GameData.newRandomRessource());
        }
        for (Ressource item:ressources){
            int x = GameData.rng(0,terrain.nbLignes);
            int y = GameData.rng(0,terrain.nbColonnes);
            while(terrain.caseEstVide(x,y)){
                x = GameData.rng(0,plateau.nbLignes);
                y =GameData.rng(0,plateau.nbColonnes);
            }
            terrain.setCase(x,y,item);
        }
        for (int i=0;i<nbMonstre;i++){
            int x = GameData.rng(0,plateau.nbLignes);
            int y =GameData.rng(0,plateau.nbColonnes);
            while(plateau.getCase(x,y)!=null){
                x = GameData.rng(0,plateau.nbLignes);
                y =GameData.rng(0,plateau.nbColonnes);
            }
            this.plateau.Deplacer(x,y,GameData.newRandomMonster(x,y));
        }

    }

}
