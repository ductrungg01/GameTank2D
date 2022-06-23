package game.tank2d;

import pkg2dgamesframework.Objects;

public class Eagle extends Objects {
    static final State DEFAULT_STATE = State.IDLE;
    static final Rotation DEFAULT_ROTATION = Rotation.UP;
    Eagle(int x, int y) {
        super(x, y, Tank2D.PIXEL * 2, Tank2D.PIXEL * 2, 0, DEFAULT_STATE, DEFAULT_ROTATION);
        this.setAnimation(100, 0, 360, Tank2D.PIXEL * 2, Tank2D.PIXEL * 2);
    }
    public void setDestroyed() {
        this.setAnimation(100, 32, 360, Tank2D.PIXEL * 2, Tank2D.PIXEL * 2);
    }
}
