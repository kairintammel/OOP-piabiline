package org.example.oop_app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;

public class ToDoList extends VBox {
    private ObservableList<String> taskid;
    private ListView<String> listView;

    public ToDoList() {
        taskid = FXCollections.observableArrayList();
        listView = new ListView<>(taskid);

        Label kirjeldus = new Label("TODO LIST");

        TextField sisend = new TextField();


        Button lisa = new Button("LISA");
        lisa.setOnMouseClicked(e -> {
            lisaTask(sisend.getText());
            sisend.clear();
        });

        Button eemalda = new Button("EEMALDA");
        eemalda.setOnMouseClicked(e -> {
            String valitudTask = listView.getSelectionModel().getSelectedItem();
            if (valitudTask != null) {
                eemaldaTask(valitudTask);
            }
        });

        getChildren().addAll(kirjeldus, listView, sisend, lisa, eemalda);


    }

    private void lisaTask(String text) {
        taskid.add(text);
    }

    public void eemaldaTask(String text) {
        taskid.remove(text);
    }

    public void setTaskid(List<String> taskid) {
        this.taskid.addAll(taskid);
    }

    public ObservableList<String> getTaskid() {
        return taskid;
    }
}

