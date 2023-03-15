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

    CanvasManager canvasManager;
    private GraphicsContext gc, transgc;
    DrawingCanvas drawingPane;
    ArrayList<GameObject> gameObjects;
    Canvas c;
    Canvas t;
    Group scrollGroup;
    @Override
    public void start(Stage stage) throws IOException {

        canvasManager = new CanvasManager(1000, 1000);
        gameObjects = new ArrayList<>();

        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        FlowPane toolPane = addToolBar();
        FlowPane propPane = addPropertiesPane();

        scrollGroup = addVbox();
        ScrollPane canvas = new ScrollPane();
        canvas.setStyle("-fx-background: #808080;");
        canvas.setContent(scrollGroup);


        border.setTop(hbox);
        border.setLeft(toolPane);
        border.setRight(propPane);
        border.setCenter(canvas);

        Scene scene = new Scene(border, 1920, 1280);
        stage.setTitle("Avedit");
        stage.setScene(scene);

        stage.show();


        double canvasWidth = border.getWidth() - toolPane.getWidth() - propPane.getWidth() - 20;
        double canvasHeight = border.getHeight() - hbox.getHeight() - 20;
        c.setHeight(canvasHeight);
        c.setWidth(canvasWidth);
        t.setWidth(canvasWidth);
        t.setHeight(canvasHeight);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);

        drawingPane.setCanvasSize(canvasWidth, canvasHeight);

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
        tools[0].setOnAction(this::gridHandler);
        tools[1].setOnAction(this::zoomInHandler);
        tools[2].setOnAction(this::zoomOutHandler);
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

    private Group addVbox() {

        Group canvasGroup = new Group();

        canvasGroup.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        canvasGroup.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        canvasGroup.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);

        canvasGroup.getChildren().addAll(canvasManager.getDrawingCanvas(),canvasManager.getTransparentCanvas());

        return canvasGroup;
    }

    private void pressHandler(MouseEvent me)
    {
        canvasManager.mousePressed(me);
    }

    private void releaseHandler(MouseEvent me)
    {
        canvasManager.mouseDrawingRelease(me, gameObjects);
    }

    private void dragHandler(MouseEvent me)
    {
        canvasManager.mouseDrag(me);
    }

    private void toggleGrid()
    {
        canvasManager.toggleGrid();
    }

    private void zoomIn()
    {
        scrollGroup.setScaleX(scrollGroup.getScaleX() * 2);
        scrollGroup.setScaleY(scrollGroup.getScaleY() * 2);
    }

    private void zoomOut()
    {
        scrollGroup.setScaleX(scrollGroup.getScaleX() / 2);
        scrollGroup.setScaleY(scrollGroup.getScaleY() / 2);
    }

    private void gridHandler(ActionEvent actionEvent)
    {
        toggleGrid();
    }

    private void zoomInHandler(ActionEvent actionEvent) { zoomIn();}

    private void zoomOutHandler(ActionEvent actionEvent) { zoomOut();}
}

