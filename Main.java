import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Tere tulemast!");
        System.out.println("Millega saame sind täna aidata?");
        System.out.println("\n1) Vajan motiveerivat sõnumit." +
                "\n2) Tahan kasutada pomodoro taimerit." +
                "\n3) Soovin luua flash kaarte.");
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sisesta siia oma soovitud tegevuse nr: ");

        int käsk = scanner.nextInt();

        if (käsk == 1){
            System.out.println(MotiveerivSõnum.saadaSõnum("peptalk.txt"));
        } else if (käsk == 2) {
            
        } else if (käsk == 3) {
            
        }
    }
}
