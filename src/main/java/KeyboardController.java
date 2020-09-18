import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardController extends KeyAdapter {
    private final MazeGame game;

    public KeyboardController(MazeGame game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0, dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  dx--; break;
            case KeyEvent.VK_RIGHT: dx++; break;
            case KeyEvent.VK_UP:    dy--; break;
            case KeyEvent.VK_DOWN:  dy++; break;
            default:
                return;
        }

        game.movePlayer(dx, dy);
    }
}

