package game.tank2d;

import pkg2dgamesframework.Objects;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import static game.tank2d.Tank2D.PIXEL;



public class Player extends Objects {
    private Shield shield;
    static Player instance;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            InactiveShiled();
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
        super(X_DEFAULT_LOCATION, Y_DEFAULT_LOCATION, PLAYER_WIDTH, PLAYER_HEIGHT, DEFAULT_STATE, DEFAULT_ROTATION);

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
                xBullet = this.pos.x + PLAYER_WIDTH/2 - 4;
                yBullet = this.pos.y;
                break;
            }
            case DOWN -> {
                xBullet = this.pos.x + PLAYER_WIDTH/2 - 2;
                yBullet = this.pos.y + PLAYER_HEIGHT;
                break;
            }
            case LEFT -> {
                xBullet = pos.x;
                yBullet = pos.y + PLAYER_HEIGHT/2 - 4;
                break;
            }
            case RIGHT -> {
                xBullet = pos.x + PLAYER_WIDTH;
                yBullet = pos.y + PLAYER_HEIGHT/2 - 4;
                break;
            }
        }

        return new Bullet(xBullet, yBullet, this.rotation);
    }
    public void Update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
//        if (this.shield.isActive()){
//            this.shield.Update(deltaTime);
//        }
    }
    public  boolean checkBoundX(int PosX) {
        if (PosX < PIXEL * 2 || PosX > PIXEL * (Tank2D.MAP_WIDTH_TILE + 2) - PLAYER_WIDTH)
            return false;
        return true;
    }

    public  boolean checkBoundY(int PosY) {
        if (PosY < PIXEL * 2 || PosY > PIXEL * (Tank2D.MAP_HEIGHT_TILE + 2) - PLAYER_HEIGHT)
            return false;
        return true;
    }

    public void Move(Rotation rotation){
        this.setRotation(rotation);
        this.setState(State.RUN);

        switch (rotation){
            case UP:
                Go_Up();
                break;
            case DOWN:
                Go_Down();
                break;
            case RIGHT:
                Go_Right();
                break;
            case LEFT:
                Go_Left();
                break;
        }

        //this.shield.setPos(this.getPosX() - 2, this.getPosY() + 1);
    }

    //region Move method
    public Point get_Left_Location(){
        int x = Player.instance.getPosX() - PLAYER_MOVE;
        if (!checkBoundX(x)){
            x = PIXEL * 2;
        }
        int y = this.getPosY();

        return new Point(x, y);
    }
    public Point get_Up_Location(){
        int y = Player.instance.getPosY() - PLAYER_MOVE;

        if (!checkBoundY(y)) {
            y = PIXEL * 2;
        }

        int x = this.getPosX();

        return new Point(x, y);
    }
    public Point get_Right_Location(){
        int x = Player.instance.getPosX() + PLAYER_MOVE;;

        if (!checkBoundX(x)){
            x = (PIXEL * (Tank2D.MAP_WIDTH_TILE + 2) - PLAYER_WIDTH);
        }

        int y = this.getPosY();

        return new Point(x, y);
    }
    public Point get_Down_Location(){
        int y = Player.instance.getPosY() + PLAYER_MOVE;

        if (!checkBoundY(y)){
            y = (PIXEL * (Tank2D.MAP_HEIGHT_TILE + 2) - PLAYER_HEIGHT);
        }

        int x = this.getPosX();

        return new Point(x, y);
    }
    void Go_Left(){
        Point p = get_Left_Location();
        this.setPos(p.x, p.y);
    }
    void Go_Up(){
        Point p = get_Up_Location();
        this.setPos(p.x, p.y);
    }
    void Go_Right(){
        Point p = get_Right_Location();
        this.setPos(p.x, p.y);
    }
    void Go_Down(){
        Point p = get_Down_Location();
        this.setPos(p.x, p.y);
    }
    //endregion
    public void Dead(){
        this.timer.cancel();
    }
    private void InactiveShiled(){
        this.shield.setActive(false);
    }
    public void Reset() throws IOException {
        super.Reset(X_DEFAULT_LOCATION, Y_DEFAULT_LOCATION, DEFAULT_STATE, DEFAULT_ROTATION);

//        timer.schedule(timerTask, Shield.SHIELD_ACTIVE_TIME);
//        this.shield = new Shield(this.getPosX() - 2, this.getPosY() + 1);
    }
    public void Paint(Graphics2D g2){
        super.Paint(g2);

//        if (this.getShield().isActive()){
//            this.getShield().Paint(g2);
//        }
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
