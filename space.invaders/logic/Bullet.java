package logic;

public abstract class Bullet {

    private int x;
    private int y;
    private int speedOfTheBullet;

    public Bullet(int x, int y, int speedOfTheBullet) {
        this.x = x;
        this.y = y;
        this.speedOfTheBullet = speedOfTheBullet;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedOfTheBullet() {
        return speedOfTheBullet;
    }

    public void setSpeedOfTheBullet(int speedOfTheBullet) {
        this.speedOfTheBullet = speedOfTheBullet;
    }
}
