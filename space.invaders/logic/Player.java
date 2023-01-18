package logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Player {

    private int xPos;

    public Player(int xPos){
        this.xPos = xPos;
    }

    public void shoot(){

    }

    //Getters and setters
    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }
}
