

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

public class EnemyAppear extends Objects {
    public static final int ENEMYAPPEAR_WIDTH = 26;
    public static final int ENEMYAPPEAR_HEIGHT = 30;
    public static final int ENEMYAPPEAR_ACTIVE_TIME = 1700;

    private BufferedImage imgEnemyAppear;
    private List<Animation> animation;
    private final Rotation rotation = Rotation.UP;
    private final State state = State.IDLE;
    public boolean isActive = true;

    EnemyAppear(int x, int y){
        super(x, y, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);

        try {
            imgEnemyAppear = ImageIO.read(new File("Assets/sprite.PNG"));
        } catch (IOException ex) {}

        animation = new ArrayList<Animation>();

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(255, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(288, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(321, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(354, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(387, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(420, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);
    }

    public void update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
    }
    public void paint(Graphics2D g2){
        this.getAnimation().PaintAnims(this.getPosX(), this.getPosY(), this.getImgEnemyAppear(), g2, 0, this.getRotation().getRotate());
    }
    //region Getter and Setter
    public BufferedImage getImgEnemyAppear() {
        return imgEnemyAppear;
    }

    public void setImgEnemyAppear(BufferedImage imgEnemyAppear) {
        this.imgEnemyAppear = imgEnemyAppear;
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
