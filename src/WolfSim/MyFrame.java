package WolfSim;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa odpowiadajaca za stworzenie okna z jego komponentami
 */

public class MyFrame extends JFrame {

    private MyBoard board;
    public Randomize randomize;

    /**
     * Konstruktor klasy MyFrame, odpowiada za ustawienia ramki
     */

    public MyFrame() {
        basicSetup();


        Wolf wolf = new Wolf(this);

        Thread threadBoard = new Thread(board);
        Thread threadWolf = new Thread(wolf);
        threadWolf.start();
        threadBoard.start();
    }

    void basicSetup() {
        var content = getContentPane();
        //content.setLayout(new GridLayout());

        board = new MyBoard(this);
        content.add(board);


        setSize(1080,720);
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
