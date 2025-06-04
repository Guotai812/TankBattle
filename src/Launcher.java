import Map.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import Recorder.*;

@SuppressWarnings("all")
public class Launcher extends JFrame {
    private Map map;

    public static void main(String[] args) throws IOException {
        Launcher launcher = new Launcher();
    }

    private Launcher() throws IOException {
        System.out.println("1: NEW GAME" + "\t\t" + "2: RESEME");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if (choice == 2) {
            if (!(new File(Recorder.getPath()).exists() && new FileReader(Recorder.getPath()).read() != -1)) {
                choice = 1;
                System.out.println("CAN'T FIND ANY RECORD, START NEW GAME");
            }
        }
        int difficulty = 0;
        if (choice == 1) {
            System.out.println("DIFFICULTY:");
            System.out.println("1: EASY");
            System.out.println("2: HARD");
            difficulty = scanner.nextInt();
        }
        this.map = new Map(choice,difficulty);
        if (choice == 2) {
            Recorder.retrieve(map);
        }
        this.add(map);
        new Thread(map).start();
        this.setSize(1000, 750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Map01");
        this.addKeyListener(map);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    Recorder.writeToFile(map);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
