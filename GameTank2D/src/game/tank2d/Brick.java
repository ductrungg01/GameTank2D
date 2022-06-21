package game.tank2d;

import pkg2dgamesframework.Objects;

enum TypeOfBrick{
    BRICK000(0),
    BRICK001(1),
    BRICK002(2),
    BRICK003(3),
    BUSH(4),
    WATER(5);

    int type;
    TypeOfBrick(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }

}

public class Brick extends Objects {
    public static final int BRICK_WIDTH = 16;
    public static final int BRICK_HEIGHT = 16;
    static final State DEFAULT_STATE = State.IDLE;
    static final Rotation DEFAULT_ROTATION = Rotation.UP;
    private TypeOfBrick type;

    Brick(TypeOfBrick type, int x, int y, int w, int h){
        super(x, y, w, h, 0, DEFAULT_STATE, DEFAULT_ROTATION);

        this.type = type;

        setAnimation(type);
    }

    //region Getter and Setter
    public void setAnimation(TypeOfBrick type) {
        int yOnImg = 0;

        switch (type){
            case BRICK001 -> {
                yOnImg = 255;
                break;
            }
            case BRICK002 -> {
                yOnImg = 271;
                break;
            }
            case BRICK003 -> {
                yOnImg = 288;
                break;
            }
            case BUSH -> {
                yOnImg = 304;
                break;
            }
            case WATER -> {
                yOnImg = 320;
                break;
            }
        }

        super.setAnimation(100, 0, yOnImg, BRICK_WIDTH, BRICK_HEIGHT);
    }

    public TypeOfBrick getType() {
        return type;
    }

    public void setType(TypeOfBrick type) {
        this.type = type;
        setAnimation(type);
    }
    //endregion
}
