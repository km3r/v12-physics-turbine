import java.awt.*;

/**
 * Created by Jackson on 11/13/14.
 */

import java.util.*;
public class Engine implements Runnable{
    ArrayList <Piston> pistons;
    Physics phys;
    public Engine(ArrayList<Piston> pistons){
        this.pistons = pistons;
        this.phys = new Physics(pistons);
    }

    public synchronized void update(){
        phys.update();
        for (Piston p: pistons){
            p.update();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();

        }
    }
}
