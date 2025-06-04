package Weapon;

public class Weapon implements Runnable {
    private int x;
    private int y;
    private int dir;
    private int speed = 10;
    private boolean onWay = false;
    public Weapon(int x, int y, int dir) {
        this.dir = dir;
        switch (dir) {
            case 0:
                this.x = x + 20;
                this.y = y;
                break;
            case 1:
                this.x = x + 20;
                this.y = y + 60;
                break;
            case 2:
                this.x = x - 10;
                this.y = y + 30;
                break;
            case 3:
                this.x = x + 50;
                this.y = y + 30;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOnWay() {
        return onWay;
    }

    public void setOnWay(boolean onWay) {
        this.onWay = onWay;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //    Simulate the movement of a bullet after it was fired
    public void move() {
        switch (this.dir){
            case 0:
                this.y -= speed;
                break;
            case 1:
                this.y += speed;
                break;
            case 2:
                this.x -= speed;
                break;
            case 3:
                this.x += speed;
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            this.move();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
