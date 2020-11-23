/**
 * Une classe statique générant aléatoirement de nom
 * @author Nicolas Devaux
 * @author Ramy Bouderbal
 */
public class Nom {
    private static final char[] voyelles = {'a','e','i','o','u'};
    private static final char[] consonnes = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};

    private Nom(){ }

    /**
     * Genere un nomre aléatoire entre inf et sup
     * @param inf borne inférieur
     * @param sup borne supérieur
     * @return Un nombre aléatoire
     */
    public static int rendAlea(int inf, int sup){
        return (int) (Math.random()*(sup-inf+1)+inf);
    }

    /**
     * Retourne True si n pair
     * @param n un entier
     * @return n pair
     */
    public static boolean estPair(int n){
            return n % 2 == 0;
    }

    /**
     * Retourne un voyelle
     * @return Une voyelle
     */
    public static char rendVoyelle(){
        return voyelles[rendAlea(0, 4)];
    }

    /**
     * retourne un consonne
     * @return une consonne
     */
    public static char rendConsonne(){
        return consonnes[rendAlea(0,20)];
    }

    /**
     * retourne un String alternat entre voyelle et consonnes
     * @return un Nom
     */
    public static String genereNom(){
        StringBuilder ch = new StringBuilder();
        for(int i=0;i<rendAlea(3,5);i++){
            if(estPair(i))
                ch.append(rendConsonne());
            else
                ch.append(rendVoyelle());
        }
        return ch.toString();
    }
}



