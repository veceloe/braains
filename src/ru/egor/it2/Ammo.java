package ru.egor.it2;

import com.wizylab.duck2d.Graph;

import java.awt.*;

public class Ammo {
    public int type;
    double x, y;
    static int wh = 30;
    public Ammo(int type){
        this.type=type;
        spawn();
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void spawn() {
        x = 800 + Math.random() * 300;
        y = Math.random() * 600;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, wh, wh);
    }

    public void draw(Graph g){
        g.putImage("ammo/" + type, (int)x, (int)y, wh, wh);
    }
}
