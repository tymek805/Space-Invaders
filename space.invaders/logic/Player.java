package logic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends JLabel {

    private final int speed;
    private final int width;
    private final int height;

    public Player(int width, int height, int speed) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.setIcon(setImageIcon());
    }

    public void setBounds(int positionX, int positionY){
        super.setBounds(positionX, positionY, width, height);
    }

    public int getSpeed() {return speed;}

    private ImageIcon setImageIcon(){
        try{
            Image img = ImageIO.read(new File("space.invaders\\graphic\\starship.png"));
            return new ImageIcon(img.getScaledInstance(width, height, 0));
        }catch (IOException e){System.out.println("Image not found!");}
        return null;
    }
}
