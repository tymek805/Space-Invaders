package logic;

public class BulletPlayer extends Bullet{

    private int placeHolderImage;  //to be changed for an image

    public BulletPlayer(int x, int y, int speedOfTheBullet, int placeHolderImage) {
        super(x, y, speedOfTheBullet);
        this.placeHolderImage = placeHolderImage;
    }

    public int getPlaceHolderImage() {
        return placeHolderImage;
    }

    public void setPlaceHolderImage(int placeHolderImage) {
        this.placeHolderImage = placeHolderImage;
    }
}

