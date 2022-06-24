package game.tank2d;

import pkg2dgamesframework.GameScreen;
import pkg2dgamesframework.Objects;


import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;

import static game.tank2d.Player.PLAYER_WIDTH;
import static game.tank2d.Player.PLAYER_HEIGHT;


public class Tank2D extends GameScreen {

    static int CurrentScene = 0;

    static ArrayList<Brick> mapBrick = new ArrayList<Brick>();
    public static final int MAP_WIDTH_TILE = 26;
    public static final int MAP_HEIGHT_TILE = 26;
    public static final int PIXEL = 16;
    public static final int ENEMY_NUMBER = 20;
    static int itemFrame = 0;
    static Map maps = new Map();
    static Eagle eagle;
    static Item item;
    static Random generator = new Random();
    static long startTime = 0;
    static long endTime;
    static boolean activeItem = false;
    static long itemEffectTime;
    static TypeOfItem typeOfItem;
    static boolean gameOver = false;
    static boolean endScene = false;
    static int lifeCount = 3;
    static int lifeCount2 = 3;
    static int score = 0;
    static boolean twoPlayersMode = false;
    static int numberEnemyToSpawn = ENEMY_NUMBER;
    static long timeToSpawnEnemy = 0;
    static boolean nextLevel = false;
    static boolean endGame = false;

    static String StartSound = "Start.wav";
    static String GetItemSound = "GetItem.wav";
    static String GameOverSound = "GameOver.wav";
    static String FireSound = "Fire.wav";
    static String ExplosionSound = "Explosion.wav";
    static String NextLevelSound = "NextLevel.wav";
    static String EndGameSound = "EndGame.wav";
    static String KeyPressSound = "Click.wav";


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
    public static void main(String[] args) {
        InitStartScreen();
        GameScreen game = new Tank2D();
    }

