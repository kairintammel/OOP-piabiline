package org.example.loppryhmatoo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

class Kaardihaldur {
    private ObservableList<Kaart> kaardid = FXCollections.observableArrayList();

    public void lisaKaart(String kusimus, String vastus) {
        kaardid.add(new Kaart(kusimus, vastus));
    }

    public List<Kaart> getKõikKaardid() {
        return kaardid;
    }
}


