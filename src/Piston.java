import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jackson on 11/13/14.
 */

//This is a generic engine object, all other objects should extend this
public class Piston {

    private double x,y; //TODO: ensure thread-safe
    double vx,vy,ax,ay,rot,density;
    Shape shape;
    ArrayList<Piston> others;
    public Piston (ArrayList<Piston> others, double x, double y, double vx, double vy, double ax, double ay, double rot, int density, Shape shape){
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.ax=ax;
        this.ay=ay;
        this.rot=rot;
        this.shape=shape;
        this.shape.setPos(x,y);
        this.others=others;
        this.density = density;
    }
    public Piston (double x, double y, double vx, double vy, double ax, double ay, double rx, int density, Shape shape){
        this(null,x,y,vx,vy,ax,ay,rx,density,shape);
    }

    public synchronized double getX() {
        return x;
    }

    public synchronized double getY() {
        return y;
    }

    private synchronized void addXY(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void update(){

        if (x + vx - shape.lengthToEdge(Math.PI) < 0 || x + vx + shape.lengthToEdge(0) >= Main.SIZE){
            vx *= -1;
        }
        if (y + vy - shape.lengthToEdge(Math.PI/2) < 0 || y + vy + shape.lengthToEdge(3*Math.PI/2) >= Main.SIZE){
            vy *= -1;
        }
        addXY(vx,vy); //thread safe
        vx += ax;
        vy += ay;
        //Handle the next move with eng.physics.XXX();

    }
    public void bindEngine(ArrayList<Piston> others){
        this.others=others;
    }
    public double mass(){

        return shape.area()*density;
    }
    public void draw(Graphics g){
        shape.setPos(x,y);
        shape.draw(g);
    }

    @Override
    public String toString() {
        return "Piston{" +
                "density=" + density +
                ", x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                ", ax=" + ax +
                ", ay=" + ay +
                ", rot=" + rot +
                '}';
    }
}
