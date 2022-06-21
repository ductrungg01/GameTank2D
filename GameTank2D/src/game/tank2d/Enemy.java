package game.tank2d;

import pkg2dgamesframework.Objects;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
    private static final int ENEMY_MOVE = 1;
    private TypeOfEnemy type;
    private EnemyAppear enemyAppear;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            InactiveAppear();
            System.out.println("timerTask of enemy");
        }
    };
    Enemy(TypeOfEnemy type, int x, int y, Rotation rotation) {
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_MOVE, DEFAULT_STATE, rotation);

        this.timer.schedule(timerTask, EnemyAppear.ENEMYAPPEAR_ACTIVE_TIME);
        this.enemyAppear = new EnemyAppear(this.getPosX(), this.getPosY());

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

    public void update(long deltaTime){
        if (this.enemyAppear.isActive){
            this.enemyAppear.Update(deltaTime);
        } else {
            Move(this.rotation);

            this.getAnimation().Update_Me(deltaTime);
        }
    }
    public void Paint(Graphics2D g2){
        if (this.enemyAppear.isActive){
            this.enemyAppear.Paint(g2);
        } else {
            super.Paint(g2);
        }
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
