package logic;

public class Player {

    private int xPos;
    public int speed;

    public Player(int xPos, int speed) {
        this.xPos = xPos;
        this.speed = speed;
    }

    public void shoot() {

    }

    // Getters and setters
    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }
}
