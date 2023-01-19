package gui;

import logic.Player;
import logic.gameTimer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class GUI extends JFrame {
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

        int x_Position = 400;
        int y_Position = 500;
        playerObject = new Player(40, 40, 5);
        playerObject.setBounds(x_Position, y_Position);
        mainBodyPanel.add(playerObject);

        // Timer
        JLabel timeLabel = new JLabel();
        timeLabel.setForeground(Color.CYAN);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setBounds(0,350,100,50);
        java.util.Timer timer = new Timer();
        timer.schedule(new gameTimer(timeLabel), 0, 1000);
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
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) left = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) right = true;

                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    JLabel bulletLabel = new JLabel("|");
                    bulletLabel.setForeground(Color.RED);
                    bulletLabel.setBounds(playerObject.getX()+((playerObject.getWidth()/2)-(bulletLabel.getWidth()/2)),(playerObject.getY()-(playerObject.getHeight()/2)),5,40);
                    mainBodyPanel.add(bulletLabel);
                    mainBodyPanel.revalidate();
                    mainBodyPanel.repaint();
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

                    Thread.sleep(30);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(0);
            }
        }).start();
    }
}
