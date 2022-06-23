package game.tank2d;

import pkg2dgamesframework.GameScreen;
import pkg2dgamesframework.Objects;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static game.tank2d.Player.PLAYER_WIDTH;
import static game.tank2d.Player.PLAYER_HEIGHT;


public class Tank2D extends GameScreen {

    static int CurrentScene = 1;

    static ArrayList<Brick> mapBrick = new ArrayList<Brick>();
    public static final int MAP_WIDTH_TILE = 26;
    public static final int MAP_HEIGHT_TILE = 26;
    public static final int PIXEL = 16;
    static int itemFrame = 0;
    static Map maps = new Map();
    static Eagle eagle;
    static Item item;
    static Random generator = new Random();
    static long startTime = System.currentTimeMillis();
    static boolean activeItem = false;
    static long itemEffectTime;
    static TypeOfItem typeOfItem;
    static boolean gameOver = false;
    static int lifeCount = 3;
    static int score = 6789;

    static Enemy enemy001 = new Enemy(TypeOfEnemy.ENEMY004, PIXEL * 2, PIXEL * 2, Objects.Rotation.DOWN);
    static Enemy enemy002 = new Enemy(TypeOfEnemy.ENEMY004, PIXEL * 4, PIXEL * 2, Objects.Rotation.DOWN);
    static ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    static ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
    //region StartScreen
    StartScreen startScreen = new StartScreen(PIXEL * 4, PIXEL * 8);
    static ArrayList<Alphabet> charList = new ArrayList<Alphabet>();
    static int CursorPosition = 20;
    //endregion

