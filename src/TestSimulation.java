public class TestSimulation {
    public static void main(String[] args) {
        Simulation S=new Simulation(6,6);
        System.out.println("Premier Tour");
        System.out.println(S.tourJour());
        while(true){
            if(S.tourNuit().equals("Défaite")){
                break;
            }
            System.out.println();
            S.tourJour();
        }
        System.out.println("Défaite");
    }
}
