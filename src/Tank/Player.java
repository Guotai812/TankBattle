package Tank;
import Map.*;
import Weapon.PlayerWeapon;
import java.util.Vector;

public class Player extends Tank{
    private int lives;
    public Player(int x, int y, int dir, int type, int speed, int lives) {
        super(x, y, dir, type, speed);
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public int lifeDown() {
        return --lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void fire(Map map) {
        Vector<PlayerWeapon> playerWeapons = map.getPlayerWeapons();
        if (playerWeapons.size() < map.getWeaponSizeOfPlayer()) {
            playerWeapons.add(new PlayerWeapon(map.getPlayerTank().getX(), map.getPlayerTank().getY(), map.getPlayerTank().getDir()));
        }
        for (PlayerWeapon p : playerWeapons) {
            if (!p.isOnWay()){
                new Thread(p).start();
                p.setOnWay(true);
            }
        }
    }
}
