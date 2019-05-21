package WolfSim;

import static java.awt.Color.PINK;

public class Position {
    int x, y;
    MyFrame frame;
    Position[] sheepsPosition;
    Position wolfPosition;

    public Position(MyFrame frame, boolean original) {
        this.frame = frame;

        if (original == true) {
            wolfPosition = new Position(frame, false);
            sheepsPosition = new Position[frame.board.sheepsNumber];
        }
    }

    void generateWolf() {
        wolfPosition.set(((int)(Math.random() * frame.board.n)),((int) (Math.random() * frame.board.m)));
    }


    void generateSheeps() {
        int x_, y_;
        boolean validPosition;

        for (int i = 0; i < frame.board.sheepsNumber; i++) {
            sheepsPosition[i] = new Position(frame, false);
            do {
                validPosition = true;
                x_ = (int)(Math.random() * frame.board.n);
                y_ = (int)(Math.random() * frame.board.m);

                if ( x_ == wolfPosition.x && y_ == wolfPosition.y) {

                    validPosition = false;
                }

                for (int j = 0; j < i; j++) {

                    if ( (sheepsPosition[j].x ==  x_) && (sheepsPosition[j].y == y_) ) {
                        //System.out.println ("OWCA ZRESPILA SIE NA OWCY");
                        validPosition = false; }
                }

            } while (validPosition == false);

            sheepsPosition[i].set(x_, y_);

        }

        for (int i = 0; i < frame.board.sheepsNumber; i++) {
            if ((sheepsPosition[i].x == wolfPosition.x) && (sheepsPosition[i].y == wolfPosition.y))
            {
                System.out.println("OWCA ZRESPILA SIE NA WILKU");
            }
            for (int j = 0; j < i; j++) {
                if ((sheepsPosition[j].x == sheepsPosition[i].x) && (sheepsPosition[j].y == sheepsPosition[i].y))
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
