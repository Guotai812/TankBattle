package Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;
import Explosion.Explosion;
import Weapon.*;
import Tank.*;
import Weapon.Weapon;

public class Map extends JPanel implements KeyListener, Runnable {
    private int lives;
    private int playerSpeed;
    private Player playerTank;
    private int WeaponSizeOfPlayer = 2;
    private Vector<PlayerWeapon> playerWeapons = new Vector<>(WeaponSizeOfPlayer);
    private int enemySize;
    private int enemySpeed;
    private Vector<Enemy> enemies;
    private Vector<EnemyWeapon> enemyWeapons = new Vector<>();
    private Vector<Explosion> explosions = new Vector<>();

    public Map(int choice, int difficulty) {
        if (choice == 1) {
            switch (difficulty) {
                case 1:
                    this.enemySize = 3;
                    this.enemySpeed = 1;
                    this.playerSpeed = 15;
                    this.lives = 3;
                    break;
                case 2:
                    this.enemySize = 6;
                    this.enemySpeed = 2;
                    this.playerSpeed = 5;
                    this.lives = 2;
                    break;
            }
        }
        this.playerTank = new Player(400, 200, 0, 0, this.playerSpeed, this.lives);
        enemies = new Vector<>();
        if (choice == 1) {
            playerTank.setLives(this.lives);

            this.enemies = new Vector<>();
            for (int i = 0 ; i < enemySize ; i++) {
                Enemy enemy = new Enemy(((100 * i) + 300), 0, 1, 1, enemySpeed);
                enemies.add(enemy);
                new Thread(enemy).start();
            }

            this.playerTank.setTanks(enemies);
            for (Enemy e : enemies) {
                e.setTanks(enemies);
            }
        }
    }

    public Vector<PlayerWeapon> getPlayerWeapons() {
        return playerWeapons;
    }

    public Player getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Player playerTank) {
        this.playerTank = playerTank;
    }

    public int getWeaponSizeOfPlayer() {
        return WeaponSizeOfPlayer;
    }

    public Vector<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Vector<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getEnemySpeed() {
        return enemySpeed;
    }

    public int getLives() {
        return lives;
    }

    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setEnemySize(int enemySize) {
        this.enemySize = enemySize;
    }

    public void setEnemySpeed(int enemySpeed) {
        this.enemySpeed = enemySpeed;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    public void paint(Graphics g) {
        super.paint(g);
        showInfo(g);
        if (enemies.isEmpty()) {
            this.win(g);
            return;
        }

        if (this.lives == 0) {
            this.over(g);
            return;
        }

//        Draw the background of the map
        g.fillRect(0, 0, 1000, 600);

//        Draw the player
        this.drawTank(this.playerTank, g);

//        Draw the enemy's tanks;
        for (Enemy e : this.enemies) {
            this.drawTank(e, g);

        }

//        Draw all the bullets of player
        g.setColor(Color.CYAN);
        for (PlayerWeapon e : this.playerWeapons) {
            g.fillOval(e.getX(), e.getY(), 3, 3);
        }

//        Draw all the bullets of enemies
        g.setColor(Color.YELLOW);
        for (EnemyWeapon e : enemyWeapons) {
            g.fillOval(e.getX(), e.getY(), 3, 3);
        }

//        Draw the explosion of tank
        Iterator<Explosion> it = this.explosions.iterator();
        while (it.hasNext()) {
            Explosion e = it.next();
            Image image = e.explode();
            g.drawImage(image, e.getX(), e.getY(), e.getRadius(), e.getRadius(), null);
            if (image == null) {
                it.remove();
            }
        }
    }

//    method to draw tank
    private void drawTank(Tank tank, Graphics g) {
//        Set the color of tank
        switch (tank.getType()) {
            case 0:
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.YELLOW);
                break;
        }

//        Draw all the direction of a tank
        switch (tank.getDir()){
            case 0:
                g.fill3DRect(tank.getX(), tank.getY(), 10,60, false);
                g.fill3DRect(tank.getX() + 30, tank.getY(), 10,60, false);
                g.fill3DRect(tank.getX() + 10,tank.getY() + 10, 20, 40,false);
                g.fillOval(tank.getX() + 10, tank.getY() + 20, 20, 20);
                g.drawLine(tank.getX() + 20,tank.getY() + 30, tank.getX() + 20, tank.getY());
                break;
            case 1:
                g.fill3DRect(tank.getX(), tank.getY(), 10,60, false);
                g.fill3DRect(tank.getX() + 30, tank.getY(), 10,60, false);
                g.fill3DRect(tank.getX() + 10,tank.getY() + 10, 20, 40,false);
                g.fillOval(tank.getX() + 10, tank.getY() + 20, 20, 20);
                g.drawLine(tank.getX() + 20,tank.getY() + 30, tank.getX() + 20, tank.getY() + 60);
                break;
            case 2:
                g.fill3DRect(tank.getX() - 10, tank.getY() + 10, 60,10, false);
                g.fill3DRect(tank.getX() - 10, tank.getY() + 40, 60,10, false);
                g.fill3DRect(tank.getX(), tank.getY() + 20, 40,20, false);
                g.fillOval(tank.getX() + 10, tank.getY() + 20, 20, 20);
                g.drawLine(tank.getX() + 20, tank.getY() + 30, tank.getX() - 10, tank.getY() + 30);
                break;
            case 3:
                g.fill3DRect(tank.getX() - 10, tank.getY() + 10, 60,10, false);
                g.fill3DRect(tank.getX() - 10, tank.getY() + 40, 60,10, false);
                g.fill3DRect(tank.getX(), tank.getY() + 20, 40,20, false);
                g.fillOval(tank.getX() + 10, tank.getY() + 20, 20, 20);
                g.drawLine(tank.getX() + 20, tank.getY() + 30, tank.getX() + 50, tank.getY() + 30);
                break;
        }
    }

    private void win(Graphics g) {
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 100));
        g.drawString("WIN", 330, 300);
    }

    private void over(Graphics g) {
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 100));
        g.drawString("OVER", 350, 300);
    }

    private void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font arial = new Font("Arial", Font.BOLD, 25);
        g.drawString("REMAINING LIVES", 10, 620);
        g.drawString(playerTank.getLives() + "", 55, 640);
        drawTank(new Player(40, 650, 0, 0, this.playerSpeed, this.lives), g);
        g.setColor(Color.BLACK);
        g.drawString("REMAINING ENEMIES", 200, 620);
        g.drawString(enemies.size() + "", 265, 640);
        drawTank(new Player(250, 650, 0, 1, this.playerSpeed, this.lives), g);
        g.setColor(Color.BLACK);
    }

//    Add event handler to achieve moving and firing
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerTank.setDir(0);
            playerTank.move();
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            playerTank.setDir(1);
            playerTank.move();
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerTank.setDir(2);
            playerTank.move();
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playerTank.setDir(3);
            playerTank.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            playerTank.fire(this);
        }
    }

//    Method to check if bullet has hit the edge
    private boolean hitTheEdge(Weapon weapon) {
        if (weapon.getX() < 0 || weapon.getX() > 1000 || weapon.getY() < 0 || weapon.getY() > 600) {
            return true;
        }
        return false;
    }

//    Method to check if bullet has hit tank
    private boolean hitTank(Tank tank, Weapon bullet) {
        switch (tank.getDir()) {
            case 0, 1:
                if (bullet.getX() > tank.getX()
                        && bullet.getX() < tank.getX() + 40
                        && bullet.getY() > tank.getY()
                        && bullet.getY() < tank.getY() + 60) {
                     return true;
                }
                break;
            case 2, 3:
                if (bullet.getX() > tank.getX()
                        && bullet.getX() < tank.getX() + 60
                        && bullet.getY() > tank.getY() + 10
                        && bullet.getY() < tank.getY() + 50) {
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
//            Check if player's bullet hit the edge of map
            playerWeapons.removeIf(this::hitTheEdge);

//            Check if bullets have hit enemy's tank
            Iterator<Enemy> enemyIterator = this.enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy e = enemyIterator.next();  // Get the enemy once per outer loop iteration
                Iterator<PlayerWeapon> weaponIterator = playerWeapons.iterator();
                while (weaponIterator.hasNext()) {
                    PlayerWeapon p = weaponIterator.next();
                    if (hitTank(e, p)) {
                        enemyIterator.remove();    // Remove this enemy
                        weaponIterator.remove();   // Remove the weapon
                        explosions.add(new Explosion(e.getX(), e.getY(), 60));
                    }
                }
            }

//            Check if enemy's tank if is fired
            for (Enemy e : enemies) {
                if (e.getBullet() == null) {
                    EnemyWeapon bullet = new EnemyWeapon(e.getX(), e.getY() ,e.getDir());
                    e.setWeapon(bullet);
                    enemyWeapons.add(bullet);
                    new Thread(bullet).start();
                }
            }

//            Check if enemy's bullet has hit the edge
            Iterator<EnemyWeapon> it2 = enemyWeapons.iterator();
            while (it2.hasNext()) {
                EnemyWeapon e = it2.next();
                int code = e.hashCode();
                if (hitTheEdge(e)) {
                    it2.remove();
                    for (Enemy t : enemies) {
                        if (t.getBullet() == null) {
                            continue;
                        }
                        if (t.getBullet().hashCode() == code) {
                            t.setWeapon(null);
                        }
                    }
                }
            }

//            Check if bullet has hit player
            Iterator<EnemyWeapon> it3 = enemyWeapons.iterator();
            while (it3.hasNext()) {
                EnemyWeapon e = it3.next();
                int code = e.hashCode();
                if (hitTank(playerTank, e)) {
                    this.lives = playerTank.lifeDown();
                    Explosion explosion = new Explosion(playerTank.getX(), playerTank.getY(), 60);
                    explosions.add(explosion);
                    if (this.lives > 0){
                        playerTank = new Player(400, 200, 0, 0,this.playerSpeed, this.lives);
                        playerTank.setTanks(enemies);
                    }else {
                        repaint();
                        return;
                    }
                    for (Enemy t : enemies) {
                        if (t.getBullet() == null) {
                            continue;
                        }
                        if (t.getBullet().hashCode() == code) {
                            t.setWeapon(null);
                        }
                    }
                    it3.remove();
                }
            }

            repaint();

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
