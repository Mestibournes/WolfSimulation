package WolfSim;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.*;

/**
 * Klasa odpowiedzialna za glowny panel programu, w ktorym beda wyswietlane mniejsze - "owce" i "wilki"
 */

public class MyBoard extends JPanel {
    int sheepsNumber = 3;       // LICZBA OWIEC
    int n = 10, m = 10;           // WYMIARY PLANSZY

    JPanel[][] panelArray = new JPanel[n][m];


    private MyFrame frame;


    /**
     * Konstruktor klasy MyBoard. Ustala podstawowe funkcje panelu.
     * @param frame - glowna ramka programu
     */

    public MyBoard(MyFrame frame) {

        this.frame = frame;
        setLayout(new GridLayout(n,m));


        generateBackground();




    }


    void generateBackground() {
        for (int i = 0; i < n; i++) {

            for (int j= 0; j < m; j++) {
                panelArray[i][j] = new JPanel();
                if ((i + j) % 2 == 0) { panelArray[i][j].setBackground(WHITE); }
                else { panelArray[i][j].setBackground(GRAY); }
            }
        }
    }

    void clearBackground() {

        for (int i = 0; i < n; i++) {

            for (int j= 0; j < m; j++) {
                if ((i + j) % 2 == 0) { panelArray[i][j].setBackground(WHITE); }
                else { panelArray[i][j].setBackground(GRAY); }
            }
        }
    }





    /** Dodawanie wszystkich pol do panelu*/
    void refreshBoard() {
        System.out.println("ODSWIEZONO");
        drawWolf();
        drawSheeps();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                add(panelArray[i][j]);
            }
        }
    }

    void drawWolf() {
        panelArray[frame.position.wolfPosition.x][frame.position.wolfPosition.y].setBackground(RED);
    }

    void drawSheeps() {
        for (int i = 0; i < sheepsNumber; i++) {
            panelArray[frame.position.sheepsPosition[i].x][frame.position.sheepsPosition[i].y].setBackground(PINK);
        }
    }



}
