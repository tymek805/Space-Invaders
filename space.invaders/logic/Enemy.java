package logic;

import javax.swing.*;

public class Enemy extends GameObject {
    public Enemy(int width, int height, int speed, JPanel panel) {
        super(width, height, speed, panel, "enemy.png", 1);
    }
}