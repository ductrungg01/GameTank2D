package game.tank2d;

import pkg2dgamesframework.Objects;

public class Bullet extends Objects {
    public static final int BULLET_WIDTH = 7;
    public static final int BULLET_HEIGHT = 9;
    private static final int BULLET_MOVE = 2;
    private static final State DEFAULT_STATE = State.IDLE;

    Bullet(int x, int y, Rotation rotation) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT, BULLET_MOVE, DEFAULT_STATE, rotation);

        setAnimation(100, 0, 351, BULLET_WIDTH, BULLET_HEIGHT);
        setAnimation(100, 0, 351, BULLET_WIDTH, BULLET_HEIGHT);
    }

    public void Update(long deltaTime) {
        Move(this.getRotation());

        this.updateRect();

        super.Update(deltaTime);
    }
    public Explosion createNewExplosion(){
        int x = this.getPosX();
        int y = this.getPosY();

        switch (this.rotation){
            case UP -> {
                x = this.getPosX() + 3;
                break;
            }
            case DOWN -> {
                x = this.getPosX() + 3;
                y = this.getPosY() + BULLET_HEIGHT;
                break;
            }
            case LEFT -> {
                y = this.getPosY() + 3;
                break;
            }
            case RIGHT -> {
                x = this.getPosX() + BULLET_WIDTH;
                y = this.getPosY() + 3;
                break;
            }
        }

        return new Explosion(x - Explosion.EXPLOSION_WIDTH/2, y - Explosion.EXPLOSION_HEIGHT/2);
    }

}
