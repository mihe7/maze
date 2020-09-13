import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class WorldViewDefaults {
    public static final Image DESERT;
    public static final Image WALL;
    public static final Image FINISH;
    public static final Image PLAYER;

    static {
        DESERT = getImage("desert.png");
        WALL = getImage("wall.png");
        FINISH = getImage("finish.png");
        PLAYER = getImage("player.png");
    }

    private static Image getImage(String name) {
        try {
            return ImageIO.read(WorldViewDefaults.class
                    .getResourceAsStream("/images/" + name));
        } catch (IOException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void configure(WorldView view) {
        view.setImage(FieldType.WALL, WALL);
        view.setImage(FieldType.DESERT, DESERT);
        view.setImage(FieldType.FINISH, FINISH);
        view.setPlayer(PLAYER);
    }
}
