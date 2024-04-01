import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MotiveerivSõnum {

    public static List<String> loefailist(String fail){
        List<String> sõnumid = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fail))) {
            String rida;

            while ((rida = br.readLine()) != null) {
                sõnumid.add(rida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sõnumid;
    }

    public static String saadaSõnum(String fail){
        List<String> sõnumid = loefailist(fail);
        Random random = new Random();
        String väljastamiseks = sõnumid.get(random.nextInt(sõnumid.size()));
        return väljastamiseks;
    }
}
