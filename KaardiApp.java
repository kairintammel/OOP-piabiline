// KaardiApp.java

import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class KaardiApp {
    private static Kaardihaldur haldur = new Kaardihaldur();

    public static void käivita() {
        String kaartideArvString = JOptionPane.showInputDialog(null, "Mitu flashkaarti soovite luua?", "Flashkaartide arv", JOptionPane.QUESTION_MESSAGE);
        int kaartideArv = Integer.parseInt(kaartideArvString);

        for (int i = 0; i < kaartideArv; i++) {
            String kusimus = JOptionPane.showInputDialog(null, "Sisesta küsimus kaardile " + (i + 1), "Küsimuse sisestamine", JOptionPane.QUESTION_MESSAGE);
            String vastus = JOptionPane.showInputDialog(null, "Sisesta vastus kaardile " + (i + 1), "Vastuse sisestamine", JOptionPane.QUESTION_MESSAGE);
            haldur.lisaKaart(kusimus, vastus);
        }

        haldur.segaKaardid();
        List<Kaart> kaardid = haldur.getKaardid();

        for (Kaart kaart : kaardid) {
            String vastusSoov = JOptionPane.showInputDialog(null, "Küsimus: " + kaart.getKusimus() + "\nKas soovite näha vastust? (jah/ei)", "Küsimuse vaatamine", JOptionPane.QUESTION_MESSAGE);
            if (vastusSoov != null && vastusSoov.equalsIgnoreCase("jah")) {
                JOptionPane.showMessageDialog(null, "Vastus: " + kaart.getVastus(), "Vastuse vaatamine", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}


/*
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


 */
