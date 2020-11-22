import java.util.ArrayList;

public class Simulation {
    public Terrain terrain;
    public ArrayList<Ressource> ressources;
    public Plateau plateau;
    public Base base;

    /**
     * Genere Une simulation contenant un terrain sur laquelle sont g&eacute;n&eacute;r&eacute;es des ressources al&eacute;atoire,
     * Un plateau contenant une base et des monstres
     * @param nbLignes nombre de lignes du plateau et du terrain
     * @param nbColonnes nombre de colonnes du plateau et du terrain
     * @param baseX abscisse de la base
     * @param baseY ordonn&eacute;es de la base
     */
    public Simulation(int nbLignes,int nbColonnes,int baseX,int baseY){
        //définit le nomre de ligne et colonnes minimum à 5
        if(nbLignes<5||nbColonnes<5){
            nbLignes=20;
            nbColonnes=20;
        }
        //initalise les terrain, plateau, ressources,et base
        this.terrain=new Terrain(nbLignes,nbColonnes);
        this.plateau=new Plateau(nbLignes,nbColonnes);
        this.ressources = new ArrayList<Ressource>();
        this.base = new Base(baseX,baseY);
        this.plateau.Deplacer(baseX,baseY,this.base);

        //definit le nombre de monstre, ressources et autre sur le plateau
        int nbMonstre = (nbLignes*nbColonnes)/GameData.MONSTER_DENSITY;
        int nbRessources = (nbLignes*nbColonnes)/GameData.RESSOURCE_DENSITY;
        int nbAgent = (nbLignes*nbColonnes)/GameData.AGENT_DENSITY;

        //Génére les ressources et les stockent
        for (int i=0;i<nbRessources;i++){
            ressources.add(GameData.newRandomRessource());
        }
        //les placent sur le plateau
        for (Ressource item:ressources){
            int x = GameData.rng(0,terrain.nbLignes);
            int y = GameData.rng(0,terrain.nbColonnes);
            while(terrain.caseEstVide(x,y)){
                x = GameData.rng(0,plateau.nbLignes);
                y =GameData.rng(0,plateau.nbColonnes);
            }
            terrain.setCase(x,y,item);
        }
        //Génére et place les monstres sur le plateau
        for (int i=0;i<nbMonstre;i++){
            int x = GameData.rng(0,plateau.nbLignes);
            int y =GameData.rng(0,plateau.nbColonnes);
            while(plateau.getCase(x,y)!=null){
                x = GameData.rng(0,plateau.nbLignes);
                y =GameData.rng(0,plateau.nbColonnes);
            }
            this.plateau.Deplacer(x,y,GameData.newRandomMonster(x,y));
        }
        //Mets les agents sur la base
        for (int i=0;i<nbAgent;i++){
            base.stockageAgent(GameData.newRandomAgent());
        }
    }

    /**
     * Réalise un tour de simulation
     */
    public String tour(){
        if(this.base.getCurrentHp()==0){
            return "Impossible, jeu terminé";
        }
        for (int lignes=0;lignes<plateau.nbLignes;lignes++){
            for(int colonnes=0;colonnes<plateau.nbColonnes;colonnes++){
                if(plateau.getCase(lignes,colonnes) instanceof Monstre){
                    plateau.getCase(lignes,colonnes).autoDeplacement();
                }
            }
        }
        for (int lignes=0;lignes<plateau.nbLignes;lignes++){
            for(int colonnes=0;colonnes<plateau.nbColonnes;colonnes++){
                if(!(plateau.getCase(lignes,colonnes) instanceof Monstre)){
                    plateau.getCase(lignes,colonnes).autoDeplacement();
                }
            }
        }
        for (int lignes=0;lignes<plateau.nbLignes;lignes++){
            for(int colonnes=0;colonnes<plateau.nbColonnes;colonnes++){
                if(!(plateau.getCase(lignes,colonnes) instanceof Monstre)){
                    plateau.getCase(lignes,colonnes).autoAction();
                }
            }
        }
        if(this.base.getCurrentHp()==0){
            return "Défaite";
        }
        if(!plateau.aliveMonster()){
            return "Victoire";
        }
        return "Tour Fini";
    }

}
