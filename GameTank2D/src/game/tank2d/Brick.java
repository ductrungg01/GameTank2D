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

enum TypeOfBrick{
    BRICK000(0),
    BRICK001(1),
    BRICK002(2),
    BRICK003(3),
    BUSH(4),
    WATER(5);

    int type;
    TypeOfBrick(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }

}

public class Brick extends Objects {
    public static final int BRICK_WIDTH = 16;
    public static final int BRICK_HEIGHT = 16;

    private BufferedImage imgBrick;
    private List<Animation> animation;
    private State state;
    private Rotation rotation;

    private TypeOfBrick type;

    Brick(TypeOfBrick type, int x, int y, int w, int h){
        super(x, y, w, h);

        this.type = type;

        try {
            imgBrick = ImageIO.read(new File("Assets/sprite.PNG"));
        } catch (IOException ex) {}

        rotation = Rotation.UP;
        state = State.IDLE;

        animation = new ArrayList<Animation>();

        int yOnImg = 0;

        switch (type){
            case BRICK001 -> {
                yOnImg = 255;
                break;
            }
            case BRICK002 -> {
                yOnImg = 271;
                break;
            }
            case BRICK003 -> {
                yOnImg = 288;
                break;
            }
            case BUSH -> {
                yOnImg = 304;
                break;
            }
            case WATER -> {
                yOnImg = 320;
                break;
            }
        }

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(0, yOnImg, BRICK_WIDTH, BRICK_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);
    }
    public void update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
    }
    //region Getter and Setter
    public BufferedImage getImgBrick() {
        return imgBrick;
    }

    public void setImgBrick(BufferedImage imgBrick) {
        this.imgBrick = imgBrick;
    }

    public Animation getAnimation() {
        return animation.get(state.getState());
    }

    public void setAnimation(List<Animation> animation) {
        this.animation = animation;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TypeOfBrick getType() {
        return type;
    }

    public void setType(TypeOfBrick type) {
        this.type = type;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }
//endregion
}
