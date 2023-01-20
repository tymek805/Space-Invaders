package gui;

import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class GUI extends JFrame {
    private ArrayList<BulletPlayer> bullets;
    private ArrayList<BulletSpaceship> enemyBullets;
    private ArrayList<Spaceship> enemies;
    int direction;
    int indirectTime = 0;
    boolean alivePlayer;

    private final MainBodyPanel mainBodyPanel;
    private boolean left = false;
    private boolean right = false;
    private final Player playerObject;
    private boolean interrupted = false;
    public GUI(){
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocationRelativeTo(null);

        mainBodyPanel = new MainBodyPanel();
        this.add(mainBodyPanel);

        //Player
        int x_Position = 400;
        int y_Position = 500;
        playerObject = new Player(40, 40, 5);
        playerObject.setBounds(x_Position, y_Position);
        mainBodyPanel.add(playerObject);
        alivePlayer = true;

        //Enemies
        enemies = new ArrayList<Spaceship>();
        int x_enemy = 100;
        int y_enemy = 50;
        for (int i = 0; i < 3; i++) {
            Spaceship enemy = new Spaceship(60,44,3);
            enemy.setBackground(Color.BLUE);
            enemy.setBounds(x_enemy*i, y_enemy);
            enemies.add(enemy);
            mainBodyPanel.add(enemy);
        }
        direction = -1 * enemies.get(0).getSpeed();

        //Bullets
        bullets = new ArrayList<BulletPlayer>();
        enemyBullets = new ArrayList<BulletSpaceship>();

        // Timer
        JLabel timeLabel = new JLabel();
        timeLabel.setForeground(Color.CYAN);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setBounds(0,350,100,50);
        java.util.Timer timer = new Timer();
        gameTimer clock = new gameTimer(timeLabel);
        timer.schedule(clock, 0, 1000);
        this.add(timeLabel,BorderLayout.NORTH);

        this.setVisible(true);
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

                    if (e.getKeyCode() == KeyEvent.VK_SPACE){
                        BulletPlayer bulletP = new BulletPlayer(playerObject.getX()+((playerObject.getWidth()/2)),(playerObject.getY()-(playerObject.getHeight()/2)),9,new JLabel("|"),1);
                        bulletP.setLabel();
                        mainBodyPanel.add(bulletP.getBulletLabel());
                        bullets.add(bulletP);
                        mainBodyPanel.revalidate();
                        mainBodyPanel.repaint();
                    }
                }

            }
        });

        new Thread(() -> {
            try {
                while (true) {
                    int newPositionX = playerObject.getX();
                    if (left)
                        newPositionX -= playerObject.getSpeed();
                    else if (right)
                        newPositionX += playerObject.getSpeed();

                    if (newPositionX >= 0 && newPositionX <= 750)
                        playerObject.setBounds(newPositionX, playerObject.getY());
                    playerBulletRemoveOnBorder();
                    enemyBulletRemoveOnBorder();
                    enemyMove();
                    int currentTimer = (int) (System.currentTimeMillis()-clock.getStartTime());
                    if (currentTimer - indirectTime >= 1000){
                        enemyShot();
                        indirectTime += currentTimer;
                    }
                    int limit = enemies.size();
                    for (int i = 0; i < limit; i++) {
                        limit -= onHit(enemies.get(i));
                    }
                    playerHit(playerObject);
                    Thread.sleep(20);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }).start();

    }
    private void playerBulletRemoveOnBorder(){
        for (int i = 0; i < bullets.size(); i++) {
            BulletPlayer bulletP = bullets.get(i);
            JLabel bullet = bulletP.getBulletLabel();
            bullet.setBounds(bullet.getX(),bullet.getY()-bulletP.getSpeedOfTheBullet(),5,40);
            if (bullet.getY() == 0){
                bullets.remove(bullet);
                mainBodyPanel.remove(bullet);
            }
        }
        mainBodyPanel.revalidate();
        mainBodyPanel.repaint();
    }
    private int onHit(Spaceship enemy){
        int x = enemy.getX();
        int y = enemy.getY();
        int width = enemy.getWidth();
        int height = enemy.getHeight();

        for (int i = 0; i < bullets.size(); i++) {
            BulletPlayer bulletP = bullets.get(i);
            JLabel bullet = bulletP.getBulletLabel();
            if (bullet.getY() < (y+(height/2)) && bullet.getY() > y && bullet.getX() <= (x+width) && bullet.getX() > x){
                enemies.remove(enemy);
                mainBodyPanel.remove(enemy);
                bullets.remove(bullet);
                mainBodyPanel.remove(bullet);
                return 1;
            }
        }
        mainBodyPanel.revalidate();
        mainBodyPanel.repaint();
        return 0;
    }
    private void playerHit(Player player){
        int x = player.getX();
        int y = player.getY();
        int width = player.getWidth();
        int height = player.getHeight();

        for (int i = 0; i < enemyBullets.size(); i++) {
            BulletSpaceship bulletP = enemyBullets.get(i);
            JLabel bullet = bulletP.getBulletLabel();
            if ((bullet.getY()+bullet.getHeight()) > y && (bullet.getY()+bullet.getHeight()) < (y+height) && bullet.getX() <= (x+width) && bullet.getX() > x){
                enemies.remove(player);
                mainBodyPanel.remove(player);
                bullets.remove(bullet);
                mainBodyPanel.remove(bullet);
                alivePlayer = false;
            }
        }
        mainBodyPanel.revalidate();
        mainBodyPanel.repaint();
    }
    private void enemyMove(){
        if (enemies.size() != 0){
            if (enemies.get(0).getX() <= 0){
                direction = enemies.get(0).getSpeed();
            }else if(enemies.get(enemies.size()-1).getX() >= this.getWidth()-(enemies.get(enemies.size()-1).getWidth()*1.2)){
                direction = -1 * enemies.get(0).getSpeed();
            }
            for (Spaceship enemy : enemies) {
                enemy.setBounds(enemy.getX() + direction,enemy.getY(),enemy.getWidth(),enemy.getHeight());
            }
        }
    }
    private void enemyShot(){
        if (enemies.size() > 1){
            Random rand = new Random();
            int upperBound = enemies.size()-1;
            int chooseShotingEnemy = rand.nextInt(upperBound);
            shot(enemies.get(chooseShotingEnemy));
        } else if (enemies.size() == 1) {
            int chooseShotingEnemy = 0;
            shot(enemies.get(chooseShotingEnemy));
        }
    }
    private void shot(Spaceship enemy){
        BulletSpaceship bulletP = new BulletSpaceship(enemy.getX()+((enemy.getWidth()/2)),(enemy.getY()+(enemy.getHeight()/2)),9,new JLabel("|"),1);
        bulletP.setLabel();
        mainBodyPanel.add(bulletP.getBulletLabel());
        enemyBullets.add(bulletP);
        mainBodyPanel.revalidate();
        mainBodyPanel.repaint();
    }
    private void enemyBulletRemoveOnBorder(){
        for (int i = 0; i < enemyBullets.size(); i++) {
            BulletSpaceship bulletE = enemyBullets.get(i);
            JLabel bullet = bulletE.getBulletLabel();
            bullet.setBounds(bullet.getX(),bullet.getY()+bulletE.getSpeedOfTheBullet(),5,40);
            if (bullet.getY() > this.getHeight()){
                enemyBullets.remove(bullet);
                mainBodyPanel.remove(bullet);
            }
        }
        mainBodyPanel.revalidate();
        mainBodyPanel.repaint();
    }
}
