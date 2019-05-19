package WolfSim;

public class Wolf implements Runnable{
    private MyFrame frame;

    public Wolf (MyFrame frame) {
        this.frame = frame;
    }

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(1000);
            frame.getMyBoard().wolfPosition.x ++;
            System.out.println("PORUSZONO W PRAWO");
            Thread.sleep(100);
            frame.getMyBoard().wolfPosition.y ++;
            System.out.println("PORUSZONO W GORE");
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    }

