package pong.model;

public abstract class AbstractPositionable implements IPositionable{
    private double dx, dy;
    private double x, y;
    private double speed;

    public AbstractPositionable(double x, double y, double dx, double dy, double speed){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }
    public void move(){
        this.x += this.speed * this.dx;
        this.y += this.speed * this.dy;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
    public double getSpeed(){
        return speed;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getDy() {
        return dy;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

}
