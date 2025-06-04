package Weapon;

public class EnemyWeapon extends Weapon{
    public EnemyWeapon(int x, int y, int dir) {
        super(x, y, dir);
        this.setSpeed(3);
    }
}
