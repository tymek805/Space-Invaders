package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainBodyPanel extends JPanel {
    JPanel mainBodyPanel;

    public MainBodyPanel() {
        mainBodyPanel = new JPanel();
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.addKeyListener();
        this.setVisible(true);
    }

    public JPanel getMainBodyPanel() {
        return mainBodyPanel;
    }

    public void setMainBodyPanel(JPanel mainBodyPanel) {
        this.mainBodyPanel = mainBodyPanel;
    }
}
