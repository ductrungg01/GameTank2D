package game.tank2d;

import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.Objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player2 extends Objects {
    private BufferedImage imgTanks;
    private List<Animation> animation;
    private Rotation rotation;
    private State state;

    static Player2 instance;

    static {
        try {
            instance = new Player2();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static Player2 getInstance() {
        return instance;
    }

    public void setState(State state) {
        this.state = state;
    }

    private Player2() throws IOException {
        Reset();
    }

    public void Reset() throws IOException {
        posX = 200;
        posY = 200;
        imgTanks = ImageIO.read(new File("Assets/sprite.bmp"));
        rotation = Rotation.UP;
        state = State.IDLE;
        animation = new ArrayList<Animation>();

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(195, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(227, 130, 26, 30);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
}
