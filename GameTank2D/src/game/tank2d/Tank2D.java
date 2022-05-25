package game.tank2d;

import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;
import pkg2dgamesframework.QueueList;

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
    public static final Color BACKGROUND_GAME_COLOR = Color.BLACK;
    public static final int PIXEL = 16;

    static Map maps = new Map();

    Alphabet Al = new Alphabet(TypeOfAlphabet.BSLASH, 16, 16, BRICK_WIDTH, BRICK_HEIGHT);

    Enemy enemy001 = new Enemy(TypeOfEnemy.ENEMY004, 50, 16, ENEMY_WIDTH, ENEMY_HEIGHT, Rotation.DOWN);

    Bullet bullet001 = new Bullet(16, 16, Rotation.DOWN);

    //ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();

    public Tank2D() {
        super(MAP_WIDTH_TILE * PIXEL, MAP_HEIGHT_TILE * PIXEL);

        BeginGame();
    }

    public static void main(String[] args) {

        maps.initBrickMap1();
        mapBrick = maps.getBrickListMap1();
        GameScreen game = new Tank2D();
    }

    public void PAINT_OBJECT(Graphics2D g2, Brick b) {
        b.getAnimation().PaintAnims(b.getPosX(), b.getPosY(), b.getImgBrick(), g2, 0, b.getRotation().getRotate());
    }

    public void PAINT_OBJECT(Graphics2D g2, Alphabet b) {
        b.getAnimation().PaintAnims(b.getPosX(), b.getPosY(), b.getImgAlphabet(), g2, 0, b.getRotation().getRotate());
    }

    boolean checkCollisionWithBrick(Rotation rotation){
        Point p = new Point(0, 0);

        switch (rotation){
            case UP -> {
                p = Player.getInstance().get_Up_Location();
                break;
            }
            case DOWN -> {
                p = Player.getInstance().get_Down_Location();
                break;
            }
            case LEFT -> {
                p = Player.getInstance().get_Left_Location();
                break;
            }
            case RIGHT -> {
                p = Player.getInstance().get_Right_Location();
                break;
            }
        }

        for (int i = 0; i < mapBrick.size(); i++){
            Rectangle brickRectNow = mapBrick.get(i).getRect();
            Rectangle futurePlayerRect = new Rectangle(p.x, p.y, PLAYER_WIDTH, PLAYER_HEIGHT);
            if (brickRectNow.intersects(futurePlayerRect)){
                switch (mapBrick.get(i).getType()){
                    case BRICK001, BRICK002 -> {
                        return false;
                    }
                    default -> {
                        break;
                    }
                }
            }
        }

        return true;
    }
    public void CollisionHandling(){
        // Collision handling: BRICK AND BULLET
        for (int i = 0; i < bulletList.size(); i++){
            for (int j = 0; j < mapBrick.size(); j++){
                Brick brickNow = mapBrick.get(j);
                Rectangle bulletRectNow = bulletList.get(i).getRect();
                Rectangle brickRectNow = brickNow.getRect();
                if (bulletRectNow.intersects(brickRectNow)){
                    boolean needRemoveBullet = false;

                    switch (brickNow.getType()){
                        case BRICK000 -> {
                            break;
                        }
                        case BRICK001 -> {
                            mapBrick.remove(j);
                            needRemoveBullet = true;
                            System.out.println("Va cham brick001 - bullet");
                            break;
                        }
                        case BRICK002 -> {
                            needRemoveBullet = true;
                            System.out.println("Va cham brick002 - bullet");
                            break;
                        }
                        case BRICK003 -> {
                            System.out.println("Va cham brick003 - bullet");
                            break;
                        }
                        case BUSH -> {
                            System.out.println("Va cham brick bush - bullet");
                            break;
                        }
                        case WATER -> {
                            System.out.println("Va cham brick water - bullet");
                            break;
                        }
                    }

                    if (needRemoveBullet){
                        bulletList.remove(i);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        CollisionHandling();

        // player
        Player.getInstance().update(deltaTime);

        // player2
        Player2.getInstance().getAnimation().Update_Me(deltaTime);

        // enemy
        enemy001.update(deltaTime);

        // bullet
        bullet001.update(deltaTime);

        for (int i = 0; i < bulletList.size(); i++){
            bulletList.get(i).update(deltaTime);
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        //g2.setColor(Color.LIGHT_GRAY);
        g2.setColor(BACKGROUND_GAME_COLOR);
        g2.fillRect(0, 0, MAP_WIDTH_TILE * PIXEL, MAP_HEIGHT_TILE * PIXEL);

        Player.getInstance().paint(g2);

        for (int i = 0; i < mapBrick.size(); i++)
        {
            PAINT_OBJECT(g2, mapBrick.get(i));
        }


        //region Test
        // player
        PAINT_OBJECT(g2, Al);
        // player2
        Player2.getInstance().getAnimation().PaintAnims(Player2.getInstance().getPosX(), Player2.getInstance().getPosY(), Player2.getInstance().getImgTanks(), g2, 0, Player2.getInstance().getRotation().getRotate());

        // enemy
        //enemy001.getAnimation().PaintAnims(enemy001.getPosX(), enemy001.getPosY(), enemy001.getImgTanks(), g2, 0, enemy001.getRotation().getRotate());
        enemy001.paint(g2);

        // bullet
        bullet001.getAnimation().PaintAnims(bullet001.getPosX(), bullet001.getPosY(), bullet001.getImg(), g2, 0, bullet001.getRotation().getRotate());

        for (int i = 0; i < bulletList.size(); i++){
            Bullet bulletNow = bulletList.get(i);
            bulletNow.getAnimation().PaintAnims(bulletNow.getPosX(), bulletNow.getPosY(), bulletNow.getImg(), g2, 0, bulletNow.getRotation().getRotate());
        }

        //endregion
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: // left
                    if (checkCollisionWithBrick(Rotation.LEFT)) {
                        Player.getInstance().Move(Rotation.LEFT);
                    }
                    break;
                case KeyEvent.VK_UP: // up
                    if (checkCollisionWithBrick(Rotation.UP)) {
                        Player.getInstance().Move(Rotation.UP);
                    }
                    break;
                case KeyEvent.VK_RIGHT: // right
                    if (checkCollisionWithBrick(Rotation.RIGHT)) {
                        Player.getInstance().Move(Rotation.RIGHT);
                    }
                    break;
                case KeyEvent.VK_DOWN: // down
                    if (checkCollisionWithBrick(Rotation.DOWN)) {
                        Player.getInstance().Move(Rotation.DOWN);
                    }
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
                case KeyEvent.VK_ENTER:
                    bulletList.add(Player.getInstance().createNewBullet());
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
