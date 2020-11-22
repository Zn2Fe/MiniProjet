import java.util.ArrayList;

public class Simulation {
    public final Terrain terrain;
    public final Plateau plateau;
    public final Base base;
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
        this.nbMonstre = 1;

        int nbAgent = (nbLignes*nbColonnes)/GameData.AGENT_DENSITY;
        //Mets les agents sur la base
        for (int i=0;i<nbAgent;i++){
            base.stockageAgent(GameData.newRandomAgent());
        }
    }


    public String tourJour(){
        initJour();
        this.terrain.affiche();
        ArrayList<Ouvrier> ouvriers = base.getAllOuvrier();
        for(Ouvrier o:ouvriers ){
            while(o.inventaireVide()){
                Ressource nearestRessource = getNearestRessource(o);
                if(nearestRessource==null){
                    break;
                }
                if(!(nearestRessource.getX()-GameData.baseX+nearestRessource.getY()-GameData.baseY>o.getPm())){
                    o.seDeplacer(nearestRessource.getX(),nearestRessource.getY());
                    Ressource var1 = terrain.videCase(o.getX(),o.getY());
                    o.addRessource(var1);
                }
                else{
                    break;
                }
            }
            o.seDeplacer(GameData.baseX,GameData.baseY);
            o.reinitPm();
            base.stockageAgent(o);
        }
        base.craftAll();
        this.terrain.affiche();
        return "fin du jour";
    }
    private Ressource getNearestRessource(Ouvrier o){
        ArrayList<Ressource> ressources = new ArrayList<>();
        for(int i=0;i< terrain.nbLignes;i++){
            for(int j=0;j< terrain.nbColonnes;j++){
                if(!terrain.caseEstVide(i,j)){
                    ressources.add(terrain.getCase(i,j));
                }
            }
        }
        if(ressources.size()==0){
            return null;
        }
        Ressource nearestRessource = ressources.get(0);
        for(Ressource ressourceItem:ressources){
            if(o.distance(ressourceItem.getX(),ressourceItem.getY()) <
                    o.distance(nearestRessource.getX(),nearestRessource.getY())){
                nearestRessource = ressourceItem;
            }
        }
        return nearestRessource;
    }

    private void initJour(){
        base.videInventaire();
        int nbRessource = (terrain.nbLignes* terrain.nbColonnes)/GameData.RESSOURCE_DENSITY;
        for(int i=0;i< terrain.nbLignes;i++){
            for(int j=0;j < terrain.nbColonnes;j++){
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


    public String tourNuit(){
        initNuit();
        ArrayList<Soldat> soldats = base.getAllSoldat();

        while(plateau.aliveMonster()&&base.getCurrentHp()>0){
            plateau.affiche();
            soldats=plateau.placeSoldat(soldats);
            plateau.affiche();
            for(Soldat soldat:soldats){
                if(soldat.getX()!=-1){
                    plateau.soldatMoveToNearestMonster(soldat);
                }
            }
            plateau.soldatAttackMonster();
            plateau.monsterMovement();
            plateau.monsterAttackBase(this.base);
            plateau.affiche();
            System.out.println("\n\nIl reste "+this.base.getCurrentHp()+" vie à la base");
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
            int x = GameData.rng(0,plateau.nbLignes);
            int y = GameData.rng(0,plateau.nbColonnes);
            while(!plateau.caseEstVide(x,y)|| base.distance(x,y)<2){
                x = GameData.rng(0,plateau.nbLignes);
                y = GameData.rng(0,plateau.nbColonnes);
            }
            this.plateau.deplacer(x,y,GameData.newRandomMonster());
        }
    }

}
