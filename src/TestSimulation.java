public class TestSimulation {
    public static void main(String[] args) {
        Simulation S=new Simulation(6,6);
        System.out.println(S.tourJour());
        while(true){
            var s = S.tourNuit();
            if(s.equals("Défaite")){
                break;
            }
            System.out.println("Tour 1");
            S.tourJour();
        }
        System.out.println("Défaite");
    }
}
