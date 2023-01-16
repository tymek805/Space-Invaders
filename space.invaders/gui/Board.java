package gui;

import logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {
    JPanel mainBodyPanel;
    JLabel player;

    public Board(){
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,600);

//        mainBody
        mainBodyPanel = new JPanel();
        mainBodyPanel.setLayout(null);
        mainBodyPanel.setBackground(Color.BLACK);
        this.add(mainBodyPanel);

//        player
        Player player1 = new Player(400);
        player = new JLabel("Statek");
//        player.setSize(40,40);
        player.setBounds(400,500,40,40);
        player.setForeground(Color.RED);

        mainBodyPanel.add(player);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
