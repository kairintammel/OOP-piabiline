// Kaart.java
public class Kaart {
    private String kusimus;//privaatne muutuja küsimuste hoidmiseks
    private String vastus;//privaatne muutuja vastuste hoidmiseks

    //Konstruktor - obkjekti Kaart loomiseks koos küsimuse ja vastusega
    public Kaart(String kusimus, String vastus) {
        this.kusimus = kusimus;
        this.vastus = vastus;
    }

    //Getter kaardi küsimuse saamiseks
    public String getKusimus() {
        return kusimus;
    }

    //Getter kaardi vastuse saamiseks
    public String getVastus() {
        return vastus;
    }
}

