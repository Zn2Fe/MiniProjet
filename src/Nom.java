public class Nom {
        private static final char[] voyelles = {'a','e','i','o','u'};
        private static final char[] consonnes = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};

        private Nom(){
        }

        public static int rendAlea(int inf, int sup){
            return (int) (Math.random()*(sup-inf+1)+inf);
        }

        public static boolean estPair(int n){
            return n % 2 == 0;
        }

        public static char rendVoyelle(){
            return voyelles[rendAlea(0, 4)];
        }

        public static char rendConsonne(){
            return consonnes[rendAlea(0,20)];
        }

        public static String genereNom(){
            StringBuilder ch = new StringBuilder();
            for(int i=0;i<rendAlea(3,6);i++){
                if(estPair(i))
                    ch.append(rendConsonne());
                else
                    ch.append(rendVoyelle());
            }
            return ch.toString();
        }
    }



