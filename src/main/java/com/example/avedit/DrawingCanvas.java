package com.example.avedit;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrawingCanvas  {
    double canvasWidth;
    double canvasHeight;
    private double sx, sy;
    private double ex, ey;
    private double startingX, startingY;
    private int selectedObject = 0;
    private Color shapeColour = Color.BLUE;
    private Boolean gridOn = true;
    private int gridSpacing = 50;



    public void pressHandler(MouseEvent me)
    {
        System.out.printf("Mouse Pressed");
        sx = me.getX();
        sy = me.getY();

        startingX = sx;
        startingY = sy;
    }

    public void releaseHandler(MouseEvent me, GraphicsContext transgc, GraphicsContext gc, ArrayList<GameObject> gameObjects)
    {
        // Make sure mouse is released in a valid location
        System.out.println("Released mouse");
        GameObject s;
        try
        {
            if (me.getX() < 0 || me.getY() < 0 || me.getX() > canvasWidth || me.getY() > canvasHeight)
                throw new IllegalArgumentException();
            s = new WallObject("Wall 01", sx, sy, ex, ey, Color.BLUE, Color.BLUE);
            System.out.println(gameObjects.size());
            gameObjects.add(s);
            System.out.println("Adding an object");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error on release");
            gc.setFill(Color.WHITE);
            gc.fillRect(0, 0, canvasWidth, canvasHeight);
        } // end catch

        gc.setFill(Color.BLUE);
        transgc.clearRect(0, 0, canvasWidth, canvasHeight);
        for (int x = 0; x < gameObjects.size(); x ++)
        {
            System.out.println("Drawing each object");
            gameObjects.get(x).draw(gc);
        }
    }

    public void dragHandler(MouseEvent me, GraphicsContext transgc)
    {
        try // make sure the mouse is in a valid location
        {
            if (me.getX() < 0 || me.getY() < 0 || me.getX() > canvasWidth || me.getY() > canvasHeight)
                throw new IllegalArgumentException();
            transgc.clearRect(0, 0, canvasWidth, canvasHeight);

                    if (me.getX() < startingX && me.getY() > startingY)
                    {
                        // if cursor is above Y-axis starting point
                        sx = me.getX();
                        ex = (startingX - sx);
                        ey = (me.getY() - sy);
                    }
                    else if (me.getY() < startingY && me.getX() > startingX)
                    {
                        // if cursor is above X-axis starting point
                        sy = me.getY();
                        ex = (me.getX() - sx);
                        ey = (startingY - sy);
                    }
                    else if (me.getX() < startingX && me.getY() < startingY)
                    {
                        // if cursor is below X-axis and Y-axis starting point
                        sx = me.getX();
                        sy = me.getY();
                        ex = (startingX - sx);
                        ey = (startingY - sy);
                    }
                    else if (me.getX() > startingX && me.getY() > startingY)
                    {
                        // if cursor is above X-axis and Y-axis starting point
                        ex = ((me.getX()) - sx);
                        ey = ((me.getY()) - sy);
                    } // end if
                    // Create a temporary square object
                    WallObject tempSquare = new WallObject("Temp Wall", sx, sy, ex, ey, Color.BLUE, Color.BLUE);
                    // draw temporary sqaure
                    tempSquare.draw(transgc);
        } // end try
        catch (IllegalArgumentException e)
        {
            System.out.println("Drag failed" + me.getX() +  " " + me.getY());
            System.out.println(canvasHeight);
            System.out.println(transgc.toString());
        } // end catch
    }

    public void setCanvasSize(double width, double height)
    {
        canvasWidth = width;
        canvasHeight = height;
    }

    public DrawingCanvas(double width, double height)
    {
        System.out.println("Creating new Drawing Canvas");
        canvasWidth = height;
        canvasHeight = width;
    }

    public void reDraw(GraphicsContext gc, ArrayList<GameObject> obj)
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        for (GameObject wall: obj)
        {
            wall.draw(gc);
        }

    }
}
