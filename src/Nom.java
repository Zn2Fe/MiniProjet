public class Nom {
        private static char[] voyelles = {'a','e','i','o','u'};
        private static char[] consonnes = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};

        private Nom(){
        }

        public static int rendAlea(int inf, int sup){
            return (int) (Math.random()*(sup-inf+1)+inf);
        }

        public static boolean estPair(int n){
            if(n%2!=0)
                return false;
            else
                return true;
        }

        public static char rendVoyelle(){
            return voyelles[rendAlea(0, 4)];
        }

        public static char rendConsonne(){
            return consonnes[rendAlea(0,20)];
        }

        public static String genereNom(){
            String ch = "";
            for(int i=0;i<rendAlea(3,6);i++){
                if(estPair(i))
                    ch = ch + rendConsonne();
                else
                    ch = ch + rendVoyelle();
            }
            return ch;
        }
    }



