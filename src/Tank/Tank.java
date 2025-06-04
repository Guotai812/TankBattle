package Tank;
import java.util.Vector;

public class Tank {
    private int x;
    private int y;
    private int dir;
    private int speed;
//    0 mean player, 1 mean enemy
    private int type;
    private Vector<Enemy> tanks = new Vector<>();

    public Tank(int x, int y, int dir , int type, int speed) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.type = type;
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

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setTanks(Vector<Enemy> tanks) {
        this.tanks = tanks;
    }

    private boolean isOverlap() {
        switch (this.getDir()) {
            case 0:
                for (Enemy e : tanks) {
                    if (e == this) {
                        continue;
                    }
                    if (e.getDir() == 0 || e.getDir() == 1) {
                        if((this.getX() >= e.getX()
                            && this.getX() <= e.getX() + 40
                            && this.getY() >= e.getY()
                            && this.getY() <= e.getY() + 60)
                            || (this.getX() + 40 >= e.getX()
                            && this.getX() + 40 <= e.getX() + 40
                            && this.getY() >= e.getY()
                            && this.getY() <= e.getY() + 60)) {
                            return true;
                        }
                    }

                    if (e.getDir() == 2 || e.getDir() == 3) {
                        if ((this.getX() >= e.getX() - 10
                                && this.getX() <= e.getX() + 50
                                && this.getY() >= e.getY() + 10
                                && this.getY() <= e.getY() + 50)
                                || (this.getX() + 40 >= e.getX() - 10
                                && this.getX() + 40 <= e.getX() + 50
                                && this.getY() >= e.getY() + 10
                                && this.getY() <= e.getY() + 50)) {
                            return true;
                        }
                    }
                }
                break;
            case 1:
                for (Enemy e : tanks) {
                    if (e == this) {
                        continue;
                    }
                    if (e.getDir() == 0 || e.getDir() == 1) {
                        if((this.getX() >= e.getX()
                            && this.getX() <= e.getX() + 40
                            && this.getY() + 60 >= e.getY()
                            && this.getY() + 60 <= e.getY() + 60)
                            || (this.getX() + 40 >= e.getX()
                            && this.getX() + 40 <= e.getX() + 40
                            && this.getY() + 60 >= e.getY()
                            && this.getY() + 60 <= e.getY() + 60)) {
                            return true;
                        }
                    }

                    if (e.getDir() == 2 || e.getDir() == 3) {
                        if ((this.getX() >= e.getX() - 10
                                && this.getX() <= e.getX() + 50
                                && this.getY() + 60 >= e.getY() + 10
                                && this.getY() + 60 <= e.getY() + 50)
                                || (this.getX() + 40 >= e.getX() - 10
                                && this.getX() + 40 <= e.getX() + 50
                                && this.getY() + 60 >= e.getY() + 10
                                && this.getY() + 60 <= e.getY() + 50)) {
                            return true;
                        }
                    }
                }
                break;
            case 2:
                for (Enemy e : tanks) {
                    if (e == this) {
                        continue;
                    }
                    if (e.getDir() == 0 || e.getDir() == 1) {
                        if ((this.getX() - 10 >= e.getX()
                            && this.getX() - 10 <= e.getX() + 40
                            && this.getY() + 10 >= e.getY()
                            && this.getY() + 10 <= e.getY() + 60)
                            || (this.getX() - 10 >= e.getX()
                            && this.getX() - 10 <= e.getX() + 40
                            && this.getY() + 50 >= e.getY()
                            && this.getY() + 50 <= e.getY() + 60)) {
                            return true;
                        }
                    }
                    if (e.getDir() == 2 || e.getDir() == 3) {
                        if ((this.getX() - 10 >= e.getX() - 10
                            && this.getX() - 10 <= e.getX() + 50
                            && this.getY() + 10 >= e.getY() + 10
                            && this.getY() + 10 <= e.getY() + 50)
                            || (this.getX() - 10 >= e.getX() - 10
                            && this.getX() - 10 <= e.getX() + 50
                            && this.getY() + 50 >= e.getY() + 10
                            && this.getY() + 50 <= e.getY() + 50)) {
                            return true;
                        }
                    }
                }
                break;
            case 3:
                for (Enemy e : tanks) {
                    if (e == this) {
                        continue;
                    }
                    if (e.getDir() == 0 || e.getDir() == 1) {
                        if ((this.getX() + 50 >= e.getX()
                            && this.getX() + 50 <= e.getX() + 40
                            && this.getY() + 10 >= e.getY()
                            && this.getY() + 10 <= e.getY() + 60)
                            || (this.getX() + 50 >= e.getX()
                            && this.getX() + 50 <= e.getX() + 40
                            && this.getY() + 50 >= e.getY()
                            && this.getY() + 50 <= e.getY() + 60)) {
                            return true;
                        }
                    }
                    if (e.getDir() == 2 || e.getDir() == 3) {
                        if ((this.getX() + 50 >= e.getX() - 10
                            && this.getX() + 50 <= e.getX() + 50
                            && this.getY() + 10 >= e.getY() + 10
                            && this.getY() + 10 <= e.getY() + 50)
                            || (this.getX() + 50 >= e.getX() - 10
                            && this.getX() + 50 <= e.getX() + 50
                            && this.getY() + 50 >= e.getY() + 10
                            && this.getY() + 50 <= e.getY() + 50)) {
                            return true;
                        }
                    }
                }
                break;
        }
    return false;
    }

    public void move() {
        switch (this.getDir()) {
            case 0:
                if (this.getY() > 10 && !this.isOverlap())  {
                    this.setY(this.getY() - this.getSpeed());
                }
                break;
            case 1:
                if (this.getY() < 540 && !this.isOverlap())  {
                    this.setY(this.getY() + this.getSpeed());
                }
                break;
            case 2:
                if (this.getX() > 10 && !this.isOverlap())  {
                    this.setX(this.getX() - this.getSpeed());
                }
                break;
            case 3:
                if (this.getX() < 945 && !this.isOverlap())  {
                    this.setX(this.getX() + this.getSpeed());
                }
                break;
        }
    }
}
