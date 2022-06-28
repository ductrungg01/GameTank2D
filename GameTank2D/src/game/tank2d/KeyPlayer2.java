package game.tank2d;

import pkg2dgamesframework.GameScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPlayer2 implements KeyListener,Runnable{

    private GameScreen context;
    private Thread thread;
    public KeyPlayer2(GameScreen context){
        this.context = context;
        this.thread = new Thread(this);
        thread.start();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      //  context.KEY_ACTION(e, GameScreen.KEY_PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // context.KEY_ACTION(e, GameScreen.KEY_RELEASED);
    }

    @Override
    public void run() {

    }
}
