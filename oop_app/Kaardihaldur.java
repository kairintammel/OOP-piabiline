package org.example.oop_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

class Kaardihaldur {

    //List kaartide jaoks
    private ObservableList<Kaart> kaardid = FXCollections.observableArrayList();

    //meetod, kaartide lisamiseks
    public void lisaKaart(String kusimus, String vastus) {
        kaardid.add(new Kaart(kusimus, vastus));
    }

    //tagastab kaarid
    public List<Kaart> getKõikKaardid() {
        return kaardid;
    }
}


