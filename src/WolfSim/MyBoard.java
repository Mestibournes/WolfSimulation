package WolfSim;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.*;



/**
 * Klasa odpowiedzialna za glowny panel programu, w ktorym beda wyswietlane mniejsze - "owce" i "wilki"
 */

public class MyBoard extends JPanel {
    int sheepsNumber = 60;       // LICZBA OWIEC
    int n = 15, m = 15;           // WYMIARY PLANSZY
    int k = 200;                          // CZAS TURY W MILISEKUNDACH


    JButton[][] panelArray = new JButton[n][m];


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

    public void setMyBounds(int n, int m){

        this.n = n;
        this.m = m;
    }


    void generateBackground() {
        for (int i = 0; i < n; i++) {

            for (int j= 0; j < m; j++) {
                panelArray[i][j] = new JButton();
                panelArray[i][j].setBackground(WHITE);

            }
        }

        /*for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                panelArray[i][j] = new JButton();
                panelArray[i][j].setBackground(WHITE);
                panelArray[i][j].setBorder(border);

            }

        }*/
    }

    void clearBackground() {

        for (int i = 0; i < n; i++) {

            for (int j= 0; j < m; j++) {
                panelArray[i][j].setBackground(WHITE);
            }
        }
    }





    /** Dodawanie wszystkich pol do panelu*/
    void refreshBoard() {
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
        for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
            panelArray[frame.position.sheepsPosition.get(i).x][frame.position.sheepsPosition.get(i).y].setBackground(PINK);
        }
    }



}
