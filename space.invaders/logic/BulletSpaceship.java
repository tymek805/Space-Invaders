package logic;

import javax.swing.*;
import java.awt.*;

public class BulletSpaceship extends Bullet{

    private JLabel bulletLabel;
    private int placeHolderImage;  //to be changed for an image
    private int bulletWidth = 5;
    private int bulletHeight = 40;

    public BulletSpaceship(int x, int y, int speedOfTheBullet, JLabel bulletLabel, int placeHolderImage) {
        super(x, y, speedOfTheBullet);
        this.bulletLabel = bulletLabel;
        this.placeHolderImage = placeHolderImage;
    }

    public void setLabel(){
        bulletLabel = new JLabel("|");
        bulletLabel.setForeground(Color.RED);
        bulletLabel.setBounds(this.getX()-(bulletWidth/2),this.getY()-(bulletHeight/2),bulletWidth,bulletHeight);
    }

    public JLabel getBulletLabel() {
        return bulletLabel;
    }

    public void setBulletLabel(JLabel bulletLabel) {
        this.bulletLabel = bulletLabel;
    }

    public int getPlaceHolderImage() {
        return placeHolderImage;
    }

    public void setPlaceHolderImage(int placeHolderImage) {
        this.placeHolderImage = placeHolderImage;
    }
}
