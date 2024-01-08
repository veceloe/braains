package ru.egor.it2.weapons;

import com.wizylab.duck2d.Graph;
import ru.egor.it2.Bullet;
import ru.egor.it2.Enemy;
import ru.egor.it2.Player;

import static ru.egor.it2.GameView.BOUNDS;

public class ShotgunWeapon extends Weapon {
    private static int DAMAGE = 10;
    private Bullet[] bullets = new Bullet[15];
    private long time = 0;
    private int reload = 5;

    public ShotgunWeapon() {
        super("weapon/2", 2);
        for (int i = 0; i < bullets.length; i++) bullets[i] = new Bullet(0.5);
    }

    public void bulletMove() {
        for (Bullet bullet : bullets) bullet.move();
    }

    private Bullet getFreeBullet() {
        for (Bullet bullet : bullets)
            if (!BOUNDS.contains(bullet.x, bullet.y)) return bullet;
        return null;
    }

    @Override
    public void onTimer(Enemy[] enemies) {
        for (Bullet bullet : bullets) {
            if (!BOUNDS.contains(bullet.x, bullet.y)) continue;
            bulletMove();
            for (Enemy enemy : enemies) {
                if (!bullet.getBounds().intersects(enemy.getBounds())) continue;
                bullet.set(-100, -100);
                enemy.damage(DAMAGE);
                break;
            }
        }
    }

    @Override
    public void onFire(Player player) {
        long time = System.currentTimeMillis();
        if (time - this.time < 1000) return;
        Bullet b1 = getFreeBullet();
        if (b1 != null) b1.set(player.x + 12, player.y + 30, player.angle + 30);
        Bullet b2 = getFreeBullet();
        if (b2 != null) b2.set(player.x + 12, player.y + 30, player.angle + 15);
        Bullet b0 = getFreeBullet();
        if (b0 != null) b0.set(player.x + 12, player.y + 30, player.angle);
        Bullet b3 = getFreeBullet();
        if (b3 != null) b3.set(player.x + 12, player.y + 30, player.angle - 15);
        Bullet b4 = getFreeBullet();
        if (b4 != null) b4.set(player.x + 12, player.y + 30, player.angle - 30);
        player.bullets-=reload;
        this.time = time;


    }

    @Override
    public void drawShot(Graph g) {
        for (Bullet bullet : bullets)
            if (BOUNDS.contains(bullet.x, bullet.y)) {
                bullet.draw(g);
            }
    }
}