package Recorder;
import Tank.Enemy;
import Tank.Player;
import java.io.*;
import Map.*;
import java.util.Vector;

public class Recorder {
    private static Vector<Enemy> enemies;
    private static Player player;
    private static String path = "src/Data/data.txt";
    private static FileWriter fileWriter = null;
    private static FileReader fileReader = null;
    private static BufferedReader bufferedReader = null;
    private static BufferedWriter bufferedWriter = null;

    public static String getPath() {
        return path;
    }

    public static void writeToFile(Map map) throws IOException {
        Recorder.player = map.getPlayerTank();
        Recorder.enemies = map.getEnemies();
        Recorder.fileWriter = new FileWriter(Recorder.path);
        bufferedWriter = new BufferedWriter(fileWriter);
        if (Recorder.player.getLives() <= 0 || Recorder.enemies.isEmpty()) {
            bufferedWriter.write("");
            bufferedWriter.close();
            return;
        }
        bufferedWriter.write(map.getLives() + " " + map.getPlayerSpeed() + " " + map.getEnemySpeed());
        bufferedWriter.newLine();
        bufferedWriter.write(player.getX() + " " + player.getY() + " " + player.getDir() + " " + player.getLives());
        bufferedWriter.newLine();
        for (Enemy enemy : enemies) {
            bufferedWriter.write(enemy.getX() + " " + enemy.getY() + " " + enemy.getDir());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static void retrieve(Map map) throws IOException {
        if (!(new File(path).exists())) {
            return;
        }
        Recorder.fileReader = new FileReader(path);
        bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        if (line == null) {
            return;
        }
        String[] data = line.split(" ");
        int lives = Integer.parseInt(data[0]);
        int playerSpeed = Integer.parseInt(data[1]);
        int enemySpeed = Integer.parseInt(data[2]);
        map.setLives(lives);
        map.setPlayerSpeed(playerSpeed);
        map.setEnemySpeed(enemySpeed);
        line = bufferedReader.readLine();
        data = line.split(" ");
        int x = Integer.parseInt(data[0]);
        int y = Integer.parseInt(data[1]);
        int dir = Integer.parseInt(data[2]);
        lives = Integer.parseInt(data[3]);
        Recorder.player = new Player(x, y, dir, 0, map.getPlayerSpeed(), lives);
        Recorder.player.setLives(lives);
        map.setPlayerTank(Recorder.player);
        Recorder.enemies = new Vector<>();
        map.setEnemies(Recorder.enemies);
        while ((line = bufferedReader.readLine()) != null) {
            data = line.split(" ");
            x = Integer.parseInt(data[0]);
            y = Integer.parseInt(data[1]);
            dir = Integer.parseInt(data[2]);
            Enemy enemy = new Enemy(x, y, dir, 1, map.getEnemySpeed());
            Recorder.enemies.add(enemy);
            new Thread(enemy).start();
        }
        Recorder.player.setTanks(Recorder.enemies);
        for (Enemy e : Recorder.enemies) {
            e.setTanks(Recorder.enemies);
        }
        bufferedReader.close();
    }
}
