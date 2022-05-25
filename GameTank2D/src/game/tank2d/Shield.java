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

public class Shield extends Objects {
    public static final int SHIELD_WIDTH = 33;
    public static final int SHIELD_HEIGHT = 33;
    public static final int SHIELD_ACTIVE_TIME = 3000;

    private BufferedImage imgShield;
    private List<Animation> animation;
    private final Rotation rotation = Rotation.UP;
    private final State state = State.IDLE;
    public boolean isActive = true;
    Shield(int x, int y){
        super(x, y, SHIELD_WIDTH, SHIELD_HEIGHT);

        try {
            imgShield = ImageIO.read(new File("Assets/sprite.PNG"));
        } catch (IOException ex) {}

        animation = new ArrayList<Animation>();

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(255, 0, SHIELD_WIDTH, SHIELD_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(288, 0, SHIELD_WIDTH, SHIELD_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);
    }
    public void update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
    }
    public void paint(Graphics2D g2){
        this.getAnimation().PaintAnims(this.getPosX(), this.getPosY(), this.getImgShield(), g2, 0, this.getRotation().getRotate());
    }
    //region Getter and Setter
    public BufferedImage getImgShield() {
        return imgShield;
    }

    public void setImgShield(BufferedImage imgShield) {
        this.imgShield = imgShield;
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

    public State getState() {
        return state;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    //endregion
}
