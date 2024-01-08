package ru.egor.it2;

import com.wizylab.duck2d.Graph;

import java.awt.*;

import static ru.egor.it2.GameView.BOUNDS;

public class Bullet {
    public double speed= 1;
    public boolean isStrike = false;
    public double x, y;
    public double angle;

    public Bullet(double speed) {
        this.speed=speed;
        set(-100, -100);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(double x, double y, double angle) {
        this.angle = angle;
        set(x, y);
    }

    public void move() {
        x += Math.cos(angle * Math.PI / 180) * speed;
        y += Math.sin(angle * Math.PI / 180) * speed;
        if (!BOUNDS.contains(x, y)) set (-100, -100);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 4, 4);
    }

    public void draw(Graph g) {
        g.putImage("weapon/bullet", (int)x, (int)y, 4, 4);
    }
}
