package ru.egor.it2.weapons;

import com.wizylab.duck2d.Graph;
import ru.egor.it2.Enemy;
import ru.egor.it2.Player;

import java.awt.*;

public abstract class Weapon extends Rectangle {
    private static int WIDTH = 24, HEIGHT = 9;
    private String texture;
    private int type;
    private int bullets;

    public Weapon(String texture, int type) {
        super(0, 0, WIDTH, HEIGHT);
        x = (int) (800 + Math.random() * 201);
        y = (int) (600 + Math.random() * 201);
        this.texture = texture;
        this.type = type;
    }

    public void draw(Graph g) {
        g.putImage(texture, x, y, width, height);
    }

    public int getType() {
        return type;
    }

    public void spawn(){
        x = (int) (Math.random() * 801);
        y = (int) (Math.random() * 601);
    }

    public abstract void onTimer(Enemy[] enemies);

    public abstract void onFire(Player player);

    public abstract void drawShot(Graph g);
}