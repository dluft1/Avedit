package com.example.avedit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.util.ArrayList;

public class Avedit extends Application {
    LayoutManager layoutManager;

    @Override
    public void start(Stage stage) throws IOException {

        layoutManager = new LayoutManager();

        Scene scene = new Scene(layoutManager.getLayout(), 1920, 1280);
        stage.setTitle("Avedit");
        stage.setScene(scene);

        stage.show();
        layoutManager.reset();
    }

    public static void main(String[] args) {
        launch();
    }
}

