package logic;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimerTask;

public class gameTimer extends TimerTask {
    JLabel currentDateTime;
    long startTime = System.currentTimeMillis();

    public long getStartTime() {
        return startTime;
    }

    public gameTimer(JLabel aLabel) {
        this.currentDateTime = aLabel;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(
                new Runnable() {

                    public void run() {

                        long elapsedTime = System.currentTimeMillis() - startTime;
                        DateFormat dateandtime = new SimpleDateFormat("mm:ss");
                        currentDateTime.setText(dateandtime.format(elapsedTime));

                    }
                });
    }
}