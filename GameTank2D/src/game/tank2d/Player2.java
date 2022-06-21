package game.tank2d;

import pkg2dgamesframework.Objects;

import java.io.IOException;

public class Player2 extends Objects {
    static Player2 instance;

    static {
        try {
            instance = new Player2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Player2 getInstance() {
        return instance;
    }

    private Player2() throws IOException {

        super(0);
        Reset();
    }

    public void Reset() throws IOException {

    }
}
