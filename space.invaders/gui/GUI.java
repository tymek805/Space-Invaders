package gui;

import logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//TODO: przenieść panel do nowej klasy

public class GUI extends JFrame implements KeyListener {
    MainBodyPanel mbp;
    
    JPanel mainBodyPanel;
    JLabel player;
    
    final int player_width = 40;
    final int player_height = 40;
    int x_position = 400;
    final int y_position = 500;

    public GUI() {
        mbp = new MainBodyPanel();

        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800, 600);

        this.add(mbp.getMainBodyPanel());
        mbp.getMainBodyPanel().addKeyListener(keyPressed(null));

        // player
        Player player1 = new Player(400);
        player = new JLabel("Statek");

        // player.setSize(40,40);
        player.setBounds(x_position, y_position, player_width, player_height);
        player.setForeground(Color.RED);

        mbp.getMainBodyPanel().add(player);
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
        if (moveLeft || moveRight) {
            if (moveLeft) {
                System.out.println("Ruch w lewo");
                x_position -= speed;
            } else if (moveRight) {
                System.out.println("Ruch w prawo");
                x_position += speed;
            }
            player.setBounds(x_position, y_position, player_width, player_height);
            mainBodyPanel.revalidate();
            mainBodyPanel.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
