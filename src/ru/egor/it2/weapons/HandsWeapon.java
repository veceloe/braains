package ru.egor.it2.weapons;

import com.wizylab.duck2d.Graph;
import ru.egor.it2.Enemy;
import ru.egor.it2.Player;

public class HandsWeapon extends Weapon {
    private Enemy enemies[];
int time = 0;
    public HandsWeapon() {
        super("", 0);
    }

    @Override
    public void onTimer(Enemy[] enemies) {
        this.enemies = enemies;
        time++;
    }

    @Override
    public void onFire(Player player) {
        if (enemies == null || time < 300) return;
        for (Enemy e : enemies) {
            double distance = Math.sqrt(Math.pow(e.x - player.x, 2) + Math.pow(e.y - player.y, 2));
            if (distance < 50) {
                e.damage(5);
                time = 0;
            }
        }
        player.attack(enemies);

    }

    @Override
    public void drawShot(Graph g) {
    }
}
