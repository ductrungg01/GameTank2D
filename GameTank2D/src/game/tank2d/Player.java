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

    static Player instance;

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

    private Player() throws IOException {
        Reset();
    }

    public void Reset() throws IOException {
        posX = PIXEL * 8;
        posY = PIXEL * (26 - 2);
        imgTanks = ImageIO.read(new File("Assets/sprite.PNG"));
        rotation = Rotation.UP;
        state = State.IDLE;
        animation = new ArrayList<Animation>();

        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(195, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(227, 0, 26, 30);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
}
