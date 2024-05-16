package org.example.loppryhmatoo;

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

public class FlashKaardid extends Application {
    private Kaardihaldur haldur = new Kaardihaldur();
    private ObservableList<Kaart> segatudKaardid = FXCollections.observableArrayList();
    private int praeguneKaartIndex = 0;
    private File logiFail = new File("flashkaardid.txt");

    @Override
    public void start(Stage primaryStage) throws IOException {
        if (!logiFail.exists()) {
            logiFail.createNewFile();
        }

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Label kusimuseLabel = new Label();
        Button naeVastusNupp = new Button("Näita vastust");
        Button jargmineKaartNupp = new Button("Järgmine kaart");
        Button tagasiNupp = new Button("Mine eelmise küsimuse juurde");
        Button lopetaNupp = new Button("Lõpeta");
        Button uuestiNupp = new Button("Alusta uuesti");

        naeVastusNupp.setDisable(true);
        jargmineKaartNupp.setDisable(true);
        tagasiNupp.setDisable(true);
        lopetaNupp.setDisable(true);
        lopetaNupp.setVisible(false);
        uuestiNupp.setDisable(true);
        uuestiNupp.setVisible(false);

        while (true) {
            TextInputDialog Dialoog = new TextInputDialog(" ");
            Dialoog.setTitle("Kaartide loomine");
            Dialoog.setHeaderText("Flashkaartide loomine");
            Dialoog.setContentText("Palun sisesta kaartide arv:");

            Optional<String> result = Dialoog.showAndWait();
            if (result.isPresent()) {
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
                    segatudKaardid.setAll(haldur.getKõikKaardid());
                    Collections.shuffle(segatudKaardid);
                    kusimuseLabel.setText(segatudKaardid.get(0).getKusimus());
                    naeVastusNupp.setDisable(false);
                    jargmineKaartNupp.setDisable(false);
                    tagasiNupp.setDisable(praeguneKaartIndex <= 0);
                    break;
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Vigane sisestus");
                    alert.setHeaderText(null);
                    alert.setContentText("Palun sisestage number.");
                    alert.showAndWait();
                }
            } else {
                // Kui kasutaja tühistab dialoogi, sulgeme programmi
                primaryStage.close();
                return;
            }
        }

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

        jargmineKaartNupp.setOnAction(e -> {
            praeguneKaartIndex++;
            if (praeguneKaartIndex >= segatudKaardid.size()) {
                kusimuseLabel.setText("Kõik kaardid on näidatud");
                jargmineKaartNupp.setDisable(true);
                naeVastusNupp.setDisable(true);
                tagasiNupp.setDisable(true);
                lopetaNupp.setDisable(false);
                lopetaNupp.setVisible(true);
                uuestiNupp.setDisable(false);
                uuestiNupp.setVisible(true);
            } else {
                kusimuseLabel.setText(segatudKaardid.get(praeguneKaartIndex).getKusimus());
                tagasiNupp.setDisable(false);
            }
        });

        tagasiNupp.setOnAction(e -> {
            if (praeguneKaartIndex > 0) {
                praeguneKaartIndex--;
                kusimuseLabel.setText(segatudKaardid.get(praeguneKaartIndex).getKusimus());
                jargmineKaartNupp.setDisable(false);
                naeVastusNupp.setDisable(false);
                tagasiNupp.setDisable(praeguneKaartIndex <= 0);
            }
        });

        lopetaNupp.setOnAction(e -> {
            primaryStage.close();
        });

        uuestiNupp.setOnAction(e -> {
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

        root.getChildren().addAll(kusimuseLabel, naeVastusNupp, jargmineKaartNupp, tagasiNupp, lopetaNupp, uuestiNupp);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Flashkaardi Rakendus");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void logiKaardiSisestus(String s, String kusimus, String vastus) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logiFail, true))) {
            writer.write(s + ";" + kusimus + ";" + vastus + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
