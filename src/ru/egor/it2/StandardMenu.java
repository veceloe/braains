package ru.egor.it2;

import com.wizylab.duck2d.*;

import java.awt.*;

import static com.wizylab.duck2d.Keyboard.onKey;
import static java.awt.event.KeyEvent.VK_ESCAPE;

public class StandardMenu implements View {
    private static final Rectangle[] BUTTONS = {
            new Rectangle(322, 100, 165, 60),
            new Rectangle(322, 160, 165, 60),
            new Rectangle(100, 200, 200, 75),
            new Rectangle(100, 300, 200, 75)};
    int value;

    @Override
    public void onTimer(long l) {
        if (onKey(VK_ESCAPE)) System.exit(0);
        value = -1;
        for (int i = 0; i < BUTTONS.length; i++)
            if (BUTTONS[i].contains(Mouse.x(), Mouse.y())) value = i;

        if (Mouse.onClick(MouseButton.LEFT)) {
            if (value == 0) Game.start(new GameView());
            if (value == 1) Game.start(new AboutMenu());
        }
    }

    @Override
    public void onDraw(Graph g) {
        g.putImage("bg", 0, 0, 800, 600);
        g.putImage("b/1",322.5, 100, 165, 60);
        g.putImage("b/2",322.5, 160, 165, 60);
        }
    }