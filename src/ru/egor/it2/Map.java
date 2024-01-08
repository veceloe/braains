package ru.egor.it2;

import com.wizylab.duck2d.Graph;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Map {
    private int[][] layout = new int[8][8];
    public Rectangle[] walls;

    public void load(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            for (int x = 0; x < 8; x++)
                for (int y = 0; y < 8; y++)
                    layout[x][y] = sc.nextInt();
            int count = sc.nextInt();
            walls = new Rectangle[count];
            for (int i = 0; i < count; i++)
                walls[i] = new Rectangle(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graph g) {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                g.putImage("map/" + layout[x][y], x * 100, y * 75, 100, 75);
        // draw walls
        g.setFillColor(new Color(218, 170, 117));
        for (Rectangle wall : walls)
            g.fillRect(wall.x, wall.y, wall.x + wall.width, wall.y + wall.height);
    }
}