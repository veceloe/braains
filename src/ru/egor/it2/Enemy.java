package ru.egor.it2;

import com.wizylab.duck2d.Environment;
import com.wizylab.duck2d.Graph;

import java.awt.*;

public class Enemy {
    public final int type;
    public double speed = 0.1 + Math.random() * 0.3;
    public double x, y;
    private long last = 0;
    public int hp;
    public Enemy(int type) {
        this.type = type;
        respawn();
    }

    public void move() {
        x -= speed;
        if (x < -24) {
            respawn();
        }
    }

    public void respawn() {
        x = 800 + Math.random() * 300;
        y = Math.random() * 600;
        hp = 50;
    }

    public void atack(Player player) {
        long time = System.currentTimeMillis();
        if (time - last < 500) return;
        player.damage((int) (10 + Math.random() * 30));
        last = time;
        int typer = (int)(Math.random()*3);
    }

    public void damage(int damage) {
        hp -= damage;
        Player player = Environment.get("player");
        Ammo ammo[] = Environment.get("ammo");
        if (hp < 0) {
            for (Ammo ammos: ammo) if (ammos.x>800) {
                ammos.set((int)x, (int)y);
                break;
        }
            respawn();
            player.score++;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 24, 52);
    }

    public void draw(Graph g) {
        g.putImage("enemy" + type, (int)x, (int)y, 24, 52);
    }
}
