import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.HashMap;

public class KeyboardController extends KeyAdapter {
    private static final Map<Integer, Direction> KEY_MAPPING =
            new HashMap<>();
    static {
        KEY_MAPPING.put(KeyEvent.VK_LEFT, Direction.WEST);
        KEY_MAPPING.put(KeyEvent.VK_RIGHT, Direction.EAST);
        KEY_MAPPING.put(KeyEvent.VK_UP, Direction.NORTH);
        KEY_MAPPING.put(KeyEvent.VK_DOWN, Direction.SOUTH);
    }

    private final MazeGame game;

    public KeyboardController(MazeGame game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction dir = KEY_MAPPING.get(e.getKeyCode());
        if (dir != null) {
            game.movePlayer(dir);
        }
    }
}

