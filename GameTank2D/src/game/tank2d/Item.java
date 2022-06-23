package game.tank2d;

import pkg2dgamesframework.Objects;

enum TypeOfItem {
    LIFE(0),
    DESTROY(1),
    SHIELD(2),
    WALL(3);

    int type;
    TypeOfItem(int i) {
        this.type = i;
    }

    public int getType() {
        return type;
    }

}

public class Item extends Objects {
    static final State DEFAULT_STATE = State.IDLE;
    static final Rotation DEFAULT_ROTATION = Rotation.UP;
    private TypeOfItem type;

    Item(TypeOfItem type, int x, int y){
        super(x, y, Tank2D.PIXEL * 2, Tank2D.PIXEL * 2, 0, DEFAULT_STATE, DEFAULT_ROTATION);

        this.type = type;

        setAnimation(type);
    }

    //region Getter and Setter
    public void setAnimation(TypeOfItem type) {
        int xOnImg = 0;
        int yOnImg = 0;

        switch (type){
            case LIFE -> {
                xOnImg = 32;
                yOnImg = 392;
                break;
            }
            case DESTROY -> {
                xOnImg = 96;
                yOnImg = 360;
                break;
            }
            case SHIELD -> {
                xOnImg = 64;
                yOnImg = 392;
                break;
            }
            case WALL -> {
                xOnImg = 96;
                yOnImg = 392;
                break;
            }
        }

        super.setAnimation(100, xOnImg, yOnImg, Tank2D.PIXEL * 2, Tank2D.PIXEL * 2);
    }

    public TypeOfItem getType() {
        return type;
    }

    public void setType(TypeOfItem type) {
        this.type = type;
        setAnimation(type);
    }
    //endregion
}
