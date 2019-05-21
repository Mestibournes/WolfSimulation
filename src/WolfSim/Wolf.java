package WolfSim;

import java.util.ArrayList;

public class Wolf implements Runnable {
    ArrayList<Integer> nearbySheeps = new ArrayList<>();
    int chasedIndex;
    int it;
    private MyFrame frame;

    public Wolf(MyFrame frame) {
        this.frame = frame;

    }

    @Override
    public void run() {

        synchronized (frame.position) {
            try {
                Thread.sleep(1000);

                do {
                    nearbySheeps = new ArrayList<>();
                    it = 0;
                    do {
                            for(int i = 0; i < frame.position.sheepsPosition.size(); i++) {
                            //if ((frame.position.wolfPosition.x - it == frame.position.sheepsPosition.get(i).x) || (frame.position.wolfPosition.x + it == frame.position.sheepsPosition.get(i).x) || (frame.position.wolfPosition.y - it == frame.position.sheepsPosition.get(i).y) || (frame.position.wolfPosition.y + it == frame.position.sheepsPosition.get(i).y)) {
                            if ( (Math.abs(frame.position.wolfPosition.x - frame.position.sheepsPosition.get(i).x) <= it) && (Math.abs(frame.position.wolfPosition.y - frame.position.sheepsPosition.get(i).y) <= it )) {
                                  nearbySheeps.add(i);
                            }

                        }
                        it++;
                    } while (nearbySheeps.size() == 0);

                    if (nearbySheeps.size() == 1) {
                        chasedIndex = nearbySheeps.get(0);
                    } else {
                        System.out.println("ZA DUZO OWCOW");
                        chasedIndex = (int) (Math.random() * nearbySheeps.size());
                    }

                    chase();
                    if( (frame.position.wolfPosition.x == frame.position.sheepsPosition.get(chasedIndex).x) && (frame.position.wolfPosition.y == frame.position.sheepsPosition.get(chasedIndex).y) ) {
                        //frame.position.sheepsPosition.get(chasedIndex).x = 0;
                        //frame.position.sheepsPosition.get(chasedIndex).x = 0;
                        frame.position.sheepsPosition.remove(chasedIndex);

                    }
                    frame.board.clearBackground();
                    frame.board.refreshBoard();
                    Thread.sleep(200);

                } while (true);

                /*
                while (frame.position.wolfPosition.x < frame.board.n - 1) {
                    frame.position.wolfPosition.x++;
                    frame.board.clearBackground();
                    frame.board.refreshBoard();
                    Thread.sleep(500);
                }

                frame.position.wait();
                while (frame.position.wolfPosition.y < frame.board.m - 1) {
                    frame.position.wolfPosition.y++;
                    frame.board.clearBackground();
                    frame.board.refreshBoard();
                    Thread.sleep(500);
                }*/


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void chase() {

            if (frame.position.wolfPosition.y > frame.position.sheepsPosition.get(chasedIndex).y) { frame.position.wolfPosition.y--; }
            else if (frame.position.wolfPosition.y < frame.position.sheepsPosition.get(chasedIndex).y){ frame.position.wolfPosition.y++; }

            if (frame.position.wolfPosition.x > frame.position.sheepsPosition.get(chasedIndex).x) { frame.position.wolfPosition.x--; }
            else if (frame.position.wolfPosition.x < frame.position.sheepsPosition.get(chasedIndex).x) { frame.position.wolfPosition.x++; }



    }
}

