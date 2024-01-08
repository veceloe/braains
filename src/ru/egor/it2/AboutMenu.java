package ru.egor.it2;

import com.wizylab.duck2d.Game;
import com.wizylab.duck2d.Graph;
import com.wizylab.duck2d.View;

import java.awt.*;

import static com.wizylab.duck2d.Keyboard.onKey;
import static java.awt.event.KeyEvent.VK_ESCAPE;

public class AboutMenu implements View {
    @Override
    public void onTimer(long l) {
        if (onKey(VK_ESCAPE)) Game.start(new StandardMenu());
    }

    @Override
    public void onDraw(Graph g) {
        g.setBackground(Color.BLACK);
        g.setColor(Color.RED);
        g.setTextStyle(14, 1, 30);
        g.text(40, 200, " This game tells you how to ");
        g.text(40, 230, " defend yourself in the apocalypse and");
        g.text(40, 260, " how not to fall into the  hands  of a ");
        g.text(40, 290, " zombie. All you have to  do is  shoot ");
        g.text(40, 320, " (Enter key), move (WASD buttons)  and");
        g.text(40, 350, " that's it. The goal: to live as long");
        g.text(40, 380, " as possible in this madness. Collect");
        g.text(40, 410, " weapons, first-aid kits, armor, power-");
        g.text(40, 440, " ups, shoot,run away, win - that's all.");
    }
}
