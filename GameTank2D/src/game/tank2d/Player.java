package game.tank2d;

import pkg2dgamesframework.Objects;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static game.tank2d.Tank2D.PIXEL;

public class Player extends Objects {
    private Shield shield = new Shield(this.getPosX() - 2, this.getPosY() + 1);
    static Player instance;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            EndShield();
        }
    };

    static final int PLAYER_WIDTH = 30;
    static final int PLAYER_HEIGHT = 32;
    static final int PLAYER_MOVE = 8;
    static final int X_DEFAULT_LOCATION = PIXEL * 11;
    static final int Y_DEFAULT_LOCATION = PIXEL * (30 - 4);
    static final State DEFAULT_STATE = State.IDLE;
    static final Rotation DEFAULT_ROTATION = Rotation.UP;

    static {
        try {
            instance = new Player();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player() throws IOException {
        super(X_DEFAULT_LOCATION, Y_DEFAULT_LOCATION, PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_MOVE, DEFAULT_STATE, DEFAULT_ROTATION);
        this.needCheckBound = true;

        //// IDLE
        setAnimation(100, 0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);

        //// RUN
        setAnimation(100, 0, 0, PLAYER_WIDTH, PLAYER_HEIGHT, 32, 0, 8);

        Reset();
    }
    public Bullet createNewBullet(){
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

        return new Bullet(xBullet, yBullet, this.rotation);
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
        super.Reset(X_DEFAULT_LOCATION, Y_DEFAULT_LOCATION, DEFAULT_STATE, DEFAULT_ROTATION);
    }
    public void StartShield(){
        timer.schedule(timerTask, Shield.SHIELD_ACTIVE_TIME);
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
    public static Player getInstance() {
        return instance;
    }
    //endregion
}
