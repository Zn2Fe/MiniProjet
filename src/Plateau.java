import java.util.ArrayList;
import java.util.Arrays;

/**
 * Un pplateau sur lequel est la base, et se d&eacute;place monstre et soldat
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 */
public class Plateau {
    public static final int NBLIGNESMAX = 20;
    public static final int NBCOLONNESMAX = 20;

    public final int nbLignes;
    public final int nbColonnes;
    private final Agent[][] plateau;

    /**
     * Constructeur prenant la taille du tableau en entr&eacute;e.
     * @param nbLignes nombre de ligne du tableau
     * @param nbColonnes nomre de lignes du tableau
     */
    public Plateau(int nbLignes, int nbColonnes) {
        if(nbLignes > NBLIGNESMAX) {
            this.nbLignes = 20;
        } else if (nbLignes <= 0) {
            this.nbLignes = 1;
        } else {
            this.nbLignes = nbLignes;
        }
        if (nbColonnes > NBCOLONNESMAX) {
            this.nbColonnes = 20;
        } else if (nbColonnes <= 0) {
            this.nbColonnes = 1;
        } else {
            this.nbColonnes = nbColonnes;
        }
        this.plateau = new Agent[this.nbLignes][this.nbColonnes];
    }

    /**
     * Essaye de sortir les soldats de la base
     * @param soldatArrayList liste des soldats en vie
     * @return la liste des soldats en vie d&eacute;plac&eacute; si possible
     */
    public ArrayList<Soldat> placeSoldat(ArrayList<Soldat> soldatArrayList){
        for(int i = GameData.baseX-1;i<GameData.baseX+2;i++){
            for(int j = GameData.baseY-1;j<GameData.baseY+2;j++){
                if(this.caseEstVide(i,j)){
                    for(Soldat soldat:soldatArrayList){
                        if(soldat.getX()==-1){
                            this.deplacer(i,j,soldat);
                            break;
                        }
                    }
                }
            }
        }
        return soldatArrayList;
    }

