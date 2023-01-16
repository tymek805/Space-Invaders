package gui;

import javax.swing.JFrame;
import java.awt.*;

public class Board extends JFrame {

    public Board(){
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(800,600);
        this.setVisible(true);
    }
}
