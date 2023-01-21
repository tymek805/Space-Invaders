package gui;

import logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends JFrame {
    private final JPanel panel;
    private final Player player;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private int direction;
    private int indirectTime = 0;
    private boolean left = false;
    private boolean right = false;
    private boolean alivePlayer = true;
    public GUI(){
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // Main panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        panel.setVisible(true);
        this.add(panel);

        //Player
        player = new Player(40, 40, 5, panel);
        player.setBounds(380, 470);
        panel.add(player);

        //Enemies
        int x_enemy = 0;
        int y_enemy = 50;
        for (int j = 0; j < 11; j++) {
            Enemy enemy = new Enemy(30,22,1, panel);
            enemy.setBounds(x_enemy, y_enemy);
            enemies.add(enemy);
            panel.add(enemy);
            x_enemy += enemy.getWidth() * 1.5;
        }
        direction = -1 * enemies.get(0).getSpeed();

        // Timer
        GameTimer gameTimer = new GameTimer();
        this.add(gameTimer.getTimeLabel(),BorderLayout.NORTH);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) left = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) right = false;
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (alivePlayer){
                    if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) left = true;
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) right = true;

                    if (e.getKeyCode() == KeyEvent.VK_SPACE && player.getReloaded()){
                        bullets.add(player.makeBullet());
                    }
                }

            }
        });

        new Thread(() -> {
            try {
                while (true) {
                    int newPositionX = player.getX();
                    if (left)
                        newPositionX -= player.getSpeed();
                    else if (right)
                        newPositionX += player.getSpeed();

                    if (newPositionX >= 0 && newPositionX <= 750)
                        player.setBounds(newPositionX, player.getY());

                    removeBulletOnBorder();
                    enemyMove();
                    playerHit();

                    int currentTimer = (int) (System.currentTimeMillis() - gameTimer.getStartTime());
                    if (currentTimer - indirectTime >= 1000){
                        enemyShot();
                        indirectTime += currentTimer;
                    }

                    for (int i = 0; i < enemies.size(); i++) {
                        enemyHit(enemies.get(i));
                    }

                    panel.revalidate();
                    panel.repaint();
                    Thread.sleep(20);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }).start();
        setVisible(true);
    }
    private boolean enemyHit(Enemy enemy){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.getDirection() == -1 && enemy.isHit(bullet)){
                enemies.remove(enemy);
                bullets.remove(bullet);
                return true;
            }
        }
        return false;
    }
    private void playerHit(){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.getDirection() == 1 && player.isHit(bullet)){
                alivePlayer = false;
            }
        }
    }

    private void enemyMove(){
        if (enemies.size() != 0){
            if (enemies.get(0).getX() <= 0){
                direction = enemies.get(0).getSpeed();
            }else if(enemies.get(enemies.size()-1).getX() >= this.getWidth() - (enemies.get(enemies.size()-1).getWidth() * 1.5)){
                direction = -1 * enemies.get(0).getSpeed();
            }

            for (Enemy enemy : enemies) {
                enemy.setBounds(enemy.getX() + direction, enemy.getY());
            }
        }
    }
    private void enemyShot(){
        int chooseShootingEnemy = 0;
        if (enemies.size() > 1)
            chooseShootingEnemy = new Random().nextInt(enemies.size() - 1);

        bullets.add(enemies.get(chooseShootingEnemy).makeBullet());
    }
    private void removeBulletOnBorder(){
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
            if (!(panel.getBounds().intersects(bullets.get(i).getBounds()))){
                panel.remove(bullets.get(i));
                bullets.remove(bullets.get(i));
            }
        }
    }
}
