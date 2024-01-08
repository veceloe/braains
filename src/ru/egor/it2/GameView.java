package ru.egor.it2;

import com.wizylab.duck2d.Environment;
import com.wizylab.duck2d.Graph;
import com.wizylab.duck2d.View;
import ru.egor.it2.weapons.*;

import java.awt.*;

import static com.wizylab.duck2d.Keyboard.hasKey;
import static com.wizylab.duck2d.Keyboard.onKey;
import static java.awt.event.KeyEvent.*;

public class GameView implements View {
    public static final Rectangle BOUNDS = new Rectangle(0, 0, 800, 600);
    Map map = new Map();
    Player player = new Player();
    Weapon[] weapons = new Weapon[]{new RifleWeapon(), new ShotgunWeapon(), new PistolWeapon(), new PistolWeapon(), new PistolWeapon(), new ShotgunWeapon(), new RifleWeapon(), new PistolWeapon(), new ShotgunWeapon(), new PistolWeapon(), new SMGWeapon(), new SMGWeapon(), new SMGWeapon(), new SMGWeapon(), new RifleWeapon()};
    Bullet[] bullets = new Bullet[15];
    Enemy[] enemies = new Enemy[10];
    Ammo[] ammo = new Ammo[10];
    int time = 0;


    public GameView() {
        Environment.put("game", this);
        Environment.put("player", player);
        Environment.put("ammo", ammo);
        map.load("map" + (int)(Math.random() * 5) + ".txt");
    }

    @Override
    public void onShow() {
        for (int i = 0; i < bullets.length; i++) bullets[i] = new Bullet(20);
        for (int i = 0; i < enemies.length; i++) enemies[i] = new Enemy((int)(Math.random() * 4));
        for (int i = 0; i < ammo.length; i++) ammo[i] = new Ammo((int)(Math.random() * 4));
    }

    @Override
    public void onTimer(long l) {
        time++;
        if (onKey(VK_ESCAPE)) System.exit(0);
        if (hasKey(VK_DOWN) && !isWall(player.x, player.y + Player.SPEED) && player.y < 545) {
            player.y += Player.SPEED;
            player.angle = 90;
        }
        if (hasKey(VK_UP) && !isWall(player.x, player.y - Player.SPEED) && player.y > -10) {
            player.y -= Player.SPEED;
            player.angle = 270;
        }
        if (hasKey(VK_RIGHT) && !isWall(player.x + Player.SPEED, player.y) && player.x < 770) {
            player.x += Player.SPEED;
            player.angle = 0;
        }
        if (hasKey(VK_LEFT) && !isWall(player.x - Player.SPEED, player.y) && player.x > -10) {
            player.x -= Player.SPEED;
            player.angle = 180;
        }
        if (hasKey(VK_ENTER) && player.bullets > 0)
            player.weapon.onFire(player);

        Rectangle rec = new Rectangle((int) player.x, (int) player.y, 36, 60);

        for (Weapon weapon : weapons) {
            if (!rec.intersects(weapon)) continue;
            player.weapon = weapon;
            weapon.x = 1000;
        }
        for (Enemy enemy : enemies) enemy.move();

        player.weapon.onTimer(enemies);

        for (Enemy enemy : enemies)
            if (player.getBounds().intersects(enemy.getBounds())) enemy.atack(player);
            for (Ammo ammo: ammo){
                if (player.getBounds().intersects(ammo.getBounds()) && ammo.type == 1) {
                    player.bullets += 30;
                    ammo.set(900, 600);
                }
                if (player.getBounds().intersects(ammo.getBounds()) && ammo.type == 2) {
                    if (player.hp + 10 < 101) player.hp+=25;
                    ammo.set(900, 600);
                }
                if (player.getBounds().intersects(ammo.getBounds()) && ammo.type == 3) {
                    player.hp += 50;
                    ammo.set(900, 600);
                }
                for (int i = (int) (Math.random() * 4); i<weapons.length; i++) {
                    if (weapons[i].x > 800 && time == 10000) {
                        weapons[i].spawn();
                        time = 0;
                    }
                }
            }
            }

    boolean isWall(double x, double y) {
        Rectangle player = new Rectangle((int) x, (int) y, 36, 60);
        for (Rectangle wall : map.walls)
            if (player.intersects(wall)) return true;
        return false;
    }

    @Override
    public void onDraw(Graph g) {
        map.draw(g);
        player.weapon.drawShot(g);
        player.draw(g);
        for (Enemy enemies : enemies) enemies.draw(g);
        for (Ammo ammo: ammo) ammo.draw(g);
        for (Weapon weapon : weapons) weapon.draw(g);

        g.setColor(Color.WHITE);
        g.setTextStyle(14, 1, 15);
        g.text(10, 20, "Score: " + player.score);
        g.text(10, 35, "Health: " + player.hp);
        g.text(10, 50, "Bullets: " + player.bullets);
        if (player.bullets == 0) {
            g.setColor(Color.RED);
            g.setTextStyle(14, 1, 50);
            g.text(250, 400, "NO AMMO!!! ");
        }
        if (time > 8000 && time < 9500){
            g.setColor(Color.CYAN);
            g.setTextStyle(14, 1, 30);
            g.text(250, 450, "Weapon on way! ");
        }
    }
}