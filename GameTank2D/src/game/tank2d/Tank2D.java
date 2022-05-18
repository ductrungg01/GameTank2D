package game.tank2d;

import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static game.tank2d.Brick.BRICK_HEIGHT;
import static game.tank2d.Brick.BRICK_WIDTH;
import static game.tank2d.Enemy.ENEMY_HEIGHT;
import static game.tank2d.Enemy.ENEMY_WIDTH;
import static game.tank2d.Player.PLAYER_WIDTH;
import static game.tank2d.Player.PLAYER_HEIGHT;
import static game.tank2d.Player.PLAYER_MOVE;


//interface GameObject {
//    public void update(long deltaTime);
//    public Animation getAnimation();
//    public Rotation getRotation();
//    public int getPosX();
//    public int getPosY();
//    public BufferedImage getImg();
//}

public class Tank2D extends GameScreen {

    static ArrayList<Brick> mapBrick = new ArrayList<Brick>();

    public static final int MAP_WIDTH_TILE = 26;
    public static final int MAP_HEIGHT_TILE = 26;
    public static final int PIXEL = 16;

    static Map maps = new Map();

    Alphabet Al = new Alphabet(TypeOfAlphabet.BSLASH, 16, 16, BRICK_WIDTH, BRICK_HEIGHT);

    Enemy enemy001 = new Enemy(TypeOfEnemy.ENEMY004, 50, 16, ENEMY_WIDTH, ENEMY_HEIGHT);

    Bullet bullet001 = new Bullet(16, 16, Rotation.DOWN);

    Shield shield001 = new Shield(PIXEL * 8 , MAP_HEIGHT_TILE * PIXEL - PLAYER_HEIGHT);

    //ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    public Tank2D() {
        super(MAP_WIDTH_TILE * PIXEL, MAP_HEIGHT_TILE * PIXEL);

        BeginGame();
    }

    public static void main(String[] args) {

        maps.initBrickMap1();
        mapBrick = maps.getBrickListMap1();
        GameScreen game = new Tank2D();
    }

    public  boolean checkBoundX(int PosX) {
        if (PosX < 0 || PosX > PIXEL * MAP_WIDTH_TILE - PLAYER_WIDTH)
            return false;
        return true;
    }

    public  boolean checkBoundY(int PosY) {
        if (PosY < 0 || PosY > PIXEL * MAP_HEIGHT_TILE - PLAYER_HEIGHT)
            return false;
        return true;
    }

    public void PAINT_OBJECT(Graphics2D g2, Brick b) {
        b.getAnimation().PaintAnims(b.getPosX(), b.getPosY(), b.getImgBrick(), g2, 0, b.getRotation().getRotate());
    }

    public void PAINT_OBJECT(Graphics2D g2, Alphabet b) {
        b.getAnimation().PaintAnims(b.getPosX(), b.getPosY(), b.getImgAlphabet(), g2, 0, b.getRotation().getRotate());
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        // shield
        shield001.update(deltaTime);

        // player
        Player.getInstance().getAnimation().Update_Me(deltaTime);

        // player2
        Player2.getInstance().getAnimation().Update_Me(deltaTime);

        // enemy
        enemy001.update(deltaTime);

        // bullet
        bullet001.update(deltaTime);

    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, MAP_WIDTH_TILE * PIXEL, MAP_HEIGHT_TILE * PIXEL);



        Player.getInstance().getAnimation().PaintAnims(Player.getInstance().getPosX(), Player.getInstance().getPosY(), Player.getInstance().getImgTanks(), g2, 0, Player.getInstance().getRotation().getRotate());

        for (int i = 0; i < mapBrick.size(); i++)
        {
            PAINT_OBJECT(g2, mapBrick.get(i));
        }


        //region Test
        // shield
        shield001.getAnimation().PaintAnims(shield001.getPosX(), shield001.getPosY(), shield001.getImgShield(), g2, 0, shield001.getRotation().getRotate());

        // player
        PAINT_OBJECT(g2, Al);
        // player2
        Player2.getInstance().getAnimation().PaintAnims(Player2.getInstance().getPosX(), Player2.getInstance().getPosY(), Player2.getInstance().getImgTanks(), g2, 0, Player2.getInstance().getRotation().getRotate());

        // enemy
        enemy001.getAnimation().PaintAnims(enemy001.getPosX(), enemy001.getPosY(), enemy001.getImgTanks(), g2, 0, enemy001.getRotation().getRotate());

        // bullet
        bullet001.getAnimation().PaintAnims(bullet001.getPosX(), bullet001.getPosY(), bullet001.getImg(), g2, 0, bullet001.getRotation().getRotate());

        //endregion
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: // left
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.LEFT);
                    if (checkBoundX(Player.instance.getPosX() - PLAYER_MOVE))
                        Player.getInstance().setPosX(Player.instance.getPosX() - PLAYER_MOVE);
                    else
                        Player.getInstance().setPosX(0);
                    break;
                case KeyEvent.VK_UP: // up
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.UP);
                    if (checkBoundY(Player.instance.getPosY() - PLAYER_MOVE))
                        Player.getInstance().setPosY(Player.instance.getPosY() - PLAYER_MOVE);
                    else
                        Player.getInstance().setPosY(0);
                    break;
                case KeyEvent.VK_RIGHT: // right
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.RIGHT);
                    if (checkBoundX(Player.instance.getPosX() + PLAYER_MOVE))
                        Player.getInstance().setPosX(Player.instance.getPosX() + PLAYER_MOVE);
                    else
                        Player.getInstance().setPosX(PIXEL * MAP_WIDTH_TILE - PLAYER_WIDTH);
                    break;
                case KeyEvent.VK_DOWN: // down
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.DOWN);
                    if (checkBoundY(Player.instance.getPosY() + PLAYER_MOVE))
                        Player.getInstance().setPosY(Player.instance.getPosY() + PLAYER_MOVE);
                    else
                        Player.getInstance().setPosY(PIXEL * MAP_HEIGHT_TILE - PLAYER_HEIGHT);
                    break;
                case KeyEvent.VK_A: // A (left for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.LEFT);
                    Player2.getInstance().setPosX(Player2.instance.getPosX() - 10);
                    break;
                case KeyEvent.VK_W: // W (up for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.UP);
                    Player2.getInstance().setPosY(Player2.instance.getPosY()-10);
                    break;
                case KeyEvent.VK_D: // D (right for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.RIGHT);
                    Player2.getInstance().setPosX(Player2.instance.getPosX()+10);
                    break;
                case KeyEvent.VK_S: // S (down for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.DOWN);
                    Player2.getInstance().setPosY(Player2.instance.getPosY()+10);
                    break;
                case KeyEvent.VK_SPACE:
//                    Bullet b = new Bullet(Player.getInstance().getPosX(), Player.getInstance().getPosX(), Player.getInstance().getRotation());
//                    gameObjects.add(b);
//                    JOptionPane.showMessageDialog(null, gameObjects.size());
                    break;
            }
        }
        if (Event == KEY_RELEASED)
        {
            Player.getInstance().setState(State.IDLE);
            // player2
            Player2.getInstance().setState(State.IDLE);
        }

    }
}
