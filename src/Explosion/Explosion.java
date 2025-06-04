package Explosion;

import java.awt.*;

public class Explosion {
    private int x;
    private int y;
    private int radius;
    private int life;
    private static Image image1;
    private static Image image2;
    private static Image image3;

    public Explosion(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.life = 9;
        image1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/bomb_3.gif"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getRadius() {
        return radius;
    }


    public Image explode() {
        this.life -- ;
        if (life > 6) {
            return image1;
        } else if (life > 3) {
            return image2;
        } else if (life > 0) {
            return image3;
        } else return null;
    }
}
