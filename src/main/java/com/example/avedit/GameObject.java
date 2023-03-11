package com.example.avedit;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {

    private String name;
    public GameObject(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public abstract void draw(GraphicsContext gc);

}
