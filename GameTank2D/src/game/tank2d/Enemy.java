package game.tank2d;

import pkg2dgamesframework.Objects;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static game.tank2d.Player.PLAYER_HEIGHT;
import static game.tank2d.Player.PLAYER_WIDTH;
import static game.tank2d.Tank2D.*;

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
    private static final State DEFAULT_STATE = State.IDLE;
    private static int ENEMY_MOVE = 1;
    private long timeChangeDirection = 0;
    private long timeShoot = 0;
    private TypeOfEnemy type;
    private EnemyAppear enemyAppear;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            InactiveAppear();
        }
    };
    Enemy(TypeOfEnemy type, int x, int y, Rotation rotation) {
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_MOVE, DEFAULT_STATE, rotation);

        if (this.type != TypeOfEnemy.ENEMY001 && this.type != TypeOfEnemy.ENEMY002) {
            setOBJECT_MOVE(ENEMY_MOVE * 2);
        }

        this.timer.schedule(timerTask, EnemyAppear.ENEMYAPPEAR_ACTIVE_TIME);
        this.enemyAppear = new EnemyAppear(this.getPosX(), this.getPosY());
        this.needCheckBound = true;
        this.type = type;
        int yOnImage = 0;

        switch (type){
            case ENEMY001:
                yOnImage = 425;
                break;
            case ENEMY002:
                yOnImage = 555;
                break;
            case ENEMY003:
                yOnImage = 682;
                break;
            case ENEMY004:
                yOnImage = 809;
                break;
        }

        super.setAnimation(100, 3, yOnImage, ENEMY_WIDTH, ENEMY_HEIGHT);
        super.setAnimation(100, 3, yOnImage, ENEMY_WIDTH, ENEMY_HEIGHT,
                32, 0, 6);
    }
    private void InactiveAppear(){
        this.enemyAppear.setActive(false);
    }

    @Override
    public void Update(long deltaTime){
        if (this.isDestroyAlready) return;
        if (this.enemyAppear.isActive){
            this.enemyAppear.Update(deltaTime);
        } else {
            if (System.currentTimeMillis() - timeChangeDirection > 2000) {
                timeChangeDirection = System.currentTimeMillis();
                int typeMove = generator.nextInt(0, 2); //0: move to eagle, 1: move random direction
                int direction = generator.nextInt(0, 5); // direction to move random
                Rotation direction1; // left or right | random direction
                Rotation direction2 = Rotation.DOWN; // down
                if (typeMove == 0) {
                    if (this.getPosX() > eagle.getPosX())
                        direction1 = Rotation.LEFT;
                    else
                        direction1 = Rotation.RIGHT;
                    direction = generator.nextInt(0, 2);
                    if (direction == 0)
                        this.rotation = direction1;
                    else
                        this.rotation = direction2;
                } else {
                    switch (direction) {
                        case 0:
                            direction1 = Rotation.LEFT;
                            break;
                        case 1:
                            direction1 = Rotation.UP;
                            break;
                        case 2:
                            direction1 = Rotation.RIGHT;
                            break;
                        case 3:
                            direction1 = Rotation.DOWN;
                            break;
                        default:
                            direction1 = Rotation.DOWN;
                            break;
                    }
                    this.rotation = direction1;
                }
            }
            if (checkNextPosIsWrong(this.rotation, this)) {
                Move(this.rotation);
            }
            if (System.currentTimeMillis() - timeShoot > 1500) {
                bulletList.add(createNewBullet());
                timeShoot = System.currentTimeMillis();
            }
            this.getAnimation().Update_Me(deltaTime);
        }
    }
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
            Enemy e = enemyList.get(i);
            if (e.checkCollision(pRect) && e != o)
                return false;
        }
        //endregion
        if (Player.getInstance().checkCollision(pRect))
            return false;
        if (twoPlayersMode && Player2.getInstance().checkCollision(pRect))
            return false;

        if (eagle != null)
            if (eagle.checkCollision(pRect))
                return false;
        return true;

    }

    public void Paint(Graphics2D g2){
        if (this.enemyAppear.isActive){
            this.enemyAppear.Paint(g2);
        } else {
            super.Paint(g2);
        }
    }

    public Bullet createNewBullet(){
        int xBullet = 0;
        int yBullet = 0;

        switch (this.rotation){
            case UP -> {
                xBullet = this.pos.x + ENEMY_WIDTH/2 - 3;
                yBullet = this.pos.y - 9;
                break;
            }
            case DOWN -> {
                xBullet = this.pos.x + ENEMY_WIDTH/2 - 3;
                yBullet = this.pos.y + ENEMY_HEIGHT + 9;
                break;
            }
            case LEFT -> {
                xBullet = pos.x - 9;
                yBullet = pos.y + ENEMY_HEIGHT/2 - 4;
                break;
            }
            case RIGHT -> {
                xBullet = pos.x + ENEMY_WIDTH + 9;
                yBullet = pos.y + ENEMY_HEIGHT/2 - 4;
                break;
            }
        }
        return new Bullet(xBullet, yBullet, this.rotation, 0);
    }

    //region Getter and Setter
    public TypeOfEnemy getType() {
        return type;
    }

    public void setType(TypeOfEnemy type) {
        this.type = type;
    }

    public EnemyAppear getEnemyAppear() {
        return enemyAppear;
    }

    public void setEnemyAppear(EnemyAppear enemyAppear) {
        this.enemyAppear = enemyAppear;
    }
    //endregion
}
