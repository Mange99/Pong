package pong.model;


import pong.event.EventBus;
import pong.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Logic for the Pong Game
 * Model class representing the "whole" game
 * Nothing visual here
 *
 */
public class Pong {
    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.02;
    public static final long HALF_SEC = 500;


    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;

    private Ball ball;
    private Paddle rightPaddle;
    private Paddle leftPaddle;
    private Ceiling ceiling;
    private Floor floor;

    public Pong(Ball ball, Paddle rightPaddle, Paddle leftPaddle, Ceiling ceiling, Floor floor) {
        this.ball = ball;
        this.rightPaddle = rightPaddle;
        this.leftPaddle = leftPaddle;
        this.ceiling = ceiling;
        this.floor = floor;

    }

    // --------  Game Logic -------------

    private long timeForLastHit = System.currentTimeMillis();         // To avoid multiple collisions

    public void update(long now) {
        // tODO Gamelogic here
        leftPaddle.move();
        paddleCollision(leftPaddle);
        rightPaddle.move();
        paddleCollision(rightPaddle);
        ball.move();

        floorCeilingCollision();
        //kollar omm den inte har träffat racket på 0.5 sec
        //Yeeeet
        if(System.currentTimeMillis() > timeForLastHit+HALF_SEC){
            checkCollision();
        }

        win();

    }
    public void respawn(){
        ball.setX(GAME_WIDTH / 2 - ball.getWidth() / 2);
        ball.setY(GAME_HEIGHT / 2 - ball.getHeight() / 2);
        int rnd = new Random().nextBoolean()? 1 : -1;
        int rnd1 = new Random().nextBoolean()? 1 : -1;
        ball.setSpeed(Ball.STARTSPEED);
        ball.setDy(rnd);
        ball.setDx(rnd1);

    }
    public void win(){
        if (ball.getX() >= GAME_WIDTH - ball.getWidth()) {
            pointsLeft++;
            respawn();

        } else if (ball.getX() <= 0) {
            pointsRight++;
            respawn();
        }
    }
    //Hej
    //HEHHEEHEHEH
    public void twirl(Paddle paddle){
        if (paddle.getDy() == 0) {
            ball.setDy(ball.getDy());
        }else{
            ball.setDy(paddle.getDy());
        }
    }
    public void floorCeilingCollision() {
        if (ball.getY() <= ceiling.getY()) {
            ball.setDy(1);
            //EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_WALL_CEILING);
        } else if (ball.getY() + ball.getHeight() >= floor.getY()) {
            ball.setDy(-1);
            //EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_WALL_CEILING);
        }
    }
    public void checkCollision(){
        if(ball.getX() <= leftPaddle.getX()+Paddle.PADDLE_WIDTH &&
                ball.getY()+ ball.getHeight() > leftPaddle.getY() &&
                ball.getY() < leftPaddle.getY() + leftPaddle.getHeight()){
                    EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_PADDLE);
                    ball.setDx(1);
                    ball.setSpeed(ball.getSpeed()*BALL_SPEED_FACTOR);
                    twirl(leftPaddle);
                    timeForLastHit = System.currentTimeMillis();
        }else if(ball.getX()+ball.getWidth() >= rightPaddle.getX() &&
                ball.getY() + ball.getHeight() > rightPaddle.getY() &&
                ball.getY() < rightPaddle.getY() + rightPaddle.getHeight()) {
                    EventBus.INSTANCE.publish(ModelEvent.Type.BALL_HIT_PADDLE);
                    ball.setDx(-1);
                    ball.setSpeed(ball.getSpeed()*BALL_SPEED_FACTOR);
                    twirl(rightPaddle);
                    timeForLastHit = System.currentTimeMillis();
        }
    }
    public void paddleCollision(Paddle paddle){
        if(paddle.getY() <= ceiling.getY()){
            paddle.setY(0);
        }else if(paddle.getY() + paddle.getHeight() >= floor.getY()){
            paddle.setY(floor.getY()-paddle.getHeight());
        }
    }
    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        drawables.add(rightPaddle);
        drawables.add(leftPaddle);
        drawables.add(ball);
        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;
    }

    public void setSpeedRightPaddle(double dy) {
        rightPaddle.setDy(dy);
    }

    public void setSpeedLeftPaddle(double dy) {
        leftPaddle.setDy(dy);

    }

    public Paddle getRightPaddle() {
        return rightPaddle;
    }

    public Paddle getLeftPaddle() {
        return leftPaddle;
    }

}
