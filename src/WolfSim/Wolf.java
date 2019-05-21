package WolfSim;

public class Wolf implements Runnable{
    private MyFrame frame;

    public Wolf (MyFrame frame) {
        this.frame = frame;
    }

    @Override
    public synchronized void run() {

        synchronized(frame.position) {
            try {
                Thread.sleep(1000);
                frame.position.wolfPosition.x++;
                System.out.println("PORUSZONO W PRAWO");
                Thread.sleep(100);
                frame.position.wolfPosition.y++;
                System.out.println("PORUSZONO W DOL");

                frame.board.clearBackground();
                frame.board.refreshBoard();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    }

