package game.tank2d;

import pkg2dgamesframework.Objects;


public class StartScreen extends Objects {
    static final State DEFAULT_STATE = State.IDLE;
    static final Rotation DEFAULT_ROTATION = Rotation.UP;

    StartScreen(int x, int y){
        super(x, y, 384, 192, 0, DEFAULT_STATE, DEFAULT_ROTATION);
        super.setAnimation(100, 130, 272, 380, 140);
    }

    //region Getter and Setter

    //endregion
}
