package game.tank2d;

import java.util.ArrayList;

import static game.tank2d.Brick.BRICK_HEIGHT;
import static game.tank2d.Brick.BRICK_WIDTH;
import static game.tank2d.Tank2D.MAP_WIDTH_TILE;
import static game.tank2d.Tank2D.MAP_HEIGHT_TILE;
import static game.tank2d.Tank2D.PIXEL;

public class Map {

    static TypeOfBrick b0 = TypeOfBrick.BRICK000;
    static TypeOfBrick b1 = TypeOfBrick.BRICK001;
    static TypeOfBrick b2 = TypeOfBrick.BRICK002;
    static TypeOfBrick b3 = TypeOfBrick.BRICK003;
    static TypeOfBrick b4 = TypeOfBrick.BUSH;
    static TypeOfBrick b5 = TypeOfBrick.WATER;


    public static ArrayList<Brick> mapbrick = new ArrayList<Brick>();
    static TypeOfBrick[][] brickMap1 = {
            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},
            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b2, b2, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b2, b2, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},

            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},
            {b1, b1, b0, b0, b1, b1, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b1, b1, b0, b0, b1, b1},

            {b2, b2, b0, b0, b1, b1, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b1, b1, b0, b0, b2, b2},
            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},

            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b1, b1, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},
            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b1, b1, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},
            {b0, b0, b1, b1, b0, b0, b1, b1, b0, b0, b0, b1, b1, b1, b1, b0, b0, b0, b1, b1, b0, b0, b1, b1, b0, b0},

            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b0, b0, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},
            {b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b1, b0, b0, b1, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0, b0},

    };
    public void initBrickMap1()
    {
        mapbrick.clear();
        for (int i = 0; i < MAP_HEIGHT_TILE; i++)
        {
            for (int j = 0; j < MAP_WIDTH_TILE; j++)
            {
                if (brickMap1[i][j] != TypeOfBrick.BRICK000)
                    mapbrick.add(new Brick(brickMap1[i][j], getTilePosX(MAP_WIDTH_TILE * i + j), getTilePosY(MAP_WIDTH_TILE * i + j), BRICK_WIDTH, BRICK_HEIGHT));
            }
        }

    }

    int getTilePosX(int tileNumber)
    {
        return tileNumber % MAP_WIDTH_TILE * PIXEL;
    }

    int getTilePosY(int tileNumber)
    {
        return tileNumber / MAP_WIDTH_TILE * PIXEL;
    }

    public ArrayList<Brick> getBrickListMap1()
    {
        return mapbrick;
    }

}
