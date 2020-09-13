import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class WorldViewDefaults {
    public static final Image DESERT = getImage("desert.png");
    public static final Image WALL = getImage("wall.png");
    public static final Image FINISH = getImage("finish.png");
    public static final Image PLAYER = getImage("player.png");

    private WorldViewDefaults() {
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
