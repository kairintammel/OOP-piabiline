// Kaardihaldur.java
import java.util.ArrayList;
import java.util.List;

public class Kaardihaldur {
    private List<Kaart> kaardid = new ArrayList<>();

    //lisab uue kaardi
    public void lisaKaart(String kusimus, String vastus) {
        kaardid.add(new Kaart(kusimus, vastus));
    }

    //segab kaarte, kasutades Math.random funktsioon
    public void segaKaardid() {
        for (int i = 0; i < kaardid.size(); i++) {
            int suvalineIndeks = (int) (Math.random() * kaardid.size());
            Kaart temp = kaardid.get(i);
            kaardid.set(i, kaardid.get(suvalineIndeks));
            kaardid.set(suvalineIndeks, temp);
        }
    }


    //tagastab kaartide listi
    public List<Kaart> getKaardid() {
        return kaardid;
    }




}
