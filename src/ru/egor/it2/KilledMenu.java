package ru.egor.it2;

import com.wizylab.duck2d.*;

import java.awt.*;

import static com.wizylab.duck2d.Keyboard.onKey;
import static java.awt.event.KeyEvent.VK_ESCAPE;

public class KilledMenu implements View {
    Player player = Environment.get("player");
    int value;
    private static final Rectangle[] BUTTONS = {
            new Rectangle(100, 400, 150, 30),
            new Rectangle(500, 400, 150, 30)};
    @Override
    public void onShow() {

    }

    @Override
    public void onClose() {
    }

    @Override
    public void onTimer(long l) {
        if (onKey(VK_ESCAPE)) System.exit(0);
        value = -1;
        for (int i = 0; i < BUTTONS.length; i++)
            if (BUTTONS[i].contains(Mouse.x(), Mouse.y())) value = i;

        if (Mouse.onClick(MouseButton.LEFT)) {
            if (value == 1) Game.start(new GameView());
            if (value == 0) Game.start(new StandardMenu());
        }
    }

    @Override
    public void onDraw(Graph g) {
        g.setColor(Color.RED);
        g.setTextStyle(14, 1, 50);
        g.text(225, 200, "Game over :( ");
        g.text(200, 300, "Your score is " + player.score);
        for (Rectangle button : BUTTONS)
            g.rect(button.x, button.y, button.x + button.width, button.y + button.height);
        g.setTextStyle(14, 1, 20);
        g.text(120, 420, "Main menu");
        g.text(520, 420, "Play again");
    }
}
