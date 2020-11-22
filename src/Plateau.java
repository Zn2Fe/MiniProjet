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

    public void autoDeplacementMonster(int x, int y){
        if(x !=GameData.baseX){
            int x2 = GameData.baseX - x < 0 ? (x - 2) : (x + 2);
            if(this.sontValides(x2,y) && this.caseEstVide(x2,y)){
                this.deplacer(x2,y,this.getCase(x,y));
                return;
            }
            int x1 = GameData.baseX - x < 0 ? (x - 1) : (x + 1);
            if(this.caseEstVide(x1,y)){
                this.deplacer(x1,y,this.getCase(x,y));
                return;
            }
        }
        if(y!=GameData.baseY){
            int y2 = GameData.baseY - y < 0 ? (y - 2) : (y + 2);
            if(this.sontValides(x, y2) && this.caseEstVide(x, y2)){
                this.deplacer(x, y2,this.getCase(x,y));
                return;
            }
            int y1= GameData.baseY-y<0 ? (y-1):(y+1);
            if(this.sontValides(x,y1)){
                this.deplacer(x,y1,this.getCase(x,y));
                return;
            }
        }
    }

    private boolean sontValides(int x,int y){
        return (x>=0 && x<=nbLignes && y>=0 && y<=nbColonnes);
    }
}
