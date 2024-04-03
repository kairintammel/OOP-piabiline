// KaardiApp.java
import java.util.List;
import java.util.Scanner;

public class KaardiApp {
    private static Kaardihaldur haldur = new Kaardihaldur();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Mitu flashkaarti soovite luua? ");
        int count = scanner.nextInt();
        scanner.nextLine(); // tarbib reavahetuse

        for (int i = 0; i < count; i++) {
            System.out.print("Sisesta küsimus kaardile " + (i + 1) + ": ");
            String kusimus = scanner.nextLine();
            System.out.print("Sisesta vastus kaardile " + (i + 1) + ": ");
            String vastus = scanner.nextLine();
            haldur.lisaKaart(kusimus, vastus);
        }

        haldur.segaKaardid();
        List<Kaart> kaardid = haldur.getKaardid();

        for (Kaart kaart : kaardid) {
            System.out.println("Küsimus: " + kaart.getKusimus());
            System.out.print("Kas soovite näha vastust? (jah/ei): ");
            String vastusSoov = scanner.nextLine();
            if (vastusSoov.equalsIgnoreCase("jah")) {
                System.out.println("Vastus: " + kaart.getVastus());
            }
            System.out.println();
        }
    }
}
