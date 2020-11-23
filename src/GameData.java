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
     * retourne une ressource générer aléatoirement
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
     * Genere un nouvel Agent aléatoirement soldat ou ouvrier
     * @return Un agent (ouvrier ou soldat)
     */
    public static Agent newRandomAgent(){
        return Math.random()*SOLDIER_REPARTITION < 5 ? new Soldat() : new Ouvrier();
    }

    /**
     * renvoie un nombre aléatoire entre [a;a+b[
     * @param a Nomre aléatoire de départ
     * @param b Taille de la génération aléatoire
     * @return Math.random()*(b)+a
     */
    public static int rng(int a, int b){
        return (int) (Math.random()*(b)+a);
    }
}
