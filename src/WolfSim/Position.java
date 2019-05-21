package WolfSim;

import java.util.ArrayList;

import static java.awt.Color.PINK;

public class Position {
    int x, y;
    MyFrame frame;
    ArrayList<Position>  sheepsPosition;
    Position wolfPosition;

    public Position(MyFrame frame, boolean original) {
        this.frame = frame;

        if (original == true) {
            wolfPosition = new Position(frame, false);
            sheepsPosition =  new ArrayList<>();
        }
    }

    void generateWolf() {
        wolfPosition.set(((int)(Math.random() * frame.board.n)),((int) (Math.random() * frame.board.m)));
    }


    void generateSheeps() {
        int x_, y_;
        boolean validPosition;

        for (int i = 0; i < frame.board.sheepsNumber; i++) {
            sheepsPosition.add(new Position(frame, false));
            do {
                validPosition = true;
                x_ = (int)(Math.random() * frame.board.n);
                y_ = (int)(Math.random() * frame.board.m);

                if ( x_ == wolfPosition.x && y_ == wolfPosition.y) {

                    validPosition = false;
                }

                for (int j = 0; j < i; j++) {

                    if ( (sheepsPosition.get(j).x ==  x_) && (sheepsPosition.get(j).y == y_) ) {
                        //System.out.println ("OWCA ZRESPILA SIE NA OWCY");
                        validPosition = false; }
                }

            } while (validPosition == false);

            sheepsPosition.get(i).set(x_, y_);

        }

        for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
            if ((sheepsPosition.get(i).x == wolfPosition.x) && (sheepsPosition.get(i).y == wolfPosition.y))
            {
                System.out.println("OWCA ZRESPILA SIE NA WILKU");
            }
            for (int j = 0; j < i; j++) {
                if ((sheepsPosition.get(j).x == sheepsPosition.get(i).x) && (sheepsPosition.get(j).y == sheepsPosition.get(i).y))
                {
                    System.out.println("OWCA ZRESPILA SIE NA WILKU");
                }
            }
            //panelArray[sheepsPosition[i].getX()][sheepsPosition[i].getY()].setBackground(PINK);
        }

    }



    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
