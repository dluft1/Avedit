package com.example.avedit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridLine {
    private double ex, ey;
    private double sx, sy;
    Color gridColor = Color.LIGHTGRAY;

    public GridLine(double sx, double sy, double ex, double ey)
    {
        this.ex = ex;
        this.ey = ey;
        this.sx = sx;
        this.sy = sy;
    }

    public void draw(GraphicsContext gc)
    {
        gc.setStroke(gridColor);
        gc.setLineWidth(1.0);
        gc.strokeLine(sx, sy, ex, ey);
    }
}
