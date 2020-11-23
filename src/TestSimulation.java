public class TestSimulation {
    public static void main(String[] args) {
        Simulation S=new Simulation(10,10);
        System.out.println("Premier Tour");
        S.tourJour();
        while (!S.tourNuit()) {
            System.out.println("Round "+S.round);
            S.tourJour();
        }
        System.out.println("Défaite");
        System.out.println("Vous avez rammassé "+Ouvrier.nbDeRecolte);
        System.out.println("et généré "+Ouvrier.nbDeGeneration+" ressources");
    }
}
