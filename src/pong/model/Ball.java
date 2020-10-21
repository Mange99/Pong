package pong.model;
/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball extends AbstractPositionable {

    public static final double WIDTH = 40;
    public static final double HEIGHT = 40;
    public static final double STARTSPEED = 2;

    public Ball(double x, double y, double dx, double dy, double speed){
        super(x,y,dx,dy, speed);
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }
}
