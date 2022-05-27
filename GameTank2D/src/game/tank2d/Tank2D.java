package game.tank2d;

import pkg2dgamesframework.GameScreen;
import pkg2dgamesframework.Objects;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static game.tank2d.Brick.BRICK_HEIGHT;
import static game.tank2d.Brick.BRICK_WIDTH;
import static game.tank2d.Player.PLAYER_WIDTH;
import static game.tank2d.Player.PLAYER_HEIGHT;


public class Tank2D extends GameScreen {

    static ArrayList<Brick> mapBrick = new ArrayList<Brick>();
    public static final int MAP_WIDTH_TILE = 26;
    public static final int MAP_HEIGHT_TILE = 26;
    public static final Color BACKGROUND_GAME_COLOR = Color.BLACK;
    public static final int PIXEL = 16;

    static Map maps = new Map();

    Alphabet Al = new Alphabet(TypeOfAlphabet.BSLASH, 16, 16, BRICK_WIDTH, BRICK_HEIGHT);
    Enemy enemy001 = new Enemy(TypeOfEnemy.ENEMY004, 50, 16, Objects.Rotation.DOWN);
    Bullet bullet001 = new Bullet(16, 16, Objects.Rotation.DOWN);
    //ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    ArrayList<Explosion> explosionList = new ArrayList<Explosion>();

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
        b.getAnimation().PaintAnims(b.getPosX(), b.getPosY(), b.getImage(), g2, 0, b.getRotation().getRotate());
    }

    public void PAINT_OBJECT(Graphics2D g2, Alphabet b) {
        b.getAnimation().PaintAnims(b.getPosX(), b.getPosY(), b.getImage(), g2, 0, b.getRotation().getRotate());
    }
    public void newExposion(Bullet bullet){
        explosionList.add(bullet.createNewExplosion());
    }
    boolean checkCollisionWithBrick(Objects.Rotation rotation){
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

            Bullet bulletNow = bulletList.get(i);
            Rectangle bulletRectNow = bulletNow.getRect();

            boolean needRemoveBullet = false;

            for (int j = 0; j < mapBrick.size(); j++){

                Brick brickNow = mapBrick.get(j);
                Rectangle brickRectNow = brickNow.getRect();

                if (bulletRectNow.intersects(brickRectNow)){

                    switch (brickNow.getType()){
                        case BRICK000 -> {
                            break;
                        }
                        case BRICK001 -> {
                            mapBrick.remove(j);
                            needRemoveBullet = true;
                            System.out.println("Va cham brick001 - bullet");

                            newExposion(bulletNow);

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
                }
            }

            if (needRemoveBullet){
                bulletList.remove(i);
                break;
            }
        }
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        CollisionHandling();


        // player
        Player.getInstance().Update(deltaTime);

        // player2

        // enemy
        enemy001.update(deltaTime);

        // bullet
        bullet001.Update(deltaTime);

        for (int i = 0; i < bulletList.size(); i++){
            bulletList.get(i).Update(deltaTime);
        }

        for (int i = 0; i < explosionList.size(); i++){
            explosionList.get(i).Update(deltaTime);
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        g2.setColor(BACKGROUND_GAME_COLOR);
        g2.fillRect(0, 0, MAP_WIDTH_TILE * PIXEL, MAP_HEIGHT_TILE * PIXEL);

        Player.getInstance().Paint(g2);

        for (int i = 0; i < mapBrick.size(); i++)
        {
            mapBrick.get(i).Paint(g2);
        }


        //region Test
        // player
        PAINT_OBJECT(g2, Al);

        // enemy
        enemy001.Paint(g2);

        // bullet
        bullet001.Paint(g2);

        for (int i = 0; i < bulletList.size(); i++){
            Bullet bulletNow = bulletList.get(i);
            bulletNow.Paint(g2);
        }

        for (int i = 0; i < explosionList.size(); i++){
            explosionList.get(i).Paint(g2);
        }
        //endregion
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: // left
                    if (Player.getInstance().getRotation() != Objects.Rotation.LEFT){
                        Player.getInstance().setRotation(Objects.Rotation.LEFT);
                    }

                    if (checkCollisionWithBrick(Objects.Rotation.LEFT)) {
                        Player.getInstance().Move(Objects.Rotation.LEFT);
                    }
                    break;
                case KeyEvent.VK_UP: // up
                    if (Player.getInstance().getRotation() != Objects.Rotation.UP){
                        Player.getInstance().setRotation(Objects.Rotation.UP);
                    }

                    if (checkCollisionWithBrick(Objects.Rotation.UP)) {
                        Player.getInstance().Move(Objects.Rotation.UP);
                    }
                    break;
                case KeyEvent.VK_RIGHT: // right
                    if (Player.getInstance().getRotation() != Objects.Rotation.RIGHT){
                        Player.getInstance().setRotation(Objects.Rotation.RIGHT);
                    }

                    if (checkCollisionWithBrick(Objects.Rotation.RIGHT)) {
                        Player.getInstance().Move(Objects.Rotation.RIGHT);
                    }
                    break;
                case KeyEvent.VK_DOWN: // down
                    if (Player.getInstance().getRotation() != Objects.Rotation.DOWN){
                        Player.getInstance().setRotation(Objects.Rotation.DOWN);
                    }

                    if (checkCollisionWithBrick(Objects.Rotation.DOWN)) {
                        Player.getInstance().Move(Objects.Rotation.DOWN);
                    }
                    break;
                case KeyEvent.VK_A: // A (left for player 2)
//                    Player2.getInstance().setState(Objects.State.RUN);
//                    Player2.getInstance().setRotation(Objects.Rotation.LEFT);
//                    Player2.getInstance().setPosX(Player2.instance.getPosX() - 10);
                    break;
                case KeyEvent.VK_W: // W (up for player 2)
//                    Player2.getInstance().setState(Objects.State.RUN);
//                    Player2.getInstance().setRotation(Objects.Rotation.UP);
//                    Player2.getInstance().setPosY(Player2.instance.getPosY()-10);
                    break;
                case KeyEvent.VK_D: // D (right for player 2)
//                    Player2.getInstance().setState(Objects.State.RUN);
//                    Player2.getInstance().setRotation(Objects.Rotation.RIGHT);
//                    Player2.getInstance().setPosX(Player2.instance.getPosX()+10);
                    break;
                case KeyEvent.VK_S: // S (down for player 2)
//                    Player2.getInstance().setState(Objects.State.RUN);
//                    Player2.getInstance().setRotation(Objects.Rotation.DOWN);
//                    Player2.getInstance().setPosY(Player2.instance.getPosY()+10);
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
            Player.getInstance().setState(Objects.State.IDLE);
            // player2
            //Player2.getInstance().setState(Objects.State.IDLE);
        }

    }
}
