package game.tank2d;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.Objects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static game.tank2d.Tank2D.PIXEL;

enum Rotation {
    LEFT((float)( -0.5f*Math.PI)),
    UP(0),
    RIGHT((float)( 0.5f*Math.PI)),
    DOWN((float)( 1f*Math.PI));

    float rotate;

    Rotation(float i) {
        this.rotate = i;
    }

    public float getRotate() {
        return rotate;
    }
}
enum State{
    IDLE(0),
    RUN(1);

    int state;
    State(int i) {
        this.state = i;
    }

    public int getState() {
        return state;
    }
}

public class Player extends Objects {
    private BufferedImage imgTanks;
    private List<Animation> animation;
    private Rotation rotation;
    private State state;
    private Shield shield;
    static Player instance;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            InactiveShiled();
        }
    };

    static final int PLAYER_WIDTH = 26;
    static final int PLAYER_HEIGHT = 30;
    static final int PLAYER_MOVE = 8;

    static {
        try {
            instance = new Player();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //region Getter and Setter
    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Animation getAnimation() {
        return animation.get(state.getState());
    }

    public BufferedImage getImgTanks() {
        return imgTanks;
    }

    public static Player getInstance() {
        return instance;
    }

    public void setState(State state) {
        this.state = state;
    }
    //endregion

    public Bullet createNewBullet(){
        int xBullet = 0;
        int yBullet = 0;

        switch (this.rotation){
            case UP -> {
                xBullet = this.posX + PLAYER_WIDTH/2 - 4;
                yBullet = this.posY;
                break;
            }
            case DOWN -> {
                xBullet = this.posX + PLAYER_WIDTH/2 - 2;
                yBullet = this.posY + PLAYER_HEIGHT;
                break;
            }
            case LEFT -> {
                xBullet = posX;
                yBullet = posY + PLAYER_HEIGHT/2 - 4;
                break;
            }
            case RIGHT -> {
                xBullet = posX + PLAYER_WIDTH;
                yBullet = posY + PLAYER_HEIGHT/2 - 4;
                break;
            }
        }

        return new Bullet(xBullet, yBullet, this.rotation);
    }
    private Player() throws IOException {
        super(PIXEL * 8, PIXEL * (26 - 2), PLAYER_WIDTH, PLAYER_HEIGHT);
        Reset();
    }
    public void update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
        if (this.shield.isActive()){
            this.shield.update(deltaTime);
        }
    }
    public  boolean checkBoundX(int PosX) {
        if (PosX < 0 || PosX > PIXEL * Tank2D.MAP_WIDTH_TILE - PLAYER_WIDTH)
            return false;
        return true;
    }

    public  boolean checkBoundY(int PosY) {
        if (PosY < 0 || PosY > PIXEL * Tank2D.MAP_HEIGHT_TILE - PLAYER_HEIGHT)
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

        this.shield.setPos(this.getPosX() - 4, this.getPosY() + 1);
    }

    //region Move method
    public Point get_Left_Location(){
        int x, y;
        if (checkBoundX(Player.instance.getPosX() - PLAYER_MOVE))
            x = Player.instance.getPosX() - PLAYER_MOVE ;
        else
            x = 0;

        y = this.getPosY();

        return new Point(x, y);
    }
    public Point get_Up_Location(){
        int x, y;

        if (checkBoundY(Player.instance.getPosY() - PLAYER_MOVE))
            y = (Player.instance.getPosY() - PLAYER_MOVE);
        else
            y = 0;

        x = this.getPosX();

        return new Point(x, y);
    }
    public Point get_Right_Location(){
        int x, y;

        if (checkBoundX(Player.instance.getPosX() + PLAYER_MOVE))
            x = (Player.instance.getPosX() + PLAYER_MOVE);
        else
            x = (PIXEL * Tank2D.MAP_WIDTH_TILE - PLAYER_WIDTH);

        y = this.getPosY();

        return new Point(x, y);
    }
    public Point get_Down_Location(){
        int x, y;

        if (checkBoundY(Player.instance.getPosY() + PLAYER_MOVE))
            y = (Player.instance.getPosY() + PLAYER_MOVE);
        else
            y = (PIXEL * Tank2D.MAP_HEIGHT_TILE - PLAYER_HEIGHT);

        x = this.getPosX();

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
        //posX = PIXEL * 8;
        //posY = PIXEL * (26 - 2);

        timer.schedule(timerTask, Shield.SHIELD_ACTIVE_TIME);
        this.shield = new Shield(this.getPosX() - 4, this.getPosY() + 1);

        imgTanks = ImageIO.read(new File("Assets/sprite.PNG"));
        rotation = Rotation.UP;
        state = State.IDLE;
        animation = new ArrayList<Animation>();

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(195, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(227, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
    public void paint(Graphics2D g2){
        this.getAnimation().PaintAnims(this.getPosX(), this.getPosY(), this.getImgTanks(), g2, 0, this.getRotation().getRotate());

        if (this.getShield().isActive()){
            this.getShield().paint(g2);
        }
    }
}