    /**
     * Retourne true si un monstre est dans les 9 blocs compris autour de la case (x,y)
     * x x x
     * x o x
     * x x x
     * @param x abscisse
     * @param y ordonn&eacute;es
     * @return Boolean
     */
    private boolean isMonsterNear(int x, int y){
        for(int i=x-1;i<x+2;i++){
            for (int j=y-1;j<y+2;j++){
                if(this.sontValides(i,j)&&this.getCase(i,j) instanceof Monstre){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retourne la liste des soldats dans les 9 blocs compris autour de la case (x,y)
     * @param x abscisse
     * @param y ordonn&eacute;es
     * @return liste de soldats
     */
    private ArrayList<Soldat> soldatsNear(int x, int y){
        ArrayList<Soldat> soldats = new ArrayList<>();
        for(int i=x-1;i<x+2;i++){
            for (int j=y-1;j<y+2;j++){
                if(this.sontValides(i,j)&&this.getCase(i,j) instanceof Soldat){
                    soldats.add((Soldat) this.getCase(i,j));
                }
            }
        }
        return soldats;
    }

    /**
     * Liste de l'ensemble des monstres
     * @return liste de monstre
     */
    private ArrayList<Monstre> getAllMonster(){
        ArrayList<Monstre> monstres = new ArrayList<>();
        for(Agent[] agents:plateau){
            for (Agent agent:agents){
                if(agent instanceof Monstre){
                    monstres.add((Monstre)agent);
                }
            }
        }
        return monstres;
    }

    /**
     * Gere les d&eacute;placements des soldats pourqu'il se dirige vers le monstre le plus proche
     * @param soldat Un soldat qui va se d&eacute;placer
     */
    public void soldatMoveToNearestMonster(Soldat soldat){
        int x = soldat.getX();
        int y = soldat.getY();
        if(isMonsterNear(x,y)){
            return;
        }
        for(int i=soldat.getX()-2;i<soldat.getX()+2;i++){
            for(int j=soldat.getY()-2;j<soldat.getY()+2;j++){
                if(sontValides(i,j)&&isMonsterNear(i,j)&&this.caseEstVide(i,j)){
                    x=i;
                    y=j;
                }
            }
        }
        this.deplacer(x,y,soldat);
    }

    /**
     * Parcours les monstres et v&eacute;rifie si il s'engage dans un combat, fait le combat le cas &eacute;ch&eacute;ant
     * Parcours les monstres et v&eacute;rifie si il s'engage dans un combat, fait le combat le cas &eacute;ch&eacute;ant
     */
    public void soldatAttackMonster(){
        ArrayList<Monstre> monstres = getAllMonster();
        for(Monstre monstre:monstres){
            ArrayList<Soldat> soldats = soldatsNear(monstre.getX(),monstre.getY());
            if(soldats.size()!=0) {
                int soldatCombatPower = 0;
                for(Soldat soldat:soldats){
                    soldatCombatPower+=soldat.getCombatPower();
                }
                if(soldatCombatPower<GameData.rng(0,11)){
                    this.videCase(soldats.get(0).getX(), soldats.get(0).getY());
                    System.out.println(soldats.get(0).nom + " est mort face a un monstre");
                }
                else{
                    this.videCase(monstre.getX(),monstre.getY());
                    System.out.println(soldats.get(0).nom + " a gagné face a un monstre");
                }
            }

        }
    }

    /**
     * lance le d&eacute;placements automatique des monstres
     */
    public void monsterMovement(){
        for(Monstre monstre:getAllMonster()){
            this.autoMonsterMovement(monstre);
        }
    }

    /**
     * Gere le d&eacute;placement automatique du monster en param&ecirc;tre
     * @param monstre monstre se d&eacute;placant
     */
    private void autoMonsterMovement(Monstre monstre){
        int x=monstre.getX();
        int y=monstre.getY();
        if(x !=GameData.baseX){
            int x2 = GameData.baseX - x < 0 ? (x - 2) : (x + 2);
            if(this.sontValides(x2,y) && this.caseEstVide(x2,y) && Math.abs(GameData.baseX - x)!=1){
                this.deplacer(x2,y,monstre);
                return;
            }
            int x1 = GameData.baseX - x < 0 ? (x - 1) : (x + 1);
            if(this.caseEstVide(x1,y)){
                this.deplacer(x1,y,monstre);
                return;
            }
        }
        if(y!=GameData.baseY){
            int y2 = GameData.baseY - y < 0 ? (y - 2) : (y + 2);
            if(this.sontValides(x, y2) && this.caseEstVide(x, y2) && Math.abs(GameData.baseY - y)!=1){
                this.deplacer(x, y2,monstre);
                return;
            }
            int y1= GameData.baseY-y<0 ? (y-1):(y+1);
            if(this.sontValides(x,y1)){
                this.deplacer(x,y1,monstre);
            }
        }
    }

    /**
     * Fait perdre des points de vie &agrave; la base si un monstre s'approche trop
     * @param base base
     */
    public void monsterAttackBase(Base base){
        for(Monstre monstre:getAllMonster()){
            if(isMonsterNear(base.getX(), base.getY())){
                base.prendreUnCoup();
            }
        }
    }

    /**
     * D&eacute;place un agent sur le coordonn&eacute;es indiqu&eacute;s
     * @param x abscisse
     * @param y ordonn&eacute;es
     * @param agent agent &agrave; d&eacute;placer
     */
    public void deplacer(int x, int y, Agent agent){
        if(!this.sontValides(x,y)){
            return;
        }
        if(this.getCase(x,y)==null){
            if(!(agent.getY()==y&&agent.getY()==x)){
                this.videCase(agent.getX(),agent.getY());
            }
            this.plateau[x][y]= agent;
            agent.seDeplacer(x,y);
        }
    }

    /**
     * Vide le plateau de tous les agents sauf la base
     */
    public void videPlateau(){
        for(int i=0;i<nbLignes;i++){
            for(int j=0;j<nbColonnes;j++){
                if(!(plateau[i][j] instanceof Base)) {
                    videCase(i, j);
                }
            }
        }
    }

    /**
     * Vide la case de coordonn&eacute;es (x,y)
     * @param x abcisse
     * @param y ordonn&eacute;es
     */
    public void videCase(int x,int y){
        if(this.sontValides(x,y)){
            this.plateau[x][y]=null;
        }
    }

    /**
     * retourne ce que contient la case (x,y)
     * @param x abcisse
     * @param y ordonn&eacute;es
     * @return L'agentt aux cordon&eacute;es ou null
     */
    public Agent getCase(int x,int y){
        if(this.sontValides(x,y)){
            return this.plateau[x][y];
        }
        else{
            return null;
        }
    }

    /**
     * renvoie true si la case est vide
     * @param x abcisse
     * @param y ordonn&eacute;es
     * @return true si la case est vide
     */
    public boolean caseEstVide(int x, int y) {
        if (this.sontValides(x, y)) {
            return this.plateau[x][y] == null;
        } else {
            return true;
        }
    }

    /**
     * La pr&eacute;cense de monstre sur le terrain
     * @return true si un monstre est pr&eacute;sent sur le terrain
     */
    public boolean aliveMonster(){
        for(Agent[] l:this.plateau){
            for (Agent c:l){
                if(c instanceof Monstre){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Affiche le plateau dans la console
     */
    public void affiche() {
        String var1 = "";
        String horizontalLine = ":";
        String horizontalSeparator = "-----";

        for(int i = 0; i < this.nbColonnes; ++i) {
            horizontalLine = horizontalLine + horizontalSeparator + ":";
        }

        horizontalLine = horizontalLine + "\n";
        var1 = horizontalLine;

        for(int i = 0; i < this.nbLignes; ++i) {
            for(int var5 = 0; var5 < this.nbColonnes; ++var5) {
                if (this.plateau[i][var5] == null) {
                    var1 = var1 + "|" + String.format("%-5s", " ");
                } else {
                    var1 = var1 + "|" + (String.format("%-5s",this.plateau[i][var5].nom).substring(0,5));
                }
            }

            var1 = var1 + "|\n" + horizontalLine;
        }
        System.out.println(var1);
    }
    private boolean sontValides(int x,int y){
        return (x>=0 && x<nbLignes && y>=0 && y<nbColonnes);
    }

    /**
     * Tostring pour l'ide
     * @return String
     */
    @Override
    public String toString() {
        return "Plateau{" +
                "nbLignes=" + nbLignes +
                ", nbColonnes=" + nbColonnes +
                "plateau contient"+this.getAllMonster()+"Monstres"+
                '}';
    }
}
