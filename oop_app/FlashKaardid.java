package org.example.oop_app;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.Collections;
import java.util.Optional;

//Põhiklass, millega tehakse JavaFX aplikatsioon
public class FlashKaardid extends Application {
    //Kaardihaldur flashkaartide haldamiseks
    private Kaardihaldur haldur = new Kaardihaldur();
    //List, mis hpiustab segatud kaarte
    private ObservableList<Kaart> segatudKaardid = FXCollections.observableArrayList();
    //indeks mis, jägib praegust kaarti
    private int praeguneKaartIndex = 0;
    //Fail, logide jaoks
    private File logiFail = new File("flashkaardid.txt");

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Kui logifaili ei eksisteeri, luuakse uus fail
        if (!logiFail.exists()) {
            logiFail.createNewFile();
        }

        //VBox paigutus
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);


        //Erinevate graafiliste elementide loomine
        Label kusimuseLabel = new Label(); //Küsimuse kuvamiseks
        Button naeVastusNupp = new Button("Näita vastust"); //Vastuse kuvamiseks
        Button jargmineKaartNupp = new Button("Järgmine kaart"); //Järgmise kaardi juurde minemiseks nupp
        Button tagasiNupp = new Button("Mine eelmise küsimuse juurde"); //Nupp, et minna eelmise küsimuse juurde
        Button lopetaNupp = new Button("Lõpeta"); //Nupp lõpetamiseks
        Button uuestiNupp = new Button("Alusta uuesti"); //Nupp, et uuesti alustada flashkaartide kasutamist


       //Alguses keelan nupud ja muudan mõned nähtamatuks, sest nende kasutamine pole veel vajalik
        naeVastusNupp.setDisable(true);
        jargmineKaartNupp.setDisable(true);
        tagasiNupp.setDisable(true);
        lopetaNupp.setDisable(true);
        lopetaNupp.setVisible(false);
        uuestiNupp.setDisable(true);
        uuestiNupp.setVisible(false);

        //Tsükkel, millega küsitakse kasutajalt kaartide arvu
        while (true) {
            TextInputDialog Dialoog = new TextInputDialog(" ");
            Dialoog.setTitle("Kaartide loomine");
            Dialoog.setHeaderText("Flashkaartide loomine");
            Dialoog.setContentText("Palun sisesta kaartide arv:");

            //Dialoogi kuvamine ja kasutajalt sisendi ootamine
            Optional<String> result = Dialoog.showAndWait();
            if (result.isPresent()) {
                //kasutaja sisendi töötlemine ja kuvamine
                try {
                    int count = Integer.parseInt(result.get());
                    for (int i = 0; i < count; i++) {
                        TextInputDialog kaardiDialoog = new TextInputDialog();
                        kaardiDialoog.setTitle("Kaardi sisestamine");
                        kaardiDialoog.setHeaderText("Sisesta kaardi andmed (" + (i + 1) + "/" + count + ")");

                        kaardiDialoog.getDialogPane().setContentText("Küsimus:");
                        Optional<String> kusimus = kaardiDialoog.showAndWait();

                        kaardiDialoog.getDialogPane().setContentText("Vastus:");
                        Optional<String> vastus = kaardiDialoog.showAndWait();

                        if (kusimus.isPresent() && vastus.isPresent()) {
                            haldur.lisaKaart(kusimus.get(), vastus.get());
                            try {
                                logiKaardiSisestus("Kaardi lisamine", kusimus.get(), vastus.get());
                            } catch (IOException e) {
                                new Alert(Alert.AlertType.ERROR, "Viga logimise ajal: " + e.getMessage()).showAndWait();
                            }
                        }
                    }

                    //Kaartide segamine
                    segatudKaardid.setAll(haldur.getKõikKaardid());
                    Collections.shuffle(segatudKaardid);
                    kusimuseLabel.setText(segatudKaardid.get(0).getKusimus());
                    //vajalikke nuppe saab kasutada
                    naeVastusNupp.setDisable(false);
                    jargmineKaartNupp.setDisable(false);
                    tagasiNupp.setDisable(praeguneKaartIndex <= 0);
                    break;
                    //kui on ei ole sisestatud number antakse vea teade
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigane sisestus");
                    alert.setHeaderText(null);
                    alert.setContentText("Palun sisestage number.");
                    alert.showAndWait();
                }
            } else {
                // Kui kasutaja tühistab dialoogi(ei siseta midagi lahtrisse), suletakse programm
                primaryStage.close();
                return;
            }
        }

        // "Näita vastust" nupu tegevus
        naeVastusNupp.setOnAction(e -> {
            String vastus = segatudKaardid.get(praeguneKaartIndex).getVastus();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vastuse vaatamine");
            alert.setHeaderText("Siin on vastus:");
            alert.setContentText(vastus);
            alert.showAndWait();
            try {
                logiKaardiSisestus("Vastuse vaatamine", segatudKaardid.get(praeguneKaartIndex).getKusimus(), vastus);
            } catch (IOException ex) {
                new Alert(Alert.AlertType.ERROR, "Viga logimisega: " + ex.getMessage()).showAndWait();
            }
        });


        // "Järgmine kaart" nupu tegevus
        jargmineKaartNupp.setOnAction(e -> {
            praeguneKaartIndex++;
            if (praeguneKaartIndex >= segatudKaardid.size()) {
                kusimuseLabel.setText("Kõik kaardid on näidatud");
                //Nuppude seadistuste muutmine
                jargmineKaartNupp.setDisable(true);
                naeVastusNupp.setDisable(true);
                tagasiNupp.setDisable(true);
                lopetaNupp.setDisable(false);
                lopetaNupp.setVisible(true);
                uuestiNupp.setDisable(false);
                uuestiNupp.setVisible(true);
            } else {
                //Kuvatakse järgmine küsimus
                kusimuseLabel.setText(segatudKaardid.get(praeguneKaartIndex).getKusimus());
                tagasiNupp.setDisable(false);
            }
        });


        //"mine eelmise küsimuse juurde" nupu tegevus
        tagasiNupp.setOnAction(e -> {
            if (praeguneKaartIndex > 0) {
                praeguneKaartIndex--;
                kusimuseLabel.setText(segatudKaardid.get(praeguneKaartIndex).getKusimus());
                jargmineKaartNupp.setDisable(false);
                naeVastusNupp.setDisable(false);
                tagasiNupp.setDisable(praeguneKaartIndex <= 0);
            }
        });


        //"Lõpeta" nupu tegevus
        lopetaNupp.setOnAction(e -> {
            primaryStage.close();
        });


        //"Alusta uuesti" nupu tegevus
        uuestiNupp.setOnAction(e -> {

            //Indeks nii öelda restetitakse ning segatakse kaardid uuest, kogu tegevus hakkab uuesti pihta
            praeguneKaartIndex = 0;
            Collections.shuffle(segatudKaardid);
            kusimuseLabel.setText(segatudKaardid.get(praeguneKaartIndex).getKusimus());
            naeVastusNupp.setDisable(false);
            jargmineKaartNupp.setDisable(false);
            tagasiNupp.setDisable(praeguneKaartIndex <= 0);
            lopetaNupp.setDisable(true);
            lopetaNupp.setVisible(false);
            uuestiNupp.setDisable(true);
            uuestiNupp.setVisible(false);
        });


        //Lisatakse komponendid juurelementi ja luuakse tseen
        root.getChildren().addAll(kusimuseLabel, naeVastusNupp, jargmineKaartNupp, tagasiNupp, lopetaNupp, uuestiNupp);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Flashkaardi Rakendus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //Meetod, millega lisatakse kaardid logifaili
    private void logiKaardiSisestus(String s, String kusimus, String vastus) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logiFail, true))) {
            writer.write(s + ";" + kusimus + ";" + vastus + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args); //Käivitatakse rakendus
    }


}
