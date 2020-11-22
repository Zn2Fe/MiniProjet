import java.util.ArrayList;

public class Plateau {
    public static final int NBLIGNESMAX = 20;
    public static final int NBCOLONNESMAX = 20;

    public final int nbLignes;
    public final int nbColonnes;
    private Agent[][] plateau;

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

    public void placeSoldat(ArrayList<Soldat> soldats){
        for(int i = GameData.baseX-1;i<GameData.baseX+1;i++){
            for(int j = GameData.baseY-1;j<GameData.baseY+1;i++){
                if(this.caseEstVide(i,j)){
                    for(Soldat soldat:soldats){
                        if(soldat.getX()==-1){
                            this.deplacer(i,j,soldat);
                        }
                    }
                }
            }
        }
    }

    public void soldatMoveToNearestMonster(Soldat soldat){
        int x = soldat.getX();
        int y = soldat.getY();
        for(int i=soldat.getX()-2;i<soldat.getX()+2;i++){
            for(int j=soldat.getY()-2;j<soldat.getY()+2;j++){
                if(isMonsterNear(i,j)&&this.caseEstVide(i,j)){
                    x=i;
                    y=j;
                }
            }
        }
        this.deplacer(x,y,soldat);
    }

    private boolean isMonsterNear(int x, int y){
        for(int i=x-1;i<x+1;i++){
            for (int j=y-1;y<y+1;j++){
                if(this.sontValides(i,j)&&this.getCase(i,j) instanceof Monstre){
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Soldat> soldatsNear(int x, int y){
        ArrayList<Soldat> soldats = new ArrayList<>();
        for(int i=x-1;i<x+1;i++){
            for (int j=y-1;y<y+1;j++){
                if(this.sontValides(i,j)&&this.getCase(i,j) instanceof Soldat){
                    soldats.add((Soldat) this.getCase(i,j));
                }
            }
        }
        return soldats;
    }

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

    public void soldatAttackMonster(){
        ArrayList<Monstre> monstres = getAllMonster();
        for(Monstre monstre:monstres){
            if(soldatsNear(monstre.getX(),monstre.getY()).size()!=0) {
                int soldatCombatPower = 0;
                for(Soldat soldat:soldatsNear(monstre.getX(),monstre.getY())){
                    soldatCombatPower+=soldat.getCombatPower();
                }
                if(soldatCombatPower+GameData.rng(-1,2)<3){
                    this.videCase(
                            soldatsNear(monstre.getX(),monstre.getY()).get(1).getX(),
                            soldatsNear(monstre.getX(),monstre.getY()).get(1).getY());
                }
                else{
                    this.videCase(monstre.getX(),monstre.getY());
                }
            }

        }
    }

    public void monsterMovement(){
        for(Monstre monstre:getAllMonster()){
            this.autoMonsterMovement(monstre);
        }
    }
    public void monsterAttackBase(Base base){
        for(Monstre monstre:getAllMonster()){
            if(monstre.distance(GameData.baseX,GameData.baseY)==1){
                base.prendreUnCoup();
            }
        }
    }

    public void deplacer(int x, int y, Agent agent){
        if(!this.sontValides(x,y)){
            return;
        }
        if(this.getCase(x,y)==null){
            this.videCase(agent.getX(),agent.getY());
            agent.seDeplacer(x,y);

        }
    }

    public void videCase(int x,int y){
        if(this.sontValides(x,y)){
            this.plateau[x][y]=null;
        }
    }

    public Agent getCase(int x,int y){
        if(this.sontValides(x,y)){
            return this.plateau[x][y];
        }
        else{
            return null;
        }
    }
    public boolean caseEstVide(int var1, int var2) {
        if (this.sontValides(var1, var2)) {
            return this.plateau[var1][var2] == null;
        } else {
            return true;
        }
    }
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

    private void autoMonsterMovement(Monstre monstre){
        int x=monstre.getX();
        int y=monstre.getY();
        if(x !=GameData.baseX){
            int x2 = GameData.baseX - x < 0 ? (x - 2) : (x + 2);
            if(this.sontValides(x2,y) && this.caseEstVide(x2,y)){
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
            if(this.sontValides(x, y2) && this.caseEstVide(x, y2)){
                this.deplacer(x, y2,monstre);
                return;
            }
            int y1= GameData.baseY-y<0 ? (y-1):(y+1);
            if(this.sontValides(x,y1)){
                this.deplacer(x,y1,monstre);
                return;
            }
        }
    }

    private boolean sontValides(int x,int y){
        return (x>=0 && x<=nbLignes && y>=0 && y<=nbColonnes);
    }
}
