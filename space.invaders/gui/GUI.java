package gui;

import logic.BulletPlayer;
import logic.Player;
import logic.gameTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class GUI extends JFrame implements KeyListener {
    MainBodyPanel mainBodyPanel;
    JLabel player;
    JLabel timeLabel;
    final int player_width = 40;
    final int player_height = 40;
    int x_position = 400;
    final int y_position = 500;
//New motion
    private boolean left = false;
    private boolean right = false;
    private boolean shotPlayer = false;


    public GUI(){
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,600);

//        mainBody
        mainBodyPanel = new MainBodyPanel();
        this.add(mainBodyPanel,BorderLayout.CENTER);

//        player
        Player player1 = new Player(400,5);
        player = new JLabel("Statek");
        //TODO:resize the player icon to sth around 60-80px and change icon source image place to graphic folder
        player.setIcon(new ImageIcon(getClass().getResource("playerLabel.png")));
        player.setBounds(x_position, y_position, player_width, player_height);
        mainBodyPanel.add(player);

//        timer
        timeLabel = new JLabel();
        timeLabel.setForeground(Color.CYAN);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setBounds(0,350,100,50);
        java.util.Timer timer = new Timer();
        timer.schedule(new gameTimer(timeLabel), 0, 1000);
        this.add(timeLabel,BorderLayout.NORTH);

        this.addKeyListener(this);
        this.setVisible(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) left = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = false;
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    JLabel bulletLabel = new JLabel("|");
                    bulletLabel.setForeground(Color.RED);
                    bulletLabel.setBounds(player.getX()+((player_width/2)-(bulletLabel.getWidth()/2)),(player.getY()-(player_height/2)),5,40);
                    mainBodyPanel.add(bulletLabel);
                    mainBodyPanel.repaint();
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    while (true) {
                        if (left) {
                            player.setBounds(player.getX() - player1.speed, player.getY() , player_width, player_height);
                        } else if (right) {
                            player.setBounds(player.getX() + player1.speed, player.getY(), player_width, player_height);
                        }
                        Thread.sleep(30);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(0);
                }
            }
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
