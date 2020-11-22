import java.util.ArrayList;

public class Simulation {
    public Terrain terrain;
    public Plateau plateau;
    public Base base;
    public int nbMonstre;

    /**
     * Genere Une simulation contenant un terrain sur laquelle sont g&eacute;n&eacute;r&eacute;es des ressources al&eacute;atoire,
     * Un plateau contenant une base et des monstres
     * @param nbLignes nombre de lignes du plateau et du terrain
     * @param nbColonnes nombre de colonnes du plateau et du terrain

     */
    public Simulation(int nbLignes,int nbColonnes){
        //définit le nomre de ligne et colonnes minimum à 5
        if(nbLignes<5||nbColonnes<5){
            nbLignes=20;
            nbColonnes=20;
        }
        //initalise les terrain, plateau, ressources,et base
        this.terrain=new Terrain(nbLignes,nbColonnes);
        this.plateau=new Plateau(nbLignes,nbColonnes);
        this.base = new Base(GameData.baseX,GameData.baseY);
        this.plateau.deplacer(GameData.baseX,GameData.baseY,this.base);

        //definit le nombre de monstre, ressources et autre sur le plateau
        this.nbMonstre = (nbLignes*nbColonnes)/GameData.MONSTER_DENSITY;

        int nbAgent = (nbLignes*nbColonnes)/GameData.AGENT_DENSITY;
        //Mets les agents sur la base
        for (int i=0;i<nbAgent;i++){
            base.stockageAgent(GameData.newRandomAgent());
        }
    }


    public String tourJour(){
        initJour();
        ArrayList<Ouvrier> ouvriers = base.getAllOuvrier();
        for(Ouvrier o:ouvriers){
            while(/*est ce que l'ouvrier a de la place*/){
              /*trouev la ressource la plus proche*/
              /*verifie qu'il peut rentrer-> break */
              /*va a la ressource la plus proche*/
            }
            /*rentre*/
        }
    }

    private void initJour(){
        int nbRessource = (terrain.nbLignes* terrain.nbColonnes)/GameData.RESSOURCE_DENSITY;
        for(int i=0;i< terrain.nbLignes;i++){
            for(int j=0;i< terrain.nbColonnes;j++){
                terrain.videCase(i,j);
            }
        }
        for(int i=0;i<nbRessource;i++){
            int x = GameData.rng(0, terrain.nbLignes);
            int y = GameData.rng(0, terrain.nbColonnes);
            while (!terrain.caseEstVide(x, y)) {
                x = GameData.rng(0, plateau.nbLignes);
                y = GameData.rng(0, plateau.nbColonnes);
            }
            terrain.setCase(x,y,GameData.newRandomRessource());
        }
    }

    public String tourCraft(){}
    public String tourNuit(){
        initNuit();
        ArrayList<Soldat> soldats = base.getAllSoldat();
        while(plateau.aliveMonster()&&soldats.size()!=0){
            plateau.placeSoldat(soldats);
            for(Soldat soldat:soldats){
                if(soldat.getX()!=-1){
                    plateau.soldatMoveToNearestMonster(soldat);
                }
            }
            plateau.soldatAttackMonster();
            plateau.monsterMovement();
            plateau.monsterAttackBase(this.base);
        }
        if(plateau.aliveMonster()){
            return "Défaite";
        }
        return "Victoire";
    }


    private void initNuit(){
        this.nbMonstre+=1;
        //Génére et place les monstres sur le plateau
        for (int i=0;i<nbMonstre;i++){
            Monstre monster = GameData.newRandomMonster();
            while(plateau.caseEstVide(monster.getX(),monster.getY()) && monster.distance(GameData.baseX,GameData.baseY)<2){
                this.plateau.deplacer(GameData.rng(0,plateau.nbLignes), GameData.rng(0,plateau.nbColonnes), monster);
            }
        }
    }

}