    static void PlaySound(String path) {
        Clip clip = null;
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
    static void InitStartScreen() {
        PlaySound(StartSound);
        charList.add(new Alphabet(TypeOfAlphabet.H, PIXEL * 12, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.I, PIXEL * 13, PIXEL * 5));
        charList.add(new Alphabet(TypeOfAlphabet.DASH, PIXEL * 14, PIXEL * 5));

        int highScore = 0;
        highScore = GetHighScore();
        String s = String.valueOf(highScore);

        int count = 0;
        for (int i = 0; i < 5 - s.length(); i++)
        {
            charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * (17 + count), PIXEL * 5));
            count++;
        }

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '0':
                    charList.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '1':
                    charList.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '2':
                    charList.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '3':
                    charList.add(new Alphabet(TypeOfAlphabet.THREE, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '4':
                    charList.add(new Alphabet(TypeOfAlphabet.FOUR, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '5':
                    charList.add(new Alphabet(TypeOfAlphabet.FIVE, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '6':
                    charList.add(new Alphabet(TypeOfAlphabet.SIX, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '7':
                    charList.add(new Alphabet(TypeOfAlphabet.SEVEN, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '8':
                    charList.add(new Alphabet(TypeOfAlphabet.EIGHT, PIXEL * (17 + count), PIXEL * 5));
                    break;
                case '9':
                    charList.add(new Alphabet(TypeOfAlphabet.NINE, PIXEL * (17 + count), PIXEL * 5));
                    break;
            }
            count++;
        }

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
        Player.getInstance().isDestroyAlready = false;
        Player2.getInstance().isDestroyAlready = false;
    }

    static void InitLevel1() {
        nextLevel = false;
        mapBrick.clear();
        mapBrick.clear();
        enemyList.clear();
        bulletList.clear();
        explosionList.clear();
        numberEnemyToSpawn = ENEMY_NUMBER;
        eagle = new Eagle(PIXEL * 14 + 1, PIXEL * (30 - 4), false);
        maps.initBrickMap1();
        mapBrick = maps.getBrickListMap();
        if (!twoPlayersMode) lifeCount2 = 0;
        try {
            Player.getInstance().Reset();
            Player.getInstance().StartShield();
        } catch (IOException e){
            e.printStackTrace();
        }
        if (twoPlayersMode) {
            try {
                Player2.getInstance().Reset();
                Player2.getInstance().StartShield();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static void InitLevel2() {
        PlaySound(NextLevelSound);
        mapBrick.clear();
        enemyList.clear();
        bulletList.clear();
        explosionList.clear();
        numberEnemyToSpawn = ENEMY_NUMBER;
        eagle = new Eagle(PIXEL * 14 + 1, PIXEL * (30 - 4), false);
        maps.initBrickMap2();
        mapBrick = maps.getBrickListMap();
        try {
            Player.getInstance().Reset();
            Player.getInstance().StartShield();
        } catch (IOException e){
            e.printStackTrace();
        }
        if (twoPlayersMode) {
            try {
                Player2.getInstance().Reset();
                Player2.getInstance().StartShield();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    static  void InitUIBar() {
        UIBar.clear();
        UIBar.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * 29, PIXEL * 17));
        UIBar.add(new Alphabet(TypeOfAlphabet.P, PIXEL * 30, PIXEL * 17));
        UIBar.add(new Alphabet(TypeOfAlphabet.ALLY, PIXEL * 29, PIXEL * 18));
        UIBar.add(new Alphabet(TypeOfAlphabet.ALLY, PIXEL * 29, PIXEL * 18));

        String s = String.valueOf(lifeCount);
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '0':
                    UIBar.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '1':
                    UIBar.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '2':
                    UIBar.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '3':
                    UIBar.add(new Alphabet(TypeOfAlphabet.THREE, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '4':
                    UIBar.add(new Alphabet(TypeOfAlphabet.FOUR, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '5':
                    UIBar.add(new Alphabet(TypeOfAlphabet.FIVE, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '6':
                    UIBar.add(new Alphabet(TypeOfAlphabet.SIX, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '7':
                    UIBar.add(new Alphabet(TypeOfAlphabet.SEVEN, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '8':
                    UIBar.add(new Alphabet(TypeOfAlphabet.EIGHT, PIXEL * (30 + count), PIXEL * 18));
                    break;
                case '9':
                    UIBar.add(new Alphabet(TypeOfAlphabet.NINE, PIXEL * (30 + count), PIXEL * 18));
                    break;
            }
            count++;
        }

        for (int i = 0; i < numberEnemyToSpawn - 1; i++) {
            if (i % 2 == 0) {
                UIBar.add(new Alphabet(TypeOfAlphabet.ENEMY, PIXEL * 29, PIXEL * (3 + i/2)));
            } else {
                UIBar.add(new Alphabet(TypeOfAlphabet.ENEMY, PIXEL * 30, PIXEL * (3 + i/2)));
            }
        }

        if (twoPlayersMode) {
            UIBar.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * 29, PIXEL * 20));
            UIBar.add(new Alphabet(TypeOfAlphabet.P, PIXEL * 30, PIXEL * 20));
            UIBar.add(new Alphabet(TypeOfAlphabet.ALLY, PIXEL * 29, PIXEL * 21));

            s = String.valueOf(lifeCount2);
            count = 0;
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case '0':
                        UIBar.add(new Alphabet(TypeOfAlphabet.ZERO, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '1':
                        UIBar.add(new Alphabet(TypeOfAlphabet.ONE, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '2':
                        UIBar.add(new Alphabet(TypeOfAlphabet.TWO, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '3':
                        UIBar.add(new Alphabet(TypeOfAlphabet.THREE, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '4':
                        UIBar.add(new Alphabet(TypeOfAlphabet.FOUR, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '5':
                        UIBar.add(new Alphabet(TypeOfAlphabet.FIVE, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '6':
                        UIBar.add(new Alphabet(TypeOfAlphabet.SIX, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '7':
                        UIBar.add(new Alphabet(TypeOfAlphabet.SEVEN, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '8':
                        UIBar.add(new Alphabet(TypeOfAlphabet.EIGHT, PIXEL * (30 + count), PIXEL * 21));
                        break;
                    case '9':
                        UIBar.add(new Alphabet(TypeOfAlphabet.NINE, PIXEL * (30 + count), PIXEL * 21));
                        break;
                }
                count++;
            }
        }
    }

    static void InitGameOver() {
        SaveScore();
        PlaySound(GameOverSound);
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

    static  void InitEndGame() {
        PlaySound(EndGameSound);
        SaveScore();
        charList.clear();
        charList.add(new Alphabet(TypeOfAlphabet.Y, PIXEL * 13, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.O, PIXEL * 14, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.U, PIXEL * 15, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.W, PIXEL * 17, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.I, PIXEL * 18, PIXEL * 14));
        charList.add(new Alphabet(TypeOfAlphabet.N, PIXEL * 19, PIXEL * 14));

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
        PlaySound(StartSound);
        charList.clear();
        bulletList.clear();
        enemyList.clear();
        explosionList.clear();
        CurrentScene = 0;
        item = null;
        eagle = null;
        InitStartScreen();
        gameOver = false;
        endScene = false;
        lifeCount = 3;
        lifeCount2 = 3;
        score = 0;
        startTime = 0;
        timeToSpawnEnemy = 0;
        activeItem = false;
        endGame = false;
        nextLevel = false;
    }

    static int GetHighScore(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("highScore.txt"));
            String line = reader.readLine();
            if (line != null)
            {
                try {
                    return Integer.parseInt(line.trim());
                } catch (NumberFormatException e1) {
                }
            }
            reader.close();

        } catch (IOException ex) {
            return 0;
        }
        return 0;
    }

    static void SaveScore() {
        if (GetHighScore() < score)
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter("highScore.txt", false));
            output.write(String.valueOf(score));
            output.close();

        } catch (IOException ex1) {
        }
    }

    static void UpgradeWall(boolean isUpgrade) {
        if (isUpgrade) {
            for (int i = 0; i < mapBrick.size(); i++)
            {
                Brick current = mapBrick.get(i);

                if (Math.abs(current.getPosX() - eagle.getPosX()) < PIXEL * 3 && Math.abs(current.getPosY() - eagle.getPosY()) < PIXEL * 3 && !current.isDestroyAlready) {
                    mapBrick.set(i, new Brick(TypeOfBrick.BRICK002, current.getPosX(), current.getPosY(), PIXEL, PIXEL));
                }
            }
        }
        else {
            for (int i = 0; i < mapBrick.size(); i++)
            {
                Brick current = mapBrick.get(i);
                if (Math.abs(current.getPosX() - eagle.getPosX()) < PIXEL * 3 && Math.abs(current.getPosY() - eagle.getPosY()) < PIXEL * 3 && !current.isDestroyAlready)
                    mapBrick.set(i, new Brick(TypeOfBrick.BRICK001, current.getPosX(), current.getPosY(), PIXEL, PIXEL));
            }
        }
    }

    static void CheckNextLevel() {
        System.out.println("enemyListSize: " + enemyList.size());
        if (numberEnemyToSpawn == 0)
        for (int i = 0; i < enemyList.size(); i++) {
            if (enemyList.get(i).isDestroyAlready == false)
                return;
        } else return;
        if (!nextLevel) {
            nextLevel = true;
            InitLevel2();
        }
        else {
            InitEndGame();
            endGame = true;
            endTime = System.currentTimeMillis();
        }
        System.out.println(nextLevel);
    }

    public void newExplosion(Bullet bullet){
        explosionList.add(bullet.createNewExplosion());
        PlaySound(ExplosionSound);
    }
    public void newExplosion(int x, int y){
        explosionList.add(new Explosion(x, y));
        PlaySound(ExplosionSound);
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

        //region vs Enemy
        for (int i = 0; i < enemyList.size(); i++) {
            if (enemyList.get(i).checkCollision(pRect))
                return false;
        }
        //endregion

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
            if (bulletNow.getType() == 0) {
                if (Player.getInstance().checkCollision(bulletNow)){
                    // nếu còn khiên
                    if (Player.getInstance().getShield().isActive()){
                        // Do nothing
                    } else {
                        Player.getInstance().Destroy();
                        lifeCount--;
                        if (lifeCount < 1 && lifeCount2 < 1) {
                            InitGameOver();
                            InitUIBar();
                            gameOver = true;
                            endTime = System.currentTimeMillis();
                        }
                        else if (lifeCount > 0){
                            InitUIBar();
                            try {
                                Player.getInstance().Reset();
                            } catch (Exception e) {

                            }
                        }
                    }

                    newExplosion(bulletNow);
                    bulletNow.Destroy();
                }
                if (twoPlayersMode)
                if (Player2.getInstance().checkCollision(bulletNow)){
                    // nếu còn khiên
                    if (Player2.getInstance().getShield().isActive()){
                        // Do nothing
                    } else {
                        Player2.getInstance().Destroy();
                        lifeCount2--;
                        if (lifeCount2 < 1 && lifeCount < 1) {
                            InitGameOver();
                            InitUIBar();
                            gameOver = true;
                            endTime = System.currentTimeMillis();
                        }
                        else if (lifeCount2 > 0){
                            InitUIBar();
                            try {
                                Player2.getInstance().Reset();
                            } catch (Exception e) {

                            }
                        }
                    }

                    newExplosion(bulletNow);
                    bulletNow.Destroy();
                }
            }

        }
        //endregion

        //region Bullet vs Enemy
        for (int i = 0; i < bulletList.size(); i++){
            Bullet bulletNow = bulletList.get(i);
            if (bulletNow.getType() == 1)
            for (int j = 0; j < enemyList.size(); j++){
                Enemy enemyNow = enemyList.get(j);
                // Nếu có va chạm
                if (enemyNow.checkCollision(bulletNow)){
                    // Nếu enemy chưa xuất hiện (chỉ hiển thị hiệu ứng)
                    if (enemyNow.getEnemyAppear().isActive){
                        // do nothing
                    } else {
                        switch (enemyNow.getType()) {
                            case ENEMY001 -> {
                                score += 100;
                                break;
                            }
                            case ENEMY002 -> {
                                score += 200;
                                break;
                            }
                            case ENEMY003 ->  {
                                score += 300;
                            }
                            case ENEMY004 -> {
                                score += 400;
                            }
                        }
                        enemyNow.Destroy();
                        System.out.println("Enemy detroyed");
                    }

                    newExplosion(bulletNow);
                    bulletNow.Destroy();
                    CheckNextLevel();
                }
            }
        }
        //endregion

        //region Bullet vs Bullet
        for (int i = 0; i < bulletList.size(); i++){
            for (int j = i + 1; j < bulletList.size(); j++){
                if (bulletList.get(i).getType() != bulletList.get(j).getType() && bulletList.get(i).checkCollision(bulletList.get(j))){
                    bulletList.get(i).Destroy();
                    bulletList.get(j).Destroy();
                    System.out.println("va chạm bullet vs bullet");
                }
            }
        }

        //endregion

        //region Item vs Player
        if (item != null) {
            if (Player.getInstance().checkCollision(item)) {
                System.out.println(item.getType());
                score += 500;
                PlaySound(GetItemSound);
                switch (item.getType()) {
                    case LIFE -> {
                        lifeCount++;
                        InitUIBar();
                        item = null;
                    }
                    case DESTROY -> {
                        for (int i = 0; i < enemyList.size(); i++) {
                            Enemy enemyNow = enemyList.get(i);
                            if (!enemyNow.isDestroyAlready) {
                                switch (enemyNow.getType()) {
                                    case ENEMY001 -> {
                                        score += 100;
                                        break;
                                    }
                                    case ENEMY002 -> {
                                        score += 200;
                                        break;
                                    }
                                    case ENEMY003 ->  {
                                        score += 300;
                                    }
                                    case ENEMY004 -> {
                                        score += 400;
                                    }
                                }
                                enemyNow.Destroy();
                                newExplosion(enemyNow.getPosX() + PIXEL, enemyNow.getPosY() + PIXEL);
                            }
                        }
                        CheckNextLevel();
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
            } else
            if (Player2.getInstance().checkCollision(item)) {
                System.out.println(item.getType());
                score += 500;
                PlaySound(GetItemSound);
                switch (item.getType()) {
                    case LIFE -> {
                        lifeCount2++;
                        InitUIBar();
                        item = null;
                    }
                    case DESTROY -> {
                        for (int i = 0; i < enemyList.size(); i++) {
                            Enemy enemyNow = enemyList.get(i);
                            if (!enemyNow.isDestroyAlready) {
                                switch (enemyNow.getType()) {
                                    case ENEMY001 -> {
                                        score += 100;
                                        break;
                                    }
                                    case ENEMY002 -> {
                                        score += 200;
                                        break;
                                    }
                                    case ENEMY003 ->  {
                                        score += 300;
                                    }
                                    case ENEMY004 -> {
                                        score += 400;
                                    }
                                }
                            }
                            enemyNow.Destroy();
                            newExplosion(enemyNow.getPosX() + PIXEL, enemyNow.getPosY() + PIXEL);
                            CheckNextLevel();
                        }
                        item = null;
                    }
                    case SHIELD -> {
                        Player2.getInstance().StartShield();
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
        }

        //endregion

        if (eagle != null)
            for (int i = 0; i < bulletList.size(); i++) {
                if (bulletList.get(i).checkCollision(eagle)) {
                    eagle = new Eagle(PIXEL * 14 + 1, PIXEL * (30 - 4), true);
                    bulletList.get(i).Destroy();
                    newExplosion(bulletList.get(i));
                    InitGameOver();
                    gameOver = true;
                    endTime = System.currentTimeMillis();
                    System.out.println("Game Over");
                    return;
                }
            }
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        if (gameOver || endGame)
            if (System.currentTimeMillis() - endTime > 2000) return;
        CollisionHandling();
        // player
        Player.getInstance().Update(deltaTime);
        if (twoPlayersMode)
            Player2.getInstance().Update(deltaTime);
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
                System.out.println("End effect");
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

        for (int i = 0; i < mapBrick.size(); i++){
            mapBrick.get(i).Update(deltaTime);
        }
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        if (endGame) {
            if (System.currentTimeMillis() - endTime > 2000) {
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, (MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
                for (int i = 0; i < charList.size(); i++)
                    charList.get(i).Paint(g2);
                return;
            }
        }
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
        if (CurrentScene != 0 && !endScene) {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, (MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
            g2.setColor(Color.BLACK);
            g2.fillRect(32, 32, (MAP_WIDTH_TILE + 0) * PIXEL, (MAP_HEIGHT_TILE + 0) * PIXEL);
            Player.getInstance().Paint(g2);
            if (twoPlayersMode)
                Player2.getInstance().Paint(g2);
            for (int i = 0; i < UIBar.size(); i++) {
                UIBar.get(i).Paint(g2);
            }
            if (eagle != null) {
                    eagle.Paint(g2);
            }

            for (int i = 0; i < enemyList.size(); i++){
                enemyList.get(i).Paint(g2);
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

        }
        //endregion
        if (gameOver) {
            if (System.currentTimeMillis() - endTime > 3000) endScene = true;
            if (endScene) {
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, (MAP_WIDTH_TILE + 6) * PIXEL, (MAP_HEIGHT_TILE + 4) * PIXEL);
                for (int i = 0; i < charList.size(); i++)
                    charList.get(i).Paint(g2);
            }
        } else if (CurrentScene != 0) {
            //region item generate
            if (startTime == 0) startTime = System.currentTimeMillis();
            if (System.currentTimeMillis() - startTime > 20000) {
                int type = generator.nextInt(0, 4);
                int posX = generator.nextInt(2, 27);
                int posY = generator.nextInt(2, 27);
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

            //region spawn enemy
            if (timeToSpawnEnemy == 0) timeToSpawnEnemy = System.currentTimeMillis();
            if (System.currentTimeMillis() - timeToSpawnEnemy > 4000 && numberEnemyToSpawn > 0) {
                int type = generator.nextInt(0, 4);
                int posX = generator.nextInt(2, 27);
                int posY = 2;
                if (posX % 2 == 1) posX++;
                if (posX % 4 == 0) posX += 2;
                if (posX > 26) posX -= 4;
                System.out.println(type + " " + posX + " " + posY);
                Enemy e;
                boolean canSpawn = true;
                switch (type) {
                    case 0:
                        e = new Enemy(TypeOfEnemy.ENEMY001, posX * PIXEL, posY * PIXEL, Objects.Rotation.DOWN);
                        break;
                    case 1:
                        e = new Enemy(TypeOfEnemy.ENEMY002, posX * PIXEL, posY * PIXEL, Objects.Rotation.DOWN);
                        break;
                    case 2:
                        e = new Enemy(TypeOfEnemy.ENEMY003, posX * PIXEL, posY * PIXEL, Objects.Rotation.DOWN);
                        break;
                    case 3:
                        e = new Enemy(TypeOfEnemy.ENEMY004, posX * PIXEL, posY * PIXEL, Objects.Rotation.DOWN);
                        break;
                    default:
                        e = new Enemy(TypeOfEnemy.ENEMY001, posX * PIXEL, posY * PIXEL, Objects.Rotation.DOWN);
                        break;
                }
                for (int i = 0; i < enemyList.size(); i++) {
                    if (enemyList.get(i).checkCollision(e))
                        canSpawn = false;
                }
                if (e.checkCollision(Player.getInstance()))
                    canSpawn = false;
                if (twoPlayersMode)
                if (e.checkCollision(Player2.getInstance()))
                    canSpawn = false;
                if (canSpawn) {
                    InitUIBar();
                    numberEnemyToSpawn--;
                    enemyList.add(e);
                    timeToSpawnEnemy = System.currentTimeMillis();
                }
            }
            //endregion
        }
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            if (endGame) {
                PlaySound(KeyPressSound);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        ResetGame();
                        break;
                }
                return;
            }
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
                            if (Player2.getInstance().getRotation() != Objects.Rotation.LEFT){
                                Player2.getInstance().setRotation(Objects.Rotation.LEFT);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.LEFT, Player2.getInstance())) {
                                Player2.getInstance().Move(Objects.Rotation.LEFT);
                            }
                            break;
                        case KeyEvent.VK_W: // W (up for player 2)
                            if (Player2.getInstance().getRotation() != Objects.Rotation.UP){
                                Player2.getInstance().setRotation(Objects.Rotation.UP);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.UP, Player2.getInstance())) {
                                Player2.getInstance().Move(Objects.Rotation.UP);
                            }
                            break;
                        case KeyEvent.VK_D: // D (right for player 2)
                            if (Player2.getInstance().getRotation() != Objects.Rotation.RIGHT){
                                Player2.getInstance().setRotation(Objects.Rotation.RIGHT);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.RIGHT, Player2.getInstance())) {
                                Player2.getInstance().Move(Objects.Rotation.RIGHT);
                            }
                            break;
                        case KeyEvent.VK_S: // S (down for player 2)
                            if (Player2.getInstance().getRotation() != Objects.Rotation.DOWN){
                                Player2.getInstance().setRotation(Objects.Rotation.DOWN);
                            }

                            if (checkNextPosIsWrong(Objects.Rotation.DOWN, Player2.getInstance())) {
                                Player2.getInstance().Move(Objects.Rotation.DOWN);
                            }
                            break;
                        case KeyEvent.VK_SPACE:
                            bulletList.add(Player2.getInstance().createNewBullet());
                            break;
                        case KeyEvent.VK_ENTER:
                            bulletList.add(Player.getInstance().createNewBullet());
                            break;
                    }
                } else {
                        PlaySound(KeyPressSound);
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_ENTER:
                                ResetGame();
                                break;
                        }
                    }
            }
            else {
                    PlaySound(KeyPressSound);
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
                            if (CursorPosition == 22)
                                twoPlayersMode = true;
                            CurrentScene++;
                            InitLevel1();
                            InitUIBar();
                            System.out.println(CurrentScene + "");
                            break;
                    }
            }
        }
        if (Event == KEY_RELEASED) {
            if (CurrentScene != 0 && !gameOver)
            {
                Player.getInstance().setState(Objects.State.IDLE);
                Player2.getInstance().setState(Objects.State.IDLE);
            }
        }
    }
}
