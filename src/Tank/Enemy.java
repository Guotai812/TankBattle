package Tank;

import Weapon.EnemyWeapon;

public class Enemy extends Tank implements Runnable{
    private EnemyWeapon bullet = null;
    public Enemy(int x, int y, int dir, int type, int speed) {
        super(x, y, dir, type, speed);
    }

    public void setWeapon (EnemyWeapon bullet) {
        this.bullet = bullet;
    }


    public EnemyWeapon getBullet() {
        return this.bullet;
    }

    @Override
    public void run() {
        int count = 0;
        while(true){
            this.move();
//            Let it change direction slowly.
            if (++count == 30) {
                count = 0;
                this.setDir((int) (Math.random() * 4));
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
