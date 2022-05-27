/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dgamesframework;

import game.tank2d.Shield;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    protected BufferedImage image;
    public static final String DEFAULT_LINK_IMAGE = "Assets/sprite.PNG";
    protected Rectangle rect;
    protected List<Animation> animation = new ArrayList<Animation>();
    protected Rotation rotation;
    protected State state;
    public Objects(){
         pos.x = pos.y = w = h = 0;
    }
    
    public Objects(int x, int y, int w, int h){
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.setRect(new Rectangle(x, y, w, h));
    }
    public Objects(int x, int y, int w, int h, State state, Rotation rotation, BufferedImage image){
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.state = state;
        this.rotation = rotation;
        this.image = image;
        this.setRect(new Rectangle(x, y, w, h));
    }
    public Objects(int x, int y, int w, int h, State state, Rotation rotation, String image){
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.state = state;
        this.rotation = rotation;
        setImage(image);
        this.setRect(new Rectangle(x, y, w, h));
    }
    public Objects(int x, int y, int w, int h, State state, Rotation rotation){
        this.pos = new Point(x, y);
        this.w = w;
        this.h = h;
        this.state = state;
        this.rotation = rotation;
        setImage(DEFAULT_LINK_IMAGE);
        this.setRect(new Rectangle(x, y, w, h));
    }
    public void Reset(int x, int y, State state, Rotation rotation){
        this.pos = new Point(x, y);
        this.state = state;
        this.rotation = rotation;
        this.rect.setLocation(x, y);
    }
    public void Paint(Graphics2D g2){
        this.getAnimation().PaintAnims(this.getPosX(), this.getPosY(), this.getImage(), g2, 0, this.getRotation().getRotate());
    }
    public void Update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
    }
    public void Move(Rotation rotation, int distance){
        this.rotation = rotation;

        switch (rotation){
            case UP:
                this.pos.y -= distance;
                break;
            case DOWN:
                this.pos.y += distance;
                break;
            case LEFT:
                this.pos.x -= distance;
                break;
            case RIGHT:
                this.pos.x += distance;
                break;
        }

        updateRect();
    }
    public boolean isCollisionHappenWith(float x, float y){
        if(x > pos.x && x < pos.x + w && y > pos.y && y < pos.y + h)
            return true;
        return false;
    }
    public boolean isCollisionHappenWith(float x, float y, float w, float h){
        if(x < pos.x + this.w && x + w > pos.x && y < pos.y + this.h && h + y > pos.y)
            return true;
        return false;
    }
    public void increasePosX(float m){
        pos.x += m;
    }
    public void increasePosY(float m){
        pos.y += m;
    }

    //region Getter and Setter
    public void setPos(int x, int y){
        this.pos = new Point(x, y);
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
    public float getW(){
        return w;
    }
    public float getH(){
        return h;
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
