/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import game.tank2d.Tank2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static game.tank2d.Tank2D.PIXEL;


public class Objects {

    public enum Rotation {
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
    public enum State{
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

    protected Point pos;
    protected int w, h;
    protected boolean needCheckBound = false;
    public boolean isDestroyAlready = false;
    public boolean isNotGameObject = false;
    protected final int OBJECT_MOVE;
    protected BufferedImage image;
    public static final String DEFAULT_LINK_IMAGE = "Assets/sprite.PNG";
    protected Rectangle rect;
    protected List<Animation> animation = new ArrayList<Animation>();
    protected Rotation rotation;
    protected State state;

    //region Constructor
    public Objects(int object_move){
        OBJECT_MOVE = object_move;
        pos.x = pos.y = w = h = 0;
    }
    public Objects(int x, int y, int w, int h, int object_move){
        OBJECT_MOVE = object_move;
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.setRect(new Rectangle(x, y, w, h));
    }
    public Objects(int x, int y, int w, int h, int object_move, State state, Rotation rotation, BufferedImage image){
        OBJECT_MOVE = object_move;
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.state = state;
        this.rotation = rotation;
        this.image = image;
        this.setRect(new Rectangle(x, y, w, h));
    }
    public Objects(int x, int y, int w, int h, State state, Rotation rotation, String image, int object_move){
        OBJECT_MOVE = object_move;
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.state = state;
        this.rotation = rotation;
        setImage(image);
        this.setRect(new Rectangle(x, y, w, h));
    }
    public Objects(int x, int y, int w, int h, int object_move, State state, Rotation rotation){
        OBJECT_MOVE = object_move;
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.state = state;
        this.rotation = rotation;
        setImage(DEFAULT_LINK_IMAGE);
        this.setRect(new Rectangle(x, y, w, h));
    }
    //endregion

    //region Move
    public Point getNextPos(Rotation rotation){
        int x = pos.x;
        int y = pos.y;

        switch (rotation){
            case UP:
                y -= OBJECT_MOVE;
                break;
            case DOWN:
                y += OBJECT_MOVE;
                break;
            case LEFT:
                x -= OBJECT_MOVE;
                break;
            case RIGHT:
                x += OBJECT_MOVE;
                break;
        }

        if (this.needCheckBound){
            return getNewPointAfterCheckBound(rotation, new Point(x, y));
        }

        return new Point(x, y);
    }
    public void Move(Rotation rotation){
        this.rotation = rotation;

        Point nextPos = getNextPos(rotation);

        setPos(nextPos);

        updateRect();
    }
    //endregion

    //region Check bound
    public  boolean checkBoundX(int posX) {
        if (posX < PIXEL * 2 || posX > PIXEL * (Tank2D.MAP_WIDTH_TILE + 2) - this.w)
            return false;
        return true;
    }

    public  boolean checkBoundY(int posY) {
        if (posY < PIXEL * 2 || posY > PIXEL * (Tank2D.MAP_HEIGHT_TILE + 2) - this.h)
            return false;
        return true;
    }
    public Point getNewPointAfterCheckBound(Rotation rotation, Point p){
        int x = p.x;
        int y = p.y;

        switch (rotation){
            case UP -> {
                if (!checkBoundY(y)){
                    y = PIXEL * 2;
                }
                break;
            }
            case DOWN -> {
                if (!checkBoundY(y)){
                    y = (PIXEL * (Tank2D.MAP_HEIGHT_TILE + 2) - this.h);
                }
                break;
            }
            case LEFT -> {
                if (!checkBoundX(x)){
                    x = PIXEL * 2;
                }
                break;
            }
            case RIGHT -> {
                if (!checkBoundX(x)){
                    x = (PIXEL * (Tank2D.MAP_WIDTH_TILE + 2) - this.w);
                }
                break;
            }
        }

        return new Point(x, y);
    }
    //endregion

    public void Reset(int x, int y, State state, Rotation rotation){
        this.pos = new Point(x, y);
        this.state = state;
        this.rotation = rotation;
        this.rect.setLocation(x, y);
    }
    public void Paint(Graphics2D g2){
        if (this.isDestroyAlready == false){
            this.getAnimation().PaintAnims(this.getPosX(), this.getPosY(), this.getImage(),
                    g2, 0, this.getRotation().getRotate());
        }
    }
    public void Update(long deltaTime){
        if (!this.isNotGameObject)
        if (!this.checkBoundX(this.getPosX()) || !this.checkBoundY(this.getPosY())){
            Destroy();
        }

        this.getAnimation().Update_Me(deltaTime);
    }
    public void Destroy(){
        this.isDestroyAlready = true;
    }
    public boolean checkCollision(Rectangle rectOfOrtherObject){
        if (this.isDestroyAlready == true)
            return false;

        Rectangle rect = getRect();
        return rect.intersects(rectOfOrtherObject);
    }
    public boolean checkCollision(Objects ortherObject){
        if (ortherObject.isDestroyAlready == true || this.isDestroyAlready == true)
            return false;

        Rectangle rect = getRect();
        Rectangle ortherRect = ortherObject.getRect();
        return rect.intersects(ortherRect);
    }

    //region Getter and Setter
    public void setPos(int x, int y){
        this.pos = new Point(x, y);
    }
    public void setPos(Point p){
        this.pos = p;
    }
    public void setPosX(int x){
        this.pos.x = x;
        updateRect();
    }
    public void setPosY(int y){
        this.pos.y = y;
        updateRect();
    }
    public int getPosX(){
        return pos.x;
    }
    public int getPosY(){
        return pos.y;
    }
    public Rectangle getRect() {
        return rect;
    }
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    public void updateRect(){
        this.rect.setLocation(pos.x, pos.y);
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public void setImage(String linkFile){
        try {
            this.image = ImageIO.read(new File(linkFile));
        } catch (IOException ex) {}
    }
    public Animation getAnimation() {
        return animation.get(state.getState());
    }
    public void setAnimation(List<Animation> animation) {
        this.animation = animation;
    }
    public void setAnimation(long mesure, int xOnImage, int yOnImage, int frameWidth,
                             int frameHeight, int xDistanceBetween, int yDistanceBetween, int numFrame){
        Animation anim = new Animation(mesure);
        AFrameOnImage aFrameOnImage;

        for (int i = 0; i < numFrame; i++){
            aFrameOnImage = new AFrameOnImage(xOnImage, yOnImage, frameWidth, frameHeight);
            anim.AddFrame(aFrameOnImage);

            xOnImage += xDistanceBetween;
            yOnImage += yDistanceBetween;
        }

        this.animation.add(anim);
    }
    public void setAnimation(long mesure, int xOnImage, int yOnImage, int frameWidth, int frameHeight){
        Animation anim = new Animation(mesure);
        AFrameOnImage aFrameOnImage;

        aFrameOnImage = new AFrameOnImage(xOnImage, yOnImage, frameWidth, frameHeight);
        anim.AddFrame(aFrameOnImage);
        this.animation.add(anim);
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
    //endregion

}
