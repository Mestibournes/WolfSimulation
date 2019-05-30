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

      void myMessageBox() {
        int x = 0;
        int y = 0;

        JTextField xField = new JTextField(5);
          JTextField yField = new JTextField(5);


          JPanel myPanel = new JPanel();
          myPanel.add(new JLabel("x:"));
          myPanel.add(xField);
          myPanel.add(Box.createHorizontalStrut(15)); // a spacer
          myPanel.add(new JLabel("y:"));
          myPanel.add(yField);

          int result = JOptionPane.showConfirmDialog(null, myPanel,
                  "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
          if (result == JOptionPane.OK_OPTION) {

           /*   try{
                  x = Integer.parseInt(xField.getText());
                  y = Integer.parseInt(yField.getText());

              }catch (NumberFormatException e) {

                  System.out.println("Bledne dane");
              }

*/


              System.out.println("x value: " + getMyBoard().n );
              System.out.println("y value: " + getMyBoard().m);

          }



      }

    void basicSetup() {



        var content = getContentPane();
        //content.setLayout(new GridLayout());

        board = new MyBoard(this);
        position = new Position(this, true);
        content.add(board);
        myMessageBox();


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
