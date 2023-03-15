package com.example.avedit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WallObject extends GameObject
{
    private double sx;
    private double sy;
    private double ex;
    private double ey;
    private Color wallColour;
    private Color wallBorder;
    private int borderWdith;
    private boolean isSelected;
    public WallObject(String name, double sx, double sy, double ex, double ey, Color newColour, Color newBorder)
    {
        super(name);
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        this.wallColour = newColour;
        this.wallBorder = newBorder;
        this.borderWdith = 2;
        isSelected = true;
    }

    public void setWallColour(Color newColour)
    {
        wallColour = newColour;
    }

    public void setWallBorder(Color newColour)
    {
        wallBorder = newColour;
    }

    public Color getColour()
    {
        return wallColour;
    }

    public void selectObject()
    {
        isSelected = false;
    }

    public void deselectObject()
    {
        isSelected = false;
    }

    public void draw(GraphicsContext gc)
    {
        //System.out.println("sx: " + sx + "  sy: " + sy + "  ex:" + ex + "  ey:" + ey);
        //System.out.println("Drawing Object");
        gc.setFill(wallColour);
        gc.fillRect(sx, sy, ex, ey);
        gc.setStroke(wallBorder);
    }
}
