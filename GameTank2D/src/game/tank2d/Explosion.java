package game.tank2d;

import pkg2dgamesframework.Objects;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Explosion extends Objects {
    public static final int EXPLOSION_WIDTH = 34;
    public static final int EXPLOSION_HEIGHT = 34;
    public static final int EXPLOSION_ACTIVE_TIME = 500;
    private static final State DEFAULT_STATE = State.IDLE;
    private static final Rotation DEFAULT_ROTATION = Rotation.UP;

    private boolean isActive = true;

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            setActive(false);
        }
    };



    Explosion(int x, int y){
        super(x, y, EXPLOSION_WIDTH, EXPLOSION_HEIGHT, 0, DEFAULT_STATE, DEFAULT_ROTATION);

        timer.schedule(timerTask, EXPLOSION_ACTIVE_TIME);

        super.setAnimation(100, 256, 62, EXPLOSION_WIDTH, EXPLOSION_HEIGHT,
                34, 0, 3);
    }
    public void Update(long deltaTime){
        if (this.isActive){
            super.Update(deltaTime);
        }
    }
    public void Paint(Graphics2D g2){
        if (this.isActive){
            super.Paint(g2);
        }
    }

    //region Setter and Getter
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    //endregion
}
