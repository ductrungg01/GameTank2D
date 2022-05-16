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

enum TypeOfEnemy{
    ENEMY001(1),
    ENEMY002(2),
    ENEMY003(3),
    ENEMY004(4);

    int type;
    TypeOfEnemy(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }
}

public class Enemy extends Objects {

    public static final int ENEMY_WIDTH = 26;
    public static final int ENEMY_HEIGHT = 30;

    private BufferedImage imgTanks;
    private List<Animation> animation;
    private Rotation rotation;
    private State state;

    private TypeOfEnemy type;

    Enemy(TypeOfEnemy type, int x, int y, int w, int h) {
        super(x, y, w, h);

        this.type = type;

        try {
            imgTanks = ImageIO.read(new File("Assets/sprite.bmp"));
        } catch (IOException ex) {}

        //rotation = Rotation.DOWN;
        rotation = Rotation.UP;
        //rotation = Rotation.RIGHT;
        //rotation = Rotation.LEFT;
        state = State.IDLE;
        animation = new ArrayList<Animation>();

        switch (type){
            case ENEMY001:
                loadAnimationOfType001();
                break;
            case ENEMY002:
                loadAnimationOfType002();
                break;
            case ENEMY003:
                loadAnimationOfType003();
                break;
            case ENEMY004:
                loadAnimationOfType004();
                break;
        }
    }

    //region Load animation
    private void loadAnimationOfType001(){
        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 425, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
    private void loadAnimationOfType002(){
        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 555, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
    private void loadAnimationOfType003(){
        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 682, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
    private void loadAnimationOfType004(){
        //// IDLE
        Animation anim = new Animation(100);
        AFrameOnImage aFrameOnImage;
        aFrameOnImage = new AFrameOnImage(3, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        animation.add(anim);

        /// RUN
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(3, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(35, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(67, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(99, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(131, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(163, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        anim = new Animation(100);
        aFrameOnImage = new AFrameOnImage(195, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(227, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(259, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(291, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(323, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);
        aFrameOnImage = new AFrameOnImage(355, 809, ENEMY_WIDTH, ENEMY_HEIGHT);
        anim.AddFrame(aFrameOnImage);

        animation.add(anim);
    }
    //endregion
    public void update(long deltaTime){
        Rotation rotation = this.getRotation();

        this.setState(State.RUN);
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
        this.setPosY(this.getPosY() - 1);
        this.setRotation(Rotation.UP);
    }
    private void goDown(){
        this.setPosY(this.getPosY() + 1);
        this.setRotation(Rotation.DOWN);
    }
    private void goLeft(){
        this.setPosX(this.getPosX() - 1);
        this.setRotation(Rotation.LEFT);
    }
    private void goRight(){
        this.setPosX(this.getPosX() + 1);
        this.setRotation(Rotation.RIGHT);
    }
    //endregion

    //region Getter and Setter
    public BufferedImage getImgTanks() {
        return imgTanks;
    }

    public void setImgTanks(BufferedImage imgTanks) {
        this.imgTanks = imgTanks;
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

    public void setState(State state) {
        this.state = state;
    }
    public TypeOfEnemy getType() {
        return type;
    }

    public void setType(TypeOfEnemy type) {
        this.type = type;
    }
    //endregion

}
