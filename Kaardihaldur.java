// Kaardihaldur.java
import java.util.ArrayList;
import java.util.List;

public class Kaardihaldur {
    private List<Kaart> kaardid = new ArrayList<>();

    public void lisaKaart(String kusimus, String vastus) {
        kaardid.add(new Kaart(kusimus, vastus));
    }

    public void segaKaardid() {
        for (int i = 0; i < kaardid.size(); i++) {
            int suvalineIndeks = (int) (Math.random() * kaardid.size());
            Kaart temp = kaardid.get(i);
            kaardid.set(i, kaardid.get(suvalineIndeks));
            kaardid.set(suvalineIndeks, temp);
        }
    }


    public List<Kaart> getKaardid() {
        return kaardid;
    }




}
