package WolfSim;

public class Sheeps implements Runnable {
    private MyFrame frame;

    public Sheeps(MyFrame frame) {
        this.frame = frame;
    }


    public void run() {
        synchronized (frame.position) {

            try {
                /*for (int i = 0; i < frame.board.sheepsNumber; i++) {
                    while (frame.position.sheepsPosition[i].x < frame.board.n - 1) {
                        frame.position.sheepsPosition[i].x++;
                        frame.board.clearBackground();
                        frame.board.refreshBoard();
                        Thread.sleep(500);
                    }
                    frame.position.notifyAll();
                    while (frame.position.sheepsPosition[i].y < frame.board.m - 1) {
                        frame.position.sheepsPosition[i].y++;
                        frame.board.clearBackground();
                        frame.board.refreshBoard();
                        Thread.sleep(500);
                    }

                }*/
                Thread.sleep(500);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }




}
