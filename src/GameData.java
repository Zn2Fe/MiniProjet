public class GameData{
    public static final int MONSTER_DENSITY = 4;
    public static final int RESSOURCE_DENSITY = 4;
    public static final int AGENT_DENSITY = 8;
    public static final int SOLDIER_REPARTITION = 15;

    public static final int baseX = 3;
    public static final int baseY = 3;

    public static final String[][] RESSOURCE_TYPE ={{"Potion","épée"},{"métal"}};

    public static Ressource newRandomRessource(){
        int randomType = rng(0,2);
        String ressourceNom = RESSOURCE_TYPE[randomType][randomType==0 ? rng(0,2):0];
        int ressourceQuantite = (randomType==0 ? 1 : rng(0,3));
        return new Ressource(ressourceNom,ressourceQuantite);
    }

    public static Monstre newRandomMonster(){
        return new Monstre(GameData.baseX,GameData.baseY);
    }

    public static Agent newRandomAgent(){
        return Math.random()*SOLDIER_REPARTITION < 5 ? new Soldat() : new Ouvrier();
    }

    public static int rng(int a, int b){
        return (int) (Math.random()*(b)+a);
    }
}
