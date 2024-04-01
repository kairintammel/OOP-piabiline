import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTaimer {
    int tööMinut;
    int pausMinut;
    int mituRingi;

    public PomodoroTaimer(int tööMinut, int pausMinut, int mituRingi) {
        this.tööMinut = tööMinut;
        this.pausMinut = pausMinut;
        this.mituRingi = mituRingi;
    }

    public void countdown(){
        for (int i = 5; i >= 0; i--) {
            Timer timer = new Timer();
            int finalI = i;
            TimerTask countdown = new TimerTask() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            };
            timer.schedule(countdown, 1000);
        }
    }
    public void teeTööd() {
        for (int i = 0; i < tööMinut; i++) {
            Timer timer = new Timer();
            double aegaJäänud = tööMinut;
            TimerTask ülesanne = new TimerTask() {
                @Override
                public void run() {
                    System.out.println();
                }
            };

            timer.schedule(ülesanne, tööMinut * 1000); //
        }
    }
}
