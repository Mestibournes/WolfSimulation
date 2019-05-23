package WolfSim;

import java.util.ArrayList;

import static java.awt.Color.PINK;
import static java.awt.Color.WHITE;

public class Sheeps implements Runnable {
    ArrayList<Position> illegalPositions = new ArrayList<>();
    boolean doubledPosition = false;
    ArrayList<Position> previousPosition = new ArrayList<>();
    private MyFrame frame;
    int deltaX, deltaY;
    public Sheeps(MyFrame frame) {
        this.frame = frame;
    }


    public void run() {
        synchronized (frame.position) {

            try {
                for (; ; ) {
                    setIllegalPositions();

                    for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
                        previousPosition.add(new Position(frame, false));
                        previousPosition.get(i).set(frame.position.sheepsPosition.get(i).x, frame.position.sheepsPosition.get(i).y);
                    }


                    runAway();


                    Thread.sleep(500);

                    //frame.board.clearBackground();
                    refreshSheeps();
                    frame.board.refreshBoard();


                    frame.position.notifyAll();
                    //System.out.println("TURA WILKA");
                    frame.position.wait();

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    void refreshSheeps() {
        for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
            frame.board.panelArray[this.previousPosition.get(i).x][this.previousPosition.get(i).y].setBackground(WHITE);
            frame.board.panelArray[frame.position.sheepsPosition.get(i).x][frame.position.sheepsPosition.get(i).y].setBackground(PINK);
        }
    }

    void setIllegalPositions() {

        illegalPositions.add(new Position(frame, false));
        illegalPositions.get(0).set(frame.position.wolfPosition.x, frame.position.wolfPosition.y);

        for (int i = 1; i < frame.position.sheepsPosition.size(); i++) {
            illegalPositions.add(new Position(frame, false));
            illegalPositions.get(i).set(frame.position.sheepsPosition.get(i).x, frame.position.sheepsPosition.get(i).y);
        }
    }

    boolean isXTaken(Position sheep, int x_, int y_) {
        for (int i = 0; i < illegalPositions.size(); i++) {
            if (((sheep.x + x_) == illegalPositions.get(i).x) && ((sheep.y + y_) == illegalPositions.get(i).y)) {
                return false;
            }
        }
        return true;
    }


    void runAway() {

        for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
            // @ @ @ @ @ @ @
            boolean isTaken = false;
            deltaX = 0;
            deltaY = 0;

            /** WARUNKI GDY OWCA JEST W JEDNYM Z CZTERECH ROGOW */
            if( (frame.position.sheepsPosition.get(i).y == 0) && (frame.position.sheepsPosition.get(i).x == 0) )
            {
                System.out.println("OWCA JEST W LEWYM GORNYM ROGU");
                randomMove(true, false);
                frame.position.sheepsPosition.get(i).x += deltaX;
                frame.position.sheepsPosition.get(i).y += deltaY;
            }
            else if ( (frame.position.sheepsPosition.get(i).y == 0) && (frame.position.sheepsPosition.get(i).x == frame.board.n - 1) ) {
                System.out.println("OWCA JEST W LEWYM DOLNYM ROGU");
                randomMove(true, true);
                frame.position.sheepsPosition.get(i).x -= deltaX;
                frame.position.sheepsPosition.get(i).y += deltaY;
            }
            else if ( (frame.position.sheepsPosition.get(i).y == frame.board.m - 1) && (frame.position.sheepsPosition.get(i).x == 0) ) {
                System.out.println("OWCA JEST W PRAWYM GORNYM ROGU");
                randomMove(true, false);
                frame.position.sheepsPosition.get(i).x += deltaX;
                frame.position.sheepsPosition.get(i).y -= deltaY;
            }
            else if ( (frame.position.sheepsPosition.get(i).y == frame.board.m - 1) && (frame.position.sheepsPosition.get(i).x == frame.board.n - 1) ) {
                System.out.println("OWCA JEST W PRAWYM DOLNYM ROGU");
                randomMove(true, true);
                frame.position.sheepsPosition.get(i).x -= deltaX;
                frame.position.sheepsPosition.get(i).y -= deltaY;
            }

            /** /WARUNKI GDY OWCA JEST PRZY SCIANIE */
            else if ((frame.position.sheepsPosition.get(i).y == 0)) {
                // LEWA KRAWEDZ
                randomMove(false, true);
                frame.position.sheepsPosition.get(i).x += deltaX;
                frame.position.sheepsPosition.get(i).y += deltaY;

            }
            else if(frame.position.sheepsPosition.get(i).y == frame.board.m - 1) {
                // PRAWA KRAWEDZ
                randomMove(false, true);
                frame.position.sheepsPosition.get(i).x += deltaX;
                frame.position.sheepsPosition.get(i).y -= deltaY;
            }
            else if(frame.position.sheepsPosition.get(i).x == 0) {
                // GORNA KRAWEZ
                randomMove(false, false);
                frame.position.sheepsPosition.get(i).x += deltaX;
                frame.position.sheepsPosition.get(i).y += deltaY;
            }
            else if(frame.position.sheepsPosition.get(i).x == frame.board.n - 1) {
                // DOLNA KRAWEDZ
                randomMove(false, false);
                frame.position.sheepsPosition.get(i).x -= deltaX;
                frame.position.sheepsPosition.get(i).y += deltaY;
            }


            else {

                if ((frame.position.wolfPosition.y > frame.position.sheepsPosition.get(i).y) && (frame.position.sheepsPosition.get(i).y != 0)) {

                    frame.position.sheepsPosition.get(i).y--;
                    deltaY--;

                } else if ((frame.position.wolfPosition.y < frame.position.sheepsPosition.get(i).y) && (frame.position.sheepsPosition.get(i).y != frame.board.m - 1)) {

                    frame.position.sheepsPosition.get(i).y++;
                    deltaY++;
                }


                if ((frame.position.wolfPosition.x > frame.position.sheepsPosition.get(i).x) && (frame.position.sheepsPosition.get(i).x != 0)) {
                    frame.position.sheepsPosition.get(i).x--;
                    deltaX--;
                } else if ((frame.position.wolfPosition.x < frame.position.sheepsPosition.get(i).x) && (frame.position.sheepsPosition.get(i).x != frame.board.n - 1)) {
                    frame.position.sheepsPosition.get(i).x++;
                    deltaX++;
                }
            }




                for (int j = 0; j < frame.position.sheepsPosition.size(); j++) {
                if ( (frame.position.sheepsPosition.get(i).x == frame.position.sheepsPosition.get(j).x) && (frame.position.sheepsPosition.get(i).y == frame.position.sheepsPosition.get(j).y) && (i!=j) ) {
                    isTaken = true;
                }
            }

            if (isTaken == true) {
                frame.position.sheepsPosition.get(i).x -= deltaX;
                frame.position.sheepsPosition.get(i).y -= deltaY;
            }

            // @ @ @ @ @ @ @
        }

    }

    void randomMove(boolean corner, boolean canXBeNegative) {
        if (corner == true) {
            do {
                deltaX = Math.abs((int) (Math.random() * 2));
                deltaY = Math.abs((int) (Math.random() * 2));
            } while (deltaX == 0 && deltaY == 0);
        }
        else if (canXBeNegative == true)
        {
            do {
                deltaX = Math.abs((int) (Math.random() * 3)) - 1;
                deltaY = Math.abs((int) (Math.random() * 2));
            } while (deltaX == 0 && deltaY == 0);
        }
        else if (canXBeNegative == false)
        {
            do {
                deltaX = Math.abs((int) (Math.random() * 2));
                deltaY = Math.abs((int) (Math.random() * 3)) - 1;
            } while (deltaX == 0 && deltaY == 0);
        }

    }


}
