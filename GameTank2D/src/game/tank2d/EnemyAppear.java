

package game.tank2d;

import pkg2dgamesframework.Objects;


public class EnemyAppear extends Objects {
    public static final int ENEMYAPPEAR_WIDTH = 26;
    public static final int ENEMYAPPEAR_HEIGHT = 30;
    public static final int ENEMYAPPEAR_ACTIVE_TIME = 1700;
    private static final State DEFAULT_STATE = State.IDLE;
    private static final Rotation DEFAULT_ROTATION = Rotation.UP;
    public boolean isActive = true;

    EnemyAppear(int x, int y){
        super(x, y, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT, DEFAULT_STATE, DEFAULT_ROTATION);

        super.setAnimation(100, 255, 30, ENEMYAPPEAR_WIDTH, ENEMYAPPEAR_HEIGHT,
                33, 0, 6);
    }

    //region Getter and Setter
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    //endregion
}
