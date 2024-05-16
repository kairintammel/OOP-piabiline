package org.example.loppryhmatoo;

public class Kaart {
    private String kusimus;
    private String vastus;

    public void setKusimus(String kusimus) {
        this.kusimus = kusimus;
    }

    public void setVastus(String vastus) {
        this.vastus = vastus;
    }

    public Kaart(String kusimus, String vastus) {
        this.kusimus = kusimus;
        this.vastus = vastus;
    }

    public String getKusimus() {
        return kusimus;
    }

    public String getVastus() {
        return vastus;
    }
}
