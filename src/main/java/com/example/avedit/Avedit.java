package com.example.avedit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Avedit extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        FlowPane toolPane = addToolBar();
        FlowPane propPane = addPropertiesPane();
        VBox canvas = addCanvas();

        border.setTop(hbox);
        border.setLeft(toolPane);
        border.setRight(propPane);
        border.setCenter(canvas);

        Scene scene = new Scene(border, 1920, 1280);
        stage.setTitle("Avedit");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #212121;");

        Button testButton01 = new Button("Test1");
        testButton01.setPrefSize(100, 20);

        Button testButton02 = new Button("Test2");
        testButton02.setPrefSize(100, 20);

        hbox.getChildren().addAll(testButton01, testButton02);

        return hbox;
    }

    private VBox addCanvas()
    {
        VBox canvas = new VBox();
        canvas.setPadding(new Insets(25, 25, 25, 25));
        canvas.setSpacing(10);
        canvas.setStyle("-fx-background-color: #AAAAAA;");

        return canvas;
    }

    private FlowPane addToolBar()
    {
        FlowPane fpane = new FlowPane();
        fpane.setPadding(new Insets(25, 0, 5, 10));
        fpane.setVgap(8);
        fpane.setHgap(8);
        fpane.setPrefWrapLength(120);
        fpane.setStyle("-fx-background-color: #3D3D3D;");

        Button tools[] = new Button[4];

        for (int i=0; i<4; i++)
        {
            tools[i] = new Button("Testing " + i);
            tools[i].setPrefSize(50, 50);
            fpane.getChildren().add(tools[i]);
        }
        return fpane;
    }

    private FlowPane addPropertiesPane()
    {
        FlowPane properties = new FlowPane();
        properties.setPadding(new Insets(25, 0, 5, 10));
        properties.setVgap(8);
        properties.setHgap(8);
        properties.setPrefWrapLength(200);
        properties.setStyle("-fx-background-color: #3D3D3D;");

        return properties;
    }

    private VBox addVbox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Testing");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        return vbox;
    }
}

