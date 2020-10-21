package pong.model;

public abstract class Collsion {
    private double y;

    public Collsion(double y){
        this.y = y;
    }
    public double getY() {
        return y;
    }
}
