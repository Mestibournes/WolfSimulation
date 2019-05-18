package WolfSim;

public class MyRun implements Runnable {

    private int id;

    public MyRun(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("Watek "+id);
            try {
                //usypiamy wątek na 100 milisekund
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
