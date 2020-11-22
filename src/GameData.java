public class GameData{
    public static final int MONSTER_DENSITY = 4;
    public static final int RESSOURCE_DENSITY = 4;
    public static final int AGENT_DENSITY = 8;

    public static final String[][] RESSOURCE_TYPE ={{"Potion","épée"},{"métal"}};

    public static Ressource newRandomRessource(){
        int randomType = rng(0,2);
        String ressourceNom = RESSOURCE_TYPE[randomType][randomType==0 ? rng(0,2):0];
        int ressourceQuantité = (randomType==0 ? 1 : rng(0,3));
        return new Ressource(ressourceNom,ressourceQuantité);
    }

    public static Monstre newRandomMonster(int x,int y){
        return new Monstre(x,y);
    }

    public static int rng(int a, int b){
        return (int) (Math.random()*(b)+a);
    }
}
