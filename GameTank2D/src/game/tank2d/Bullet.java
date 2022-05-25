package game.tank2d;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bullet extends Objects {
    public static final int BULLET_WIDTH = 7;
    public static final int BULLET_HEIGHT = 9;
    private static final int BULLET_SPEED = 2;
    private BufferedImage imgBullet;
    private List<Animation> animation;
    private Rotation rotation;
    private final State state = State.IDLE;

    Bullet(int x, int y, Rotation rotation){
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT);

        try {
            imgBullet = ImageIO.read(new File("Assets/sprite.PNG"));
        } catch (IOException ex) {}

        this.rotation = rotation;
        animation = new ArrayList<Animation>();

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(0, 351, BULLET_WIDTH, BULLET_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);
    }

    public void update(long deltaTime){
        Rotation rotation = this.getRotation();

        switch (rotation){
            case UP -> {
                goUp();
                break;
            }
            case DOWN -> {
                goDown();
                break;
            }
            case LEFT -> {
                goLeft();
                break;
            }
            case RIGHT -> {
                goRight();
                break;
            }
        }
        this.getAnimation().Update_Me(deltaTime);
    }

    //region Move method
    private void goUp(){
        this.setPosY(this.getPosY() - BULLET_SPEED);
        this.setRotation(Rotation.UP);
    }
    private void goDown(){
        this.setPosY(this.getPosY() + BULLET_SPEED);
        this.setRotation(Rotation.DOWN);
    }
    private void goLeft(){
        this.setPosX(this.getPosX() - BULLET_SPEED);
        this.setRotation(Rotation.LEFT);
    }
    private void goRight(){
        this.setPosX(this.getPosX() + BULLET_SPEED);
        this.setRotation(Rotation.RIGHT);
    }
    //endregion

    public BufferedImage getImg() {
        return imgBullet;
    }

    public void setImgBullet(BufferedImage imgBullet) {
        this.imgBullet = imgBullet;
    }

    public Animation getAnimation() {
        return animation.get(state.getState());
    }

    public void setAnimation(List<Animation> animation) {
        this.animation = animation;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public State getState() {
        return state;
    }
}
