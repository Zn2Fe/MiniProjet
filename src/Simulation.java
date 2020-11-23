import java.util.ArrayList;
/**
 * Une simulation classe principale
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 */
public class Simulation {
    public final Terrain terrain;
    public final Plateau plateau;
    public final Base base;
    public int nbMonstre;
    public int round;

    public Simulation(){
        this(20,20);
    }
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
        this.round=0;
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

    /**
     * Simule un Tour de rammassage de ressource
     */
    public void tourJour(){
        initJour();
        System.out.println("Le terrain se remplit de ressources ...");
        this.terrain.affiche();
        ArrayList<Ouvrier> ouvriers = base.getAllOuvrier();
        for(Ouvrier ouvrier:ouvriers){
            ouvrier.videInventaire();
        }
        for(Ouvrier o:ouvriers ){
            while(o.inventairePasPlein()){
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
        System.out.println("Vos ouvriers ont récoltés des ressources et créer de l'équipement pour vos Soldats");
        this.terrain.affiche();
        System.out.println("La nuit Tombe ... \n\n");
    }

    /**
     * renvoie la ressource la plus proche
     * @param o Ouvrier
     * @return ressource la plus proche de lui
     */
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

    /**
     * Initialise les ressources sur le terrain
     */
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

    /**
     * Simule un tour de combat
     * @return true en cas de défaite, false sinon
     */
    public boolean tourNuit(){
        initNuit();
        plateau.affiche();
        ArrayList<Soldat> soldats = base.getAllSoldat();
        for(Soldat so:soldats){
            if(this.base.inventaire.size()!=0){
                so.inventaire.add(this.base.inventaire.get(0));
                this.base.inventaire.remove(0);
            }
        }
        System.out.println("Vos soldats vont défendre votre base");
        while(plateau.aliveMonster()&&base.getCurrentHp()>0){
            soldats=plateau.placeSoldat(soldats);
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
            System.out.println("Vos soldats n'ont pas réussi a protéger votre base");
            return true;
        }
        round+=1;
        System.out.println("\nIl reste "+this.base.getCurrentHp()+" vie à la base");
        return false;
    }

    /**
     * initalise le plateau avec des monstres (suffisament &eacute;loign&eacute;s de la base)
     */
    private void initNuit(){
        plateau.videPlateau();
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
