package gui;

import javax.swing.JPanel;

public class MainBodyPanel extends JPanel {
    JPanel mainBodyPanel;

    public MainBodyPanel() {
        mainBodyPanel = new JPanel();
        this.setLayout(null);
        // this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public JPanel getMainBodyPanel() {
        return mainBodyPanel;
    }

    public void setMainBodyPanel(JPanel mainBodyPanel) {
        this.mainBodyPanel = mainBodyPanel;
    }
}
