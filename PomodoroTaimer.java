package org.example.oop_app;// Pomodoro taimer

import java.util.Scanner;

public class PomodoroTaimer {
    public static void kutsuVälja() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Mitu 25-5 tsüklit sooviksid käivitada? ");

        int tsükleid = scanner1.nextInt();

        System.out.println("Vajuta ENTER taimeri käivitamiseks.");
        Scanner scanner2 = new Scanner(System.in);

        int mitmes = 1;

        while (mitmes <= tsükleid) {
            scanner2.nextLine(); // Ootab, et kasutaja vajutaks ENTER
            tsükleid--;

            System.out.println("Tsükkel " + (mitmes + 1) + " --> Tööaeg 25 minutit.");
            countdown(25);

            if (mitmes % 3 == 0) { // peale igat neljandat tsüklit pikem paus
                System.out.println("Pikem paus - 15 minutit.");
                countdown(15);
            } else {
                System.out.println("Paus - 5 minutit.");
                countdown(5);
            }
            mitmes ++;
        }
    }

    public static void countdown(int minutid)
    {
        int sekundid = minutid * 60;

        while (sekundid > 0) {

            System.out.print("\r" + String.format("%02d:%02d", sekundid / 60, sekundid % 60)); // väljastab järelejäänud aja ekraanile
            try {
                Thread.sleep(1000); // ootab 1 sekund enne uut tsüklit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sekundid--;
        }
        System.out.println("\nAeg!!!");
    }
}
