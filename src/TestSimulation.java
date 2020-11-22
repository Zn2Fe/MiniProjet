public class TestSimulation {
    public static void main(String[] args) {
        Simulation S=new Simulation(6,6);
        S.tourJour();
        while(S.tourNuit().equals("Défaite")){
            System.out.println("Tour 1");
            S.tourJour();
        }
        System.out.println("Défaite");
    }
}
