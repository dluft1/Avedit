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
    double SCREEN_WIDTH;
    double SCREEN_HEIGHT;
    private double sx, sy;
    private double ex, ey;
    private double startingX, startingY;
    private int selecteObject = 0;
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
            if (me.getX() < 0 || me.getY() < 0 || me.getX() > SCREEN_WIDTH || me.getY() > SCREEN_HEIGHT)
                throw new IllegalArgumentException();
            s = new WallObject("Wall 01", sx, sy, ex, ey, Color.BLUE, Color.BLUE);
            System.out.println(gameObjects.size());
            gameObjects.add(s);
            System.out.println("Adding an object");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Error on release");
            gc.setFill(Color.LIGHTGRAY);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        } // end catch

        //gc.setFill(Color.BLACK);
        //gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        gc.setFill(Color.BLUE);
        transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for (int x = 0; x < gameObjects.size(); x ++)
        {
            System.out.println("Drawing each object");
            gameObjects.get(x).draw(gc);
        }
        /*for ( GameObject obj : gameObjects) {
            //System.out.println("Found an object");
            obj.draw(gc);
        } */
    }

    public void dragHandler(MouseEvent me, GraphicsContext transgc)
    {
        try // make sure the mouse is in a valid location
        {
            if (me.getX() < 0 || me.getY() < 0 || me.getX() > SCREEN_WIDTH || me.getY() > SCREEN_HEIGHT)
                throw new IllegalArgumentException();
            transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

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
            System.out.println(SCREEN_HEIGHT);
            System.out.println(transgc.toString());
        } // end catch
    }

    public void setCanvasSize(double width, double height)
    {
        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;
    }

    public DrawingCanvas(double width, double height)
    {
        System.out.println("Creating new Drawing Canvas");
        SCREEN_HEIGHT = height;
        SCREEN_WIDTH = width;
    }

    public boolean toggleGrid()
    {
        if (gridOn)
            gridOn = false;
        else
            gridOn = true;
        return gridOn;
    }

    public void reDraw(GraphicsContext gc)
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    }
    public void drawGrid(GraphicsContext gc)
    {
        for (int x = gridSpacing; x < SCREEN_WIDTH - gridSpacing; x = x + gridSpacing)
        {
            GridLine line = new GridLine(x, 0, x, SCREEN_HEIGHT);
            line.draw(gc);
        }
        for (int y = gridSpacing; y < SCREEN_HEIGHT - gridSpacing; y = y + gridSpacing)
        {
             GridLine line = new GridLine(0, y, SCREEN_WIDTH, y);
             line.draw(gc);
        }
    }
}
