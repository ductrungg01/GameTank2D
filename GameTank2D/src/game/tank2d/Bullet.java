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
    private static final State DEFAULT_STATE = State.IDLE;

    Bullet(int x, int y, Rotation rotation) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT, DEFAULT_STATE, rotation);

        setAnimation(100, 0, 351, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public void Update(long deltaTime) {
        Move(this.getRotation(), BULLET_SPEED);

        this.getAnimation().Update_Me(deltaTime);

        this.updateRect();
    }
    public Explosion createNewExplosion(){
        int x = this.getPosX();
        int y = this.getPosY();

        switch (this.rotation){
            case UP -> {
                x = this.getPosX() + 3;
                break;
            }
            case DOWN -> {
                x = this.getPosX() + 3;
                y = this.getPosY() + BULLET_HEIGHT;
                break;
            }
            case LEFT -> {
                y = this.getPosY() + 3;
                break;
            }
            case RIGHT -> {
                x = this.getPosX() + BULLET_WIDTH;
                y = this.getPosY() + 3;
                break;
            }
        }

        return new Explosion(x - Explosion.EXPLOSION_WIDTH/2, y - Explosion.EXPLOSION_HEIGHT/2);
    }

}
