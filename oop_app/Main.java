package org.example.oop_app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tere tulemast!");
        System.out.println("Millega saame sind täna aidata?");
        System.out.println("\n1) Vajan motiveerivat sõnumit." +
                "\n2) Tahan kasutada pomodoro taimerit." +
                "\n3) Soovin luua flash kaarte." +
                "\n4) soovin avada TO-DO listi.");
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sisesta siia oma soovitud tegevuse nr: ");

        int käsk = scanner.nextInt();

        if (käsk == 1){
            System.out.println(MotiveerivSõnum.saadaSõnum("peptalk.txt"));
        } else if (käsk == 2) {
            System.out.println("POMODORO TAIMER\n");
            System.out.println("Pomodoro taimeri töötsükli pikkus on 25 minutit.");
            System.out.println("Peale igat töötsüklit on 5 minutit pausi, aga peale ");
            System.out.println("igat neljandat tsüklit on pikem paus (15 minutit).\n");
            PomodoroTaimer.kutsuVälja();
            
        } else if (käsk == 3) {
            FlashKaardid.main(args);

        } else if (käsk == 4) {
            ToDoApp.main(args);
        }
    }
}
