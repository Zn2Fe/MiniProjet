/**
 * Une classe statique permetant de donner les regles du jeu
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 */
public class GameData{
    public static final int RESSOURCE_DENSITY = 4;
    public static final int AGENT_DENSITY = 8;
    public static final int SOLDIER_REPARTITION = 15;

    public static final int baseX = 3;
    public static final int baseY = 3;

    public static final String[][] RESSOURCE_TYPE ={{"Potion","épée"},{"métal"}};

    /**
     * retourne une ressource g&eacute;n&eacute;rer al&eacute;atoirement
     * @return Une ressource
     */
    public static Ressource newRandomRessource(){
        int randomType = rng(0,2);
        String ressourceNom = RESSOURCE_TYPE[randomType][randomType==0 ? rng(0,2):0];
        int ressourceQuantite = (randomType==0 ? 1 : rng(1,3));
        return new Ressource(ressourceNom,ressourceQuantite);
    }

    /**
     * Genere un monstre
     * @return Un monstre
     */
    public static Monstre newRandomMonster(){
        return new Monstre();
    }

    /**
     * Genere un nouvel Agent al&eacute;atoirement soldat ou ouvrier
     * @return Un agent (ouvrier ou soldat)
     */
    public static Agent newRandomAgent(){
        return Math.random()*SOLDIER_REPARTITION < 5 ? new Soldat() : new Ouvrier();
    }

    /**
     * renvoie un nombre al&eacute;atoire entre [a;a+b[
     * @param a Nomre al&eacute;atoire de d&eacute;part
     * @param b Taille de la g&eacute;n&eacute;ration al&eacute;atoire
     * @return Math.random()*(b)+a
     */
    public static int rng(int a, int b){
        return (int) (Math.random()*(b)+a);
    }
}
