package com.example.avedit;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class LayoutManager {

    private BorderPane layout;
    private HBox header;
    private FlowPane toolBar;
    private Group canvasGroup;
    private FlowPane properties;
    private ScrollPane canvas;

    private CanvasManager canvasManager;
    private ArrayList<GameObject> gameObjects;


    public LayoutManager()
    {

        canvasManager = new CanvasManager(1000, 1000);
        gameObjects = new ArrayList<>();

        layout = new BorderPane();
        header = createHeader();
        toolBar = createToolBar();
        properties = createProperties();
        canvas = new ScrollPane();
        canvasGroup = createCanvasGroup();
        canvas.setContent(canvasGroup);
        layout.setTop(header);
        layout.setLeft(toolBar);
        layout.setRight(properties);
        layout.setCenter(canvas);
    }
    public BorderPane getLayout()
    {
        return layout;
    }

    private HBox createHeader() {
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

    private FlowPane createToolBar()
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

    private FlowPane createProperties()
    {
        FlowPane properties = new FlowPane();
        properties.setPadding(new Insets(25, 0, 5, 10));
        properties.setVgap(8);
        properties.setHgap(8);
        properties.setPrefWrapLength(200);
        properties.setStyle("-fx-background-color: #3D3D3D;");

        return properties;
    }

    public Group createCanvasGroup() {

        Group canvasGroup = new Group();

        canvasGroup.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        canvasGroup.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        canvasGroup.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);

        canvasGroup.getChildren().addAll(canvasManager.getBackgroundCanvas(),canvasManager.getDrawingCanvas(), canvasManager.getTransparentCanvas(), canvasManager.getGridCanvas());

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

    private void gridHandler(ActionEvent actionEvent)
    {
        toggleGrid();
    }

    private void zoomIn()
    {
        canvasGroup.setScaleX(canvasGroup.getScaleX() * 2);
        canvasGroup.setScaleY(canvasGroup.getScaleY() * 2);
    }

    private void zoomOut()
    {
        canvasGroup.setScaleX(canvasGroup.getScaleX() / 2);
        canvasGroup.setScaleY(canvasGroup.getScaleY() / 2);
    }
    private void zoomInHandler(ActionEvent actionEvent) { zoomIn();}

    private void zoomOutHandler(ActionEvent actionEvent) { zoomOut();}

    public void reset()
    {
        canvasManager.resetBackground();
    }
}
