package com.example.avedit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CanvasManager {
    DrawingCanvas displayPane;
    Canvas drawingCanvas, transparentCanvas, gridCanvas, backgroundCanvas;
    private double gridSpacing;
    private boolean gridActive;

    private double canvasWidth, canvasHeight;

    private GraphicsContext displayContext, transparentContext, gridContext, backgroundContext;

    public CanvasManager(double canvasWidth, double canvasHeight)
    {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.gridSpacing = 10;

        displayPane = new DrawingCanvas(canvasWidth, canvasHeight);
        drawingCanvas = new Canvas(canvasWidth, canvasHeight);
        transparentCanvas = new Canvas(canvasWidth, canvasHeight);
        gridCanvas = new Canvas(canvasWidth, canvasHeight);
        backgroundCanvas = new Canvas(canvasWidth + 200, canvasHeight + 200);

        displayContext = drawingCanvas.getGraphicsContext2D();
        transparentContext = transparentCanvas.getGraphicsContext2D();
        backgroundContext = backgroundCanvas.getGraphicsContext2D();
        gridContext = gridCanvas.getGraphicsContext2D();

        backgroundContext.setFill(Color.WHITE);
        backgroundContext.fillRect(0, 0, canvasWidth + 50, canvasHeight + 50);

    }
    public void resetBackground()
    {
        System.out.println("Drawing Background");
        backgroundContext.setFill(Color.LIGHTGRAY);
        backgroundContext.fillRect(0, 0, canvasWidth + 50, canvasHeight + 50);
        backgroundContext.setFill(Color.WHITE);
        backgroundContext.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    public Canvas getDrawingCanvas()
    {
        return drawingCanvas;
    }

    public Canvas getTransparentCanvas()
    {
        return transparentCanvas;
    }

    public Canvas getGridCanvas()
    {
        return gridCanvas;
    }

    public Canvas getBackgroundCanvas()
    {
        return backgroundCanvas;
    }

    public GraphicsContext getDisplayContext()
    {
        return displayContext;
    }

    public GraphicsContext getTransparentContext()
    {
        return transparentContext;
    }

    public void mouseDrawingRelease(MouseEvent me, ArrayList<GameObject> gameObjects)
    {
        displayPane.releaseHandler(me, transparentContext, displayContext, gameObjects);
    }

    public void mouseDrag(MouseEvent me)
    {
        displayPane.dragHandler(me, transparentContext);
    }

    public void mousePressed(MouseEvent me)
    {
        displayPane.pressHandler(me);
    }

    public void redraw(ArrayList<GameObject> gameObjects)
    {
        for (GameObject item : gameObjects)
            item.draw(displayContext);
    }

    public void toggleGrid()
    {
        if (gridActive)
        {
            gridCanvas.setVisible(false);
            gridActive = false;
        }
        else {
            gridActive = true;
            gridCanvas.setVisible(true);
            drawGrid();
        }
    }

    public void drawGrid()
    {
        for (double x = 0; x < canvasWidth; x = x + gridSpacing)
        {
            GridLine line = new GridLine(x, 0, x, canvasHeight);
            line.draw(gridContext);
        }
        for (double y = 0; y < canvasHeight; y = y + gridSpacing)
        {
            GridLine line = new GridLine(0, y, canvasWidth, y);
            line.draw(gridContext);
        }
    }
}
