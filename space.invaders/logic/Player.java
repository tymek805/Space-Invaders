package logic;

import javax.swing.*;

public class Player extends GameObject {
    private boolean reloaded = true;

    public Player(int width, int height, int speed, JPanel panel) {
        super(width, height, speed, panel, "starship.png", -1);
    }

    public Bullet makeBullet(){
        if (reloaded){
            reloading();
            return super.makeBullet();
        }
        return null;
    }
    public boolean getReloaded(){return reloaded;}
    private void reloading(){
        reloaded = false;
        new Thread(() -> {
            try{
                Thread.sleep(1000);
                reloaded = true;
            }catch (InterruptedException ignored){};
        }).start();
    }
}
