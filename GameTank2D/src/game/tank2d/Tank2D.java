package game.tank2d;

import pkg2dgamesframework.GameScreen;

import java.awt.*;
import java.awt.event.KeyEvent;

import static game.tank2d.Brick.BRICK_HEIGHT;
import static game.tank2d.Brick.BRICK_WIDTH;
import static game.tank2d.Enemy.ENEMY_HEIGHT;
import static game.tank2d.Enemy.ENEMY_WIDTH;

public class Tank2D extends GameScreen {

    Enemy enemy001 = new Enemy(TypeOfEnemy.ENEMY004, 250, 250, ENEMY_WIDTH, ENEMY_HEIGHT);
    Brick brick001 = new Brick(TypeOfBrick.BRICK001, 300, 300, BRICK_WIDTH, BRICK_HEIGHT);
    Brick brick002 = new Brick(TypeOfBrick.BRICK002, 330, 300, BRICK_WIDTH, BRICK_HEIGHT);
    Brick brick003 = new Brick(TypeOfBrick.BRICK003, 300, 330, BRICK_WIDTH, BRICK_HEIGHT);
    Brick brick004 = new Brick(TypeOfBrick.BUSH, 330, 330, BRICK_WIDTH, BRICK_HEIGHT);
    Brick brick005 = new Brick(TypeOfBrick.WATER, 400, 300, BRICK_WIDTH, BRICK_HEIGHT);

    Bullet bullet001 = new Bullet(150, 250, Rotation.DOWN);

    Shield shield001 = new Shield(90, 100);

    public Tank2D() {
        super(500, 500);

        BeginGame();
    }

    public static void main(String[] args) {
        new Tank2D();
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        // shield
        shield001.update(deltaTime);

        // player
        Player.getInstance().getAnimation().Update_Me(deltaTime);

        // player2
        Player2.getInstance().getAnimation().Update_Me(deltaTime);

        // brick
        brick001.update(deltaTime);
        brick002.update(deltaTime);
        brick003.update(deltaTime);
        brick004.update(deltaTime);
        brick005.update(deltaTime);

        // enemy
        enemy001.update(deltaTime);

        // bullet
        bullet001.update(deltaTime);


    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, 500, 500);

        // shield
        shield001.getAnimation().PaintAnims(shield001.getPosX(), shield001.getPosY(), shield001.getImgShield(), g2, 0, shield001.getRotation().getRotate());

        // player
        Player.getInstance().getAnimation().PaintAnims(Player.getInstance().getPosX(), Player.getInstance().getPosY(), Player.getInstance().getImgTanks(), g2, 0, Player.getInstance().getRotation().getRotate());

        // player2
        Player2.getInstance().getAnimation().PaintAnims(Player2.getInstance().getPosX(), Player2.getInstance().getPosY(), Player2.getInstance().getImgTanks(), g2, 0, Player2.getInstance().getRotation().getRotate());

        // brick
        brick001.getAnimation().PaintAnims(brick001.getPosX(), brick001.getPosY(), brick001.getImgBrick(), g2, 0, brick001.getRotation().getRotate());
        brick002.getAnimation().PaintAnims(brick002.getPosX(), brick002.getPosY(), brick002.getImgBrick(), g2, 0, brick002.getRotation().getRotate());
        brick003.getAnimation().PaintAnims(brick003.getPosX(), brick003.getPosY(), brick003.getImgBrick(), g2, 0, brick003.getRotation().getRotate());
        brick004.getAnimation().PaintAnims(brick004.getPosX(), brick004.getPosY(), brick004.getImgBrick(), g2, 0, brick004.getRotation().getRotate());
        brick005.getAnimation().PaintAnims(brick005.getPosX(), brick005.getPosY(), brick005.getImgBrick(), g2, 0, brick005.getRotation().getRotate());

        // enemy
        enemy001.getAnimation().PaintAnims(enemy001.getPosX(), enemy001.getPosY(), enemy001.getImgTanks(), g2, 0, enemy001.getRotation().getRotate());

        // bullet
        bullet001.getAnimation().PaintAnims(bullet001.getPosX(), bullet001.getPosY(), bullet001.getImgBullet(), g2, 0, bullet001.getRotation().getRotate());


    }


    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: // left
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.LEFT);
                    Player.getInstance().setPosX(Player.instance.getPosX()-10);
                    break;
                case KeyEvent.VK_UP: // up
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.UP);
                    Player.getInstance().setPosY(Player.instance.getPosY()-10);
                    break;
                case KeyEvent.VK_RIGHT: // right
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.RIGHT);
                    Player.getInstance().setPosX(Player.instance.getPosX()+10);
                    break;
                case KeyEvent.VK_DOWN: // down
                    Player.getInstance().setState(State.RUN);
                    Player.getInstance().setRotation(Rotation.DOWN);
                    Player.getInstance().setPosY(Player.instance.getPosY()+10);
                    break;
                case KeyEvent.VK_A: // A (left for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.LEFT);
                    Player2.getInstance().setPosX(Player2.instance.getPosX()-10);
                    break;
                case KeyEvent.VK_W: // W (up for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.UP);
                    Player2.getInstance().setPosY(Player2.instance.getPosY()-10);
                    break;
                case KeyEvent.VK_D: // D (right for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.RIGHT);
                    Player2.getInstance().setPosX(Player2.instance.getPosX()+10);
                    break;
                case KeyEvent.VK_S: // S (down for player 2)
                    Player2.getInstance().setState(State.RUN);
                    Player2.getInstance().setRotation(Rotation.DOWN);
                    Player2.getInstance().setPosY(Player2.instance.getPosY()+10);
                    break;

            }
        }
        if (Event == KEY_RELEASED)
        {
            Player.getInstance().setState(State.IDLE);
            // player2
            Player2.getInstance().setState(State.IDLE);
        }

    }
}
