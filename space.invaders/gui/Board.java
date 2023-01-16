package gui;

import jdk.jfr.Event;
import logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//TODO: przenieść panel do nowej klasy

public class Board extends JFrame implements KeyListener {
    JPanel mainBodyPanel;
    JLabel player;
    int player_width = 40;
    int player_height = 40;
    int x_position = 400;
    int y_position = 500;

    public Board(){


        EventQueue.invokeLater(()->{
            this.setTitle("Space Invaders");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
            this.setSize(800,600);

//        mainBody
            mainBodyPanel = new JPanel();
            mainBodyPanel.setLayout(null);
            mainBodyPanel.setBackground(Color.BLACK);
            this.addKeyListener(this);
            this.add(mainBodyPanel);

//        player
            Player player1 = new Player(400);
            player = new JLabel("Statek");
//        player.setSize(40,40);
            player.setBounds(x_position,y_position,player_width,player_height);
            player.setForeground(Color.RED);

            mainBodyPanel.add(player);

            this.setVisible(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_UP:
                // handle up
                break;
            case KeyEvent.VK_DOWN:
                // handle down
                break;
            case KeyEvent.VK_LEFT:
                // handle left
                System.out.println("Ruch w lewo");
                player.setBounds(x_position,y_position,player_width,player_height);
                mainBodyPanel.revalidate();
                mainBodyPanel.repaint();
                break;
            case KeyEvent.VK_RIGHT :
                // handle right
                int speed = 3;
                int temp = x_position+speed;
                System.out.println("Ruch w prawo");
                player.setBounds(temp,y_position,player_width,player_height);
                mainBodyPanel.revalidate();
                mainBodyPanel.repaint();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
