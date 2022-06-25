package game.tank2d;

import pkg2dgamesframework.Objects;

import javax.sound.sampled.LineUnavailableException;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static game.tank2d.Tank2D.*;

public class Player2 extends Objects {
    static Player2 instance;
    private Shield shield = new Shield(this.getPosX() - 2, this.getPosY() + 1);
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            EndShield();
        }
    };

    static final int PLAYER_WIDTH = 30;
    static final int PLAYER_HEIGHT = 32;
    static final int PLAYER_MOVE = 2;
    static final int X_DEFAULT_LOCATION = PIXEL * 18;
    static final int Y_DEFAULT_LOCATION = PIXEL * (30 - 4);
    static final State DEFAULT_STATE = State.IDLE;
    static final Rotation DEFAULT_ROTATION = Rotation.UP;
    static private long timeShoot = System.currentTimeMillis();
    static {
        try {
            instance = new Player2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Player2() throws IOException {
        super(X_DEFAULT_LOCATION, Y_DEFAULT_LOCATION, PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_MOVE, DEFAULT_STATE, DEFAULT_ROTATION);
        this.needCheckBound = true;
        setAnimation(100, 0, 128, PLAYER_WIDTH, PLAYER_HEIGHT);

        //// RUN
        setAnimation(100, 0, 128, PLAYER_WIDTH, PLAYER_HEIGHT, 32, 0, 2);
        Reset();
    }

    public Bullet createNewBullet() {
        int xBullet = 0;
        int yBullet = 0;

        switch (this.rotation){
            case UP -> {
                xBullet = this.pos.x + PLAYER_WIDTH/2 - 3;
                yBullet = this.pos.y - 9;
                break;
            }
            case DOWN -> {
                xBullet = this.pos.x + PLAYER_WIDTH/2 - 3;
                yBullet = this.pos.y + PLAYER_HEIGHT + 9;
                break;
            }
            case LEFT -> {
                xBullet = pos.x - 9;
                yBullet = pos.y + PLAYER_HEIGHT/2 - 4;
                break;
            }
            case RIGHT -> {
                xBullet = pos.x + PLAYER_WIDTH + 9;
                yBullet = pos.y + PLAYER_HEIGHT/2 - 4;
                break;
            }
        }
        return new Bullet(xBullet, yBullet, this.rotation, 1);
    }
    public void Update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
        if (this.shield.isActive()){
            this.shield.Update(deltaTime);
        }
    }
    public void Move(Rotation rotation){
        super.Move(this.rotation);
        this.state = State.RUN;
        this.shield.setPos(this.getPosX() - 2, this.getPosY() + 1);
    }

    public void Dead(){
        this.timer.cancel();
    }
    private void EndShield(){
        this.shield.setActive(false);
    }
    public void Reset() throws IOException {
        this.isDestroyAlready = false;
        super.Reset(X_DEFAULT_LOCATION, Y_DEFAULT_LOCATION, DEFAULT_STATE, DEFAULT_ROTATION);
        StartShield();
    }
    public void StartShield(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                EndShield();
            }
        }, Shield.SHIELD_ACTIVE_TIME);
        this.shield = new Shield(this.getPosX() - 2, this.getPosY() + 1);
        this.shield.isDestroyAlready = false;
    }
    public void Paint(Graphics2D g2){
        super.Paint(g2);

        if (this.getShield().isActive()){
            this.getShield().Paint(g2);
        }
    }

    //region Getter and Setter
    public Shield getShield() {
        return shield;
    }
    public void setShield(Shield shield) {
        this.shield = shield;
    }
    public static Player2 getInstance() {
        return instance;
    }
    public static long getTimeShoot() {
        return timeShoot;
    }
    public void setTimeShoot(long time) {
        this.timeShoot = time;
    }
    //endregion
}
