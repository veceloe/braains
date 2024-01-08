package ru.egor.it2.weapons;

import com.wizylab.duck2d.Graph;
import ru.egor.it2.Bullet;
import ru.egor.it2.Enemy;
import ru.egor.it2.Player;

import static ru.egor.it2.GameView.BOUNDS;

public class RifleWeapon extends Weapon {
    private static int DAMAGE = 15;
    private Bullet[] bullets = new Bullet[15];
    private long time = 0;
    private int reload = 1;

    public RifleWeapon() {
        super("weapon/1", 1);
        for (int i = 0; i < bullets.length; i++) bullets[i] = new Bullet(1.3);
    }

    public void bulletMove() {
        for (Bullet bullet : bullets) bullet.move();
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
        if (time - this.time < 300) return;
        for (Bullet bullet : bullets) {
            if (BOUNDS.contains(bullet.x, bullet.y)) continue;
            bullet.set(player.x + 12, player.y + 30, player.angle);
            player.bullets-=reload;
            this.time = time;
            break;
        }
    }

    @Override
    public void drawShot(Graph g) {
        for (Bullet bullet : bullets)
            if (BOUNDS.contains(bullet.x, bullet.y)) {
                bullet.draw(g);
            }
    }
}