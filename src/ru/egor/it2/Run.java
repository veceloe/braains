package ru.egor.it2;

import com.wizylab.duck2d.Game;

public class Run {
    public static void main(String[] args) {
        Game.addView(new StandardMenu());
        Game.start(new StandardMenu());
    }
}