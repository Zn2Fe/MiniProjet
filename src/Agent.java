/**
 *
 */
public class Agent {
    private final int finalPm;

    private int x;
    private int y;
    private int pm;

    protected Ressource[] inventaire;

    public Agent(int fpm) {
        this.finalPm = fpm;
        this.pm = fpm;
    }

    public void seDéplacer(int x,int y){
        if(this.pm < Math.abs(this.x-x)+Math.abs(this.y-y)){
            this.pm -= (Math.abs(this.x-x)+Math.abs(this.y-y));
            this.x = x;
            this.y = y;
        }
    }

    public double distance(int x, int y){
        return Math.sqrt((x-this.x)^2+(y-this.y)^2);
    }
}
