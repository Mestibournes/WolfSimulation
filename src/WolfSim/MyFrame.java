package WolfSim;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadajaca za stworzenie okna z jego komponentami
 */

public class MyFrame extends JFrame {

    public MyBoard board;
    public Position position;
    public Randomize randomize;

    /**
     * Konstruktor klasy MyFrame, odpowiada za ustawienia ramki
     */

    public MyFrame() {
        basicSetup();
        position.generateWolf();
        position.generateSheeps();
        board.refreshBoard();

        Wolf wolf = new Wolf(this);
        Thread wolfThread = new Thread(wolf);
        wolfThread.start();

        Sheeps sheeps = new Sheeps(this);
        Thread sheepsThread = new Thread(sheeps);
        sheepsThread.start();




      }

    void basicSetup() {
        var content = getContentPane();
        //content.setLayout(new GridLayout());

        board = new MyBoard(this);
        position = new Position(this, true);
        content.add(board);


        setSize(800,800);
        setResizable(true);
        //pack();
        setLocationRelativeTo(null); // wysrodkowuje okno na ekranie
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    MyBoard getMyBoard() {
        return board;
    }

}
