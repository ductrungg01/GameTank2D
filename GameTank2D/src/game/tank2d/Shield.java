package game.tank2d;

import pkg2dgamesframework.Objects;

public class Shield extends Objects {
    public static final int SHIELD_WIDTH = 34;
    public static final int SHIELD_HEIGHT = 34;
    public static final int SHIELD_ACTIVE_TIME = 3000;
    private static final State DEFAULT_STATE = State.IDLE;
    private static final Rotation DEFAULT_ROTATION = Rotation.UP;
    public boolean isActive = true;
    Shield(int x, int y){
        super(x, y, SHIELD_WIDTH, SHIELD_HEIGHT, DEFAULT_STATE, DEFAULT_ROTATION);

        super.setAnimation(100, 255, 0, SHIELD_WIDTH,
                SHIELD_HEIGHT, 33, 0, 2);
    }
    public void Update(long deltaTime){
        this.getAnimation().Update_Me(deltaTime);
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
