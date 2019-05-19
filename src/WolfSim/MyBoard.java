package WolfSim;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static java.awt.Color.*;

/**
 * Klasa odpowiedzialna za glowny panel programu, w ktorym beda wyswietlane mniejsze - "owce" i "wilki"
 */

public class MyBoard extends JPanel implements Runnable {
    int sheepsNumber = 3;       // LICZBA OWIEC
    int n = 10, m = 10;           // WYMIARY PLANSZY

    JPanel[][] panelArray = new JPanel[n][m];
    Position[] sheepsPosition = new Position[sheepsNumber];
    Position wolfPosition = new Position();

    private MyFrame frame;


    /**
     * Konstruktor klasy MyBoard. Ustala podstawowe funkcje panelu.
     * @param frame - glowna ramka programu
     */

    public MyBoard(MyFrame frame) {

        this.frame = frame;
        setLayout(new GridLayout(n,m));


        generateBackground();
        generateWolf();
        generateSheeps();
        refreshBoard();



    }

    @Override
    public synchronized void run() {
        try {

            System.out.println("Przed waitem");
            //this.wait();
            System.out.println("Po waicie");
            Thread.sleep(3000);
            System.out.println("POZYCJA WILKA: " + wolfPosition.x + " " + wolfPosition.y);
            clearBackground();
            System.out.println("WYCZYSZCZONO TLO");
            //System.out.println("POZYCJA WILKA: " + wolfPosition.x + " " + wolfPosition.y);
            refreshBoard();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


    void generateWolf() {
        wolfPosition.set(((int)(Math.random() * n)),((int) (Math.random() * m)));

    }

    void generateSheeps() {
        int x, y;
        boolean validPosition;

        for (int i = 0; i < sheepsNumber; i++) {
            sheepsPosition[i] = new Position();
            do {
                validPosition = true;
                x = (int)(Math.random() * n);
                y = (int)(Math.random() * m);

                if ( x == wolfPosition.getX() && y == wolfPosition.getY()) {
                    //System.out.println ("OWCA ZRESPILA SIE NA WILKU");
                    validPosition = false;
                }

                for (int j = 0; j < i; j++) {
                   // if ( (sheepsPosition[j].getX() == x) && (sheepsPosition[j].getY() == y) ) { validPosition = false; }
                    if ( (sheepsPosition[j].getX() ==  x) && (sheepsPosition[j].getY() == y) ) {
                        //System.out.println ("OWCA ZRESPILA SIE NA OWCY");
                        validPosition = false; }
                }

            } while (validPosition == false);

            sheepsPosition[i].set(x, y);

            }

        for (int i = 0; i < sheepsNumber; i++) {
            if ((sheepsPosition[i].getX() == wolfPosition.getX()) && (sheepsPosition[i].getY() == wolfPosition.getY()))
            {
                System.out.println("OWCA ZRESPILA SIE NA WILKU");
            }
            for (int j = 0; j < i; j++) {
                if ((sheepsPosition[j].getX() == sheepsPosition[i].getX()) && (sheepsPosition[j].getY() == sheepsPosition[i].getY()))
                {
                    System.out.println("OWCA ZRESPILA SIE NA WILKU");
                }
            }
            panelArray[sheepsPosition[i].getX()][sheepsPosition[i].getY()].setBackground(PINK);
        }

    }

    /** Dodawanie wszystkich pol do panelu*/
    void refreshBoard() {
        panelArray[wolfPosition.getX()][wolfPosition.getY()].setBackground(RED);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                add(panelArray[i][j]);
            }
        }
    }



}
