package pong.model;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle extends AbstractPositionable {

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;

    public Paddle(double x, double y, double dx, double dy, double speed) {
        super(x, y, dx, dy, speed);
    }

    @Override
    public double getWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public double getHeight() {
        return PADDLE_HEIGHT;
    }
}
