package org.example.oop_app;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ToDoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<String> failiTaskid = loeTodo("todo.txt");

        ToDoList todo = new ToDoList();
        todo.setAlignment(Pos.CENTER);

        todo.setTaskid(failiTaskid);

        primaryStage.setTitle("To-Do");
        primaryStage.setScene(new Scene(todo, 400, 300));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> {
            salvestaFaili(todo.getTaskid(), "todo.txt");
        });

    }

    private static void salvestaFaili(ObservableList<String> taskid, String file) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, false))) {
            for (String task : taskid) {
                bw.write(task);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<String> loeTodo(String fail){
        List<String> failiTaskid = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "utf-8"))) {
            String rida;
            while ((rida = bf.readLine()) != null) {
                failiTaskid.add(rida);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("vale lols");
        }

        return failiTaskid;
    }
}
