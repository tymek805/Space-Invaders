package gui;

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

    public GUI(){
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,600);

//        mainBody
        mainBodyPanel = new MainBodyPanel();
        this.add(mainBodyPanel,BorderLayout.CENTER);

//        player
        Player player1 = new Player(400);
        player = new JLabel("Statek");
        //TODO:resize the player icon to sth around 60-80px and change icon source image place to graphic folder
        player.setIcon(new ImageIcon(getClass().getResource("playerLabel.png")));
//        player.setSize(40,40);
        player.setBounds(x_position, y_position, player_width, player_height);
//        player.setForeground(Color.RED);
        mainBodyPanel.add(player);

//        timer
        timeLabel = new JLabel();
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.CYAN);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setBounds(0,350,100,50);
        java.util.Timer timer = new Timer();
        timer.schedule(new gameTimer(timeLabel), 0, 1000);
        this.add(timeLabel,BorderLayout.NORTH);

        this.addKeyListener(this);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        int speed = 3;
        boolean moveLeft = keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A;
        boolean moveRight = keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D;
        if (moveLeft || moveRight){
            if (moveLeft){
//                System.out.println("Ruch w lewo");
                x_position -= speed;
            }else if (moveRight){
//                System.out.println("Ruch w prawo");
                x_position += speed;
            }
            player.setBounds(x_position, y_position, player_width, player_height);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
