package WolfSim;

import java.util.ArrayList;

import static java.awt.Color.*;

public class Wolf implements Runnable {
    ArrayList<Integer> nearbySheeps = new ArrayList<>();
    int chasedIndex;
    int it;
    Position previousPosition;
    private MyFrame frame;
    ArrayList<Integer> distance = new ArrayList<>();

    public Wolf(MyFrame frame) {
        this.frame = frame;
        previousPosition = new Position(frame, false);

    }

    @Override
    public void run() {

        synchronized (frame.position) {
            try {
                Thread.sleep(1000);

                do {
                    Thread.sleep(50);
                    nearbySheeps = new ArrayList<>();
                    it = 0;
                    previousPosition.set(frame.position.wolfPosition.x, frame.position.wolfPosition.y);
                    /*do {
                            for(int i = 0; i < frame.position.sheepsPosition.size(); i++) {
                            //if ((frame.position.wolfPosition.x - it == frame.position.sheepsPosition.get(i).x) || (frame.position.wolfPosition.x + it == frame.position.sheepsPosition.get(i).x) || (frame.position.wolfPosition.y - it == frame.position.sheepsPosition.get(i).y) || (frame.position.wolfPosition.y + it == frame.position.sheepsPosition.get(i).y)) {
                            if ( (Math.abs(frame.position.wolfPosition.x - frame.position.sheepsPosition.get(i).x) <= it) && (Math.abs(frame.position.wolfPosition.y - frame.position.sheepsPosition.get(i).y) <= it )) {
                                  nearbySheeps.add(i);
                            }

                        }
                        it++;
                    } while (nearbySheeps.size() == 0);

                    if (nearbySheeps.size() == 1) {
                        //chasedIndex = nearbySheeps.get(0);
                    } else {
                        System.out.println("ZA DUZO OWCOW");
                        chasedIndex = (int) (Math.random() * nearbySheeps.size());
                    }*/

                    searchForSheep();
                    chase();
                    for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
                        if ((frame.position.wolfPosition.x == frame.position.sheepsPosition.get(i).x) && (frame.position.wolfPosition.y == frame.position.sheepsPosition.get(i).y)) {
                            frame.position.sheepsPosition.remove(i);
                        }
                    }

                    frame.board.panelArray[this.previousPosition.x][this.previousPosition.y].setBackground(WHITE);
                    frame.board.panelArray[frame.position.wolfPosition.x][frame.position.wolfPosition.y].setBackground(RED);


                    frame.position.notifyAll();
                    frame.position.wait();

                } while (true);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void searchForSheep() {
        distance = new ArrayList<>();
        for (int i = 0; i < frame.position.sheepsPosition.size(); i++) {
            distance.add(Math.max(Math.abs(frame.position.wolfPosition.x - frame.position.sheepsPosition.get(i).x), Math.abs(frame.position.wolfPosition.y - frame.position.sheepsPosition.get(i).y)));
        }
        nearbySheeps = new ArrayList<>();
        int shortestDistance = distance.get(0);
        nearbySheeps.add(0);

        for (int i = 1; i < frame.position.sheepsPosition.size(); i++) {
            if (shortestDistance > distance.get(i)) {
                shortestDistance = distance.get(i);
                nearbySheeps = new ArrayList<>();
                nearbySheeps.add(i);
            }
            else if(shortestDistance == distance.get(i)) {
                nearbySheeps.add(i);
            }
        }
        if (nearbySheeps.size() == 0) {
            System.out.println("O KURWA MAC TAK NIE MOZE BYC");
        }

        if (nearbySheeps.size() == 1) {
           chasedIndex = nearbySheeps.get(0);
        }
        else {
            int randomSheep =  (int) (Math.random() * nearbySheeps.size());
            chasedIndex = nearbySheeps.get(randomSheep);
            System.out.println("DUZO OWCOW");
        }
    }



    void chase() {

            if (frame.position.wolfPosition.y > frame.position.sheepsPosition.get(chasedIndex).y) { frame.position.wolfPosition.y--; }
            else if (frame.position.wolfPosition.y < frame.position.sheepsPosition.get(chasedIndex).y){ frame.position.wolfPosition.y++; }

            if (frame.position.wolfPosition.x > frame.position.sheepsPosition.get(chasedIndex).x) { frame.position.wolfPosition.x--; }
            else if (frame.position.wolfPosition.x < frame.position.sheepsPosition.get(chasedIndex).x) { frame.position.wolfPosition.x++; }



    }
}

