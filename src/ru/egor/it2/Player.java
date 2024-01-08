package ru.egor.it2;

import com.wizylab.duck2d.Game;
import com.wizylab.duck2d.Graph;
import ru.egor.it2.weapons.HandsWeapon;
import ru.egor.it2.weapons.Weapon;

import java.awt.*;

public class Player {
    public static final double SPEED = 0.3;
    public Weapon weapon = new HandsWeapon();
    public double x;
    public double y;
    public int hp = 100;
    public int angle = 0;
    int score = 0;
    public int bullets =35;


    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) Game.start(new KilledMenu());
    }
        public void attack(Enemy[] enemies){
            for (Enemy enemy : enemies) {
                if (getBounds().intersects(enemy.getBounds())) enemy.hp-=3;
                }
            }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 36, 60);
    }

    public void draw(Graph g) {

        g.putImage("player/" + weapon.getType() + "/" + angle, (int)x, (int)y, 36, 60);
    }
}
