package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import byog.Core.Position;
/**n
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final Random RANDOM = new Random (0);

    public static int hexRowWidth(int s, int i) {
        int effective_i = i;
        if (i >= s) {
            effective_i = 2 * s - 1 - i;
        }
        return s + 2 * effective_i;
    }

    public static int hexRowOffset(int s, int i) {
        int effective_i = i;
        if ( i >= s ) {
            effective_i = 2 * s - 1 - i;
        }
        return -effective_i;
    }

    public static void addRow ( TETile[][] world, Position p, int width, TETile t){
        int y = p.y;
        for (int xi = 0; xi < width; xi++){
            int x = p.x + xi;
            world[x][y] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        if ( s < 2 ){
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        for (int yi = 0; yi < 2 * s ; yi++){
            int thisx = p.x + hexRowOffset(s, yi);
            int thisy = p.y + yi;
            Position start = new Position(thisx, thisy);
            int rowWidth = hexRowWidth(s, yi);
            addRow(world, start, rowWidth, t);
        }
    }
    public static void main(String arg[]) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        int WIDTH = 50;
        int HEIGHT = 50;
        Position p = new Position ( 20, 10);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        addHexagon(world, p, 5, Tileset.TREE);
        addHexagon(world, new Position(10,10), 3, Tileset.WATER);
        addHexagon(world, new Position(35,35), 3, Tileset.FLOWER);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}

