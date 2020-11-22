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

    public void Deplacer(int x,int y,Agent agent){
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

    private boolean sontValides(int x,int y){
        return (x>=0 && x<=nbLignes && y>=0 && y<=nbColonnes);
    }
}