    static ArrayList<Alphabet> UIBar = new ArrayList<Alphabet>();
    public Tank2D() {
        super((MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
        BeginGame();
    }

    static void InitStartScreen() {
        charList.add(new Alphabet(TypeOfAlphabet.H, PIXEL * 12, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.I, PIXEL * 13, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.DASH, PIXEL * 14, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * 17, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * 18, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * 19, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * 20, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * 21, PIXEL * 5));

        charList.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * 14, PIXEL * 20));
        charList.add(new Alphabet(TypeOfAlphabet.P, PIXEL * 16, PIXEL * 20));
        charList.add(new Alphabet(TypeOfAlphabet.L, PIXEL * 17, PIXEL * 20));
        charList.add(new Alphabet(TypeOfAlphabet.A, PIXEL * 18, PIXEL * 20));
        charList.add(new Alphabet(TypeOfAlphabet.Y, PIXEL * 19, PIXEL * 20));
        charList.add(new Alphabet(TypeOfAlphabet.E, PIXEL * 20, PIXEL * 20));
        charList.add(new Alphabet(TypeOfAlphabet.R, PIXEL * 21, PIXEL * 20));

        charList.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * 14, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.P, PIXEL * 16, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.L, PIXEL * 17, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.A, PIXEL * 18, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.Y, PIXEL * 19, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.E, PIXEL * 20, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.R, PIXEL * 21, PIXEL * 22));
        charList.add(new Alphabet(TypeOfAlphabet.S, PIXEL * 22, PIXEL * 22));

        Player.getInstance().setAnimation(100, 0, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        Player.getInstance().setRotation(Objects.Rotation.RIGHT);
        Player.getInstance().setState(Objects.State.RUN);
    }

    static void InitLevel1() {
        eagle = new Eagle(PIXEL * 14 + 1, PIXEL * (30 - 4));
        enemyList.add(enemy001);
        enemyList.add(enemy002);
        maps.initBrickMap1();
        mapBrick = maps.getBrickListMap();
        try {
            Player.getInstance().Reset();
            Player.getInstance().StartShield();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static void InitLevel2() {
        maps.initBrickMap2();
        mapBrick = maps.getBrickListMap();
        try {
            Player.getInstance().Reset();
            Player.getInstance().StartShield();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static  void InitUIBar() {
        UIBar.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * 27, PIXEL * 17));
        UIBar.add(new Alphabet(TypeOfAlphabet.P, PIXEL * 28, PIXEL * 17));
        UIBar.add(new Alphabet(TypeOfAlphabet.ALLY, PIXEL * 27, PIXEL * 18));
        UIBar.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * 28, PIXEL * 18));
    }

    static void InitGameOver() {
        charList.clear();
        charList.add(new Alphabet(TypeOfAlphabet.G, PIXEL * 12, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.A, PIXEL * 13, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.M, PIXEL * 14, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.E, PIXEL * 15, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.O, PIXEL * 17, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.V, PIXEL * 18, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.E, PIXEL * 19, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.R, PIXEL * 20, PIXEL * 14));

        charList.add(new Alphabet(TypeOfAlphabet.S, PIXEL * 11, PIXEL * 16));
        charList.add(new Alphabet(TypeOfAlphabet.C, PIXEL * 12, PIXEL * 16));
        charList.add(new Alphabet(TypeOfAlphabet.O, PIXEL * 13, PIXEL * 16));
        charList.add(new Alphabet(TypeOfAlphabet.R, PIXEL * 14, PIXEL * 16));
        charList.add(new Alphabet(TypeOfAlphabet.E, PIXEL * 15, PIXEL * 16));


        String s = String.valueOf(score);

        int count = 0;
        for (int i = 0; i < 5 - s.length(); i++)
        {
            charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * (17 + count), PIXEL * 16));
            count++;
        }

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '0':
                    charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '1':
                    charList.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '2':
                    charList.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '3':
                    charList.add(new Alphabet(TypeOfAlphabet.THREE, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '4':
                    charList.add(new Alphabet(TypeOfAlphabet.FOUR, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '5':
                    charList.add(new Alphabet(TypeOfAlphabet.FIVE, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '6':
                    charList.add(new Alphabet(TypeOfAlphabet.SIX, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '7':
                    charList.add(new Alphabet(TypeOfAlphabet.SEVEN, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '8':
                    charList.add(new Alphabet(TypeOfAlphabet.EIGHT, PIXEL * (17 + count), PIXEL * 16));
                    break;
                case '9':
                    charList.add(new Alphabet(TypeOfAlphabet.NINE, PIXEL * (17 + count), PIXEL * 16));
                    break;
            }
            count++;
        }
    }

    static void ResetGame() {
        charList.clear();
        bulletList.clear();
        enemyList.clear();
        explosionList.clear();
        CurrentScene = 0;
        item = null;
        eagle = null;
        InitStartScreen();
        gameOver = false;
        lifeCount = 3;
        score = 0;
        startTime = System.currentTimeMillis();
        activeItem = false;
        System.out.println(CurrentScene);
    }

    static void UpgradeWall(boolean isUpgrade) {
        if (isUpgrade) {
            for (int i = 0; i < mapBrick.size(); i++)
            {
                if (Math.abs(mapBrick.get(i).getPosX() - eagle.getPosX()) < PIXEL * 3 && Math.abs(mapBrick.get(i).getPosY() - eagle.getPosY()) < PIXEL * 3) {
                    mapBrick.get(i).setType(TypeOfBrick.BRICK002);
                }
            }
        }
        else {
            for (int i = 0; i < mapBrick.size(); i++)
            {
                if (Math.abs(mapBrick.get(i).getPosX() - eagle.getPosX()) < PIXEL * 3 && Math.abs(mapBrick.get(i).getPosY() - eagle.getPosY()) < PIXEL * 3)
                    mapBrick.get(i).setType(TypeOfBrick.BRICK001);
            }
        }
    }

    public static void main(String[] args) {
        InitStartScreen();
        InitLevel1();
        InitUIBar();
        GameScreen game = new Tank2D();
    }
    public void newExplosion(Bullet bullet){
        explosionList.add(bullet.createNewExplosion());
    }
    public void newExplosion(int x, int y){
        explosionList.add(new Explosion(x, y));
    }

    // Cái này chỉ là kiểm tra xem vị trí TIẾP THEO (trong tương lai) có hợp lệ hay không?
    boolean checkNextPosIsWrong(Objects.Rotation rotation, Objects o){
        // Vị trí tiếp theo trong tương lai
        Point p = o.getNextPos(rotation);
        Rectangle pRect = new Rectangle(p.x, p.y, PLAYER_WIDTH, PLAYER_HEIGHT);

        //region Kiểm tra với brick
        for (int i = 0; i < mapBrick.size(); i++){
            if (mapBrick.get(i).checkCollision(pRect)){
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
        //endregion

        for (int i = 0; i < enemyList.size(); i++) {
            if (enemyList.get(i).checkCollision(pRect))
                return false;
        }


        if (eagle != null)
            if (eagle.checkCollision(pRect))
                return false;
        return true;
    }

    public void CollisionHandling(){
        //region Bullet vs Brick
        for (int i = 0; i < bulletList.size(); i++){
            Bullet bulletNow = bulletList.get(i);
            boolean needRemoveBullet = false;

            for (int j = 0; j < mapBrick.size(); j++){
                Brick brickNow = mapBrick.get(j);
                if (bulletNow.checkCollision(brickNow)){
                    switch (brickNow.getType()){
                        case BRICK000 -> {
                            break;
                        }
                        case BRICK001 -> {
                            brickNow.Destroy();
                            needRemoveBullet = true;
                            System.out.println("Va cham brick001 - bullet");
                            newExplosion(bulletNow);
                            break;
                        }
                        case BRICK002 -> {
                            needRemoveBullet = true;
                            newExplosion(bulletNow);
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
                bulletNow.Destroy();
                break;
            }
        }
        //endregion

        //region Bullet vs Player
        for (int i = 0; i < bulletList.size(); i++){
            Bullet bulletNow = bulletList.get(i);
            // Nếu có va chạm
            if (Player.getInstance().checkCollision(bulletNow)){
                // nếu còn khiên
                if (Player.getInstance().getShield().isActive()){
                    // Do nothing
                } else {
                    Player.getInstance().Destroy();
                    System.out.println("PLAYER DIED, GAME OVER!");
                }

                newExplosion(bulletNow);
                bulletNow.Destroy();
            }
        }
        //endregion

        //region Bullet vs Enemy
        for (int i = 0; i < bulletList.size(); i++){
            Bullet bulletNow = bulletList.get(i);
            for (int j = 0; j < enemyList.size(); j++){
                Enemy enemyNow = enemyList.get(j);
                // Nếu có va chạm
                if (enemyNow.checkCollision(bulletNow)){
                    // Nếu enemy chưa xuất hiện (chỉ hiển thị hiệu ứng)
                    if (enemyNow.getEnemyAppear().isActive){
                        // do nothing
                    } else {
                        enemyNow.Destroy();
                        System.out.println("Enemy detroyed");
                    }

                    newExplosion(bulletNow);
                    bulletNow.Destroy();
                }
            }
        }
        //endregion

        //region Bullet vs Bullet
        for (int i = 0; i < bulletList.size(); i++){
            for (int j = i + 1; j < bulletList.size(); j++){
                if (bulletList.get(i).checkCollision(bulletList.get(j))){
                    bulletList.get(i).Destroy();
                    bulletList.get(j).Destroy();
                    System.out.println("va chạm bullet vs bullet");
                }
            }
        }

        //endregion

        //region Item vs Player
        if (item != null)
            if (Player.getInstance().checkCollision(item)) {
                System.out.println(item.getType());
                switch (item.getType()) {
                    case LIFE -> {
                        lifeCount++;
                        item = null;
                    }
                    case DESTROY -> {
                        for (int i = 0; i < enemyList.size(); i++)
                            enemyList.get(i).Destroy();
                        item = null;
                    }
                    case SHIELD -> {
                        Player.getInstance().StartShield();
                        item = null;
                    }
                    case WALL -> {
                        typeOfItem = TypeOfItem.WALL;
                        activeItem = true;
                        itemEffectTime = System.currentTimeMillis();
                        UpgradeWall(true);
                        item = null;
                    }
                }
            }
        //endregion

        //region Enemy vs Player
//        for (int i = 0; i < enemyList.size(); i++){
//            Enemy enemyNow = enemyList.get(i);
//
//            // nếu có va chạm
//            if (Player.getInstance().checkCollision(enemyNow)){
//                // nếu enemy chưa xuất hiện hoặc Player còn khiên
//                if (enemyNow.getEnemyAppear().isActive || Player.getInstance().getShield().isActive){
//                    // Do nothing
//                } else {
////                    enemyNow.Destroy();
////                    Player.getInstance().Destroy();
////                    System.out.println("GAME OVER");
////
////                    newExplosion(Player.getInstance().getPosX() + PLAYER_WIDTH/2,
////                            Player.getInstance().getPosY() + PLAYER_HEIGHT/2);
////                    newExplosion(enemyNow.getPosX() + Enemy.ENEMY_WIDTH/2,
////                            enemyNow.getPosY() + Enemy.ENEMY_HEIGHT/2);
//
//                    break;
//                }
//            }
//        }
        //endregion

        if (eagle != null)
            for (int i = 0; i < bulletList.size(); i++) {
                if (bulletList.get(i).checkCollision(eagle)) {
                    eagle.setDestroyed();
                    bulletList.get(i).Destroy();
                    newExplosion(bulletList.get(i));
                    InitGameOver();
                    gameOver = true;
                    System.out.println("Game Over");
                    return;
                }
            }
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        if (gameOver) return;
        CollisionHandling();
        // player
        Player.getInstance().Update(deltaTime);

        for (int i = 0; i < UIBar.size(); i++) {
            UIBar.get(i).Update(deltaTime);
        }

        if (eagle != null) {
            eagle.Update(deltaTime);
        }

        if (item != null) {
            item.Update(deltaTime);
        }

        if (activeItem) {
            if (System.currentTimeMillis() - itemEffectTime > 8000)
            {
                activeItem = false;
                UpgradeWall(false);
            }
        }

        if (bulletList.size() != 0){
            for (int i = 0; i < bulletList.size(); i++){
                bulletList.get(i).Update(deltaTime);
            }
        }

        for (int i = 0; i < explosionList.size(); i++){
            explosionList.get(i).Update(deltaTime);
        }

        for (int i = 0; i < enemyList.size(); i++){
            enemyList.get(i).Update(deltaTime);
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        //region StartScreen
        if (CurrentScene == 0) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, (MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
            for (int i = 0; i < charList.size(); i++) {
                charList.get(i).Paint(g2);
            }
            startScreen.Paint(g2);

            Player.getInstance().setPos(PIXEL * 11, PIXEL * CursorPosition - 8);
            Player.getInstance().Paint(g2);
        }
        //endregion
        //region Level1
        if (CurrentScene != 0 && !gameOver) {
            g2.setColor(Color.GRAY);
            g2.fillRect(0, 0, (MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
            g2.setColor(Color.BLACK);
            g2.fillRect(32, 32, (MAP_WIDTH_TILE + 0) * PIXEL, (MAP_HEIGHT_TILE + 0) * PIXEL);
            Player.getInstance().Paint(g2);
            for (int i = 0; i < UIBar.size(); i++) {
                UIBar.get(i).Paint(g2);
            }
            if (eagle != null) {
                    eagle.Paint(g2);
            }

            for (int i = 0; i < mapBrick.size(); i++) {
                mapBrick.get(i).Paint(g2);
            }
            for (int i = 0; i < bulletList.size(); i++){
                bulletList.get(i).Paint(g2);
            }
            for (int i = 0; i < explosionList.size(); i++){
                explosionList.get(i).Paint(g2);
            }
            for (int i = 0; i < enemyList.size(); i++){
                enemyList.get(i).Paint(g2);
            }
            enemy001.Paint(g2);
            enemy002.Paint(g2);
        }
        //endregion
        if (gameOver) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, (MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
            for (int i = 0; i < charList.size(); i++)
                charList.get(i).Paint(g2);
        } else if (CurrentScene != 0) {
            //region item generate
            if (System.currentTimeMillis() - startTime > 20000) {
                int type = generator.nextInt(3, 4);
                int posX = generator.nextInt(2, 27);
                int posY = generator.nextInt(2, 27);
                System.out.println(type + " " + posX + " " + posY);
                switch (type) {
                    case 0:
                        item = new Item(TypeOfItem.LIFE, posX * PIXEL, posY * PIXEL);
                        break;
                    case 1:
                        item = new Item(TypeOfItem.DESTROY, posX * PIXEL, posY * PIXEL);
                        break;
                    case 2:
                        item = new Item(TypeOfItem.SHIELD, posX * PIXEL, posY * PIXEL);
                        break;
                    case 3:
                        item = new Item(TypeOfItem.WALL, posX * PIXEL, posY * PIXEL);
                        break;
                }
                startTime = System.currentTimeMillis();
            }
            if (item != null) {
                //item blink
                if (itemFrame < 16)
                    item.Paint(g2);
                if (itemFrame > 32)
                    itemFrame = 0;
                itemFrame++;
            }
            if ( item != null && System.currentTimeMillis() - startTime > 8000) {
                item = null;
                startTime = System.currentTimeMillis();
            }
            //endregion
        }
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            if (CurrentScene != 0) {
                if (!gameOver) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT: // left
                            if (Player.getInstance().getRotation() != Objects.Rotation.LEFT){
                                Player.getInstance().setRotation(Objects.Rotation.LEFT);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.LEFT, Player.getInstance())) {
                                Player.getInstance().Move(Objects.Rotation.LEFT);
                            }
                            break;
                        case KeyEvent.VK_UP: // up
                            if (Player.getInstance().getRotation() != Objects.Rotation.UP){
                                Player.getInstance().setRotation(Objects.Rotation.UP);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.UP, Player.getInstance())) {
                                Player.getInstance().Move(Objects.Rotation.UP);
                            }
                            break;
                        case KeyEvent.VK_RIGHT: // right
                            if (Player.getInstance().getRotation() != Objects.Rotation.RIGHT){
                                Player.getInstance().setRotation(Objects.Rotation.RIGHT);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.RIGHT, Player.getInstance())) {
                                Player.getInstance().Move(Objects.Rotation.RIGHT);
                            }
                            break;
                        case KeyEvent.VK_DOWN: // down
                            if (Player.getInstance().getRotation() != Objects.Rotation.DOWN){
                                Player.getInstance().setRotation(Objects.Rotation.DOWN);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.DOWN, Player.getInstance())) {
                                Player.getInstance().Move(Objects.Rotation.DOWN);
                            }
                            break;
                        case KeyEvent.VK_A: // A (left for player 2)

                            break;
                        case KeyEvent.VK_W: // W (up for player 2)

                            break;
                        case KeyEvent.VK_D: // D (right for player 2)

                            break;
                        case KeyEvent.VK_S: // S (down for player 2)

                            break;
                        case KeyEvent.VK_SPACE:
//                        CurrentScene++;
//                        InitLevel2();
                            break;
                        case KeyEvent.VK_ENTER:
                            bulletList.add(Player.getInstance().createNewBullet());
                            break;
                    }
                } else {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                ResetGame();
                                break;
                        }
                    }
            }
            else {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP: // up
                            if (CursorPosition == 22)
                                CursorPosition-= 2;
                            break;
                        case KeyEvent.VK_DOWN: // down
                            if (CursorPosition == 20)
                                CursorPosition+= 2;
                            break;
                        case KeyEvent.VK_ENTER:
                            CurrentScene++;
                            InitLevel1();
                            break;
                    }
            }
        }
        if (Event == KEY_RELEASED) {
            if (CurrentScene != 0 && !gameOver)
            {
                Player.getInstance().setState(Objects.State.IDLE);
                // player2
                //Player2.getInstance().setState(Objects.State.IDLE);
            }
        }
    }
}
