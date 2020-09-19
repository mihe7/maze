import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

public class App2 {

    private MazeWorld world;
    private WorldView worldView;

    private KeyListener keyboardController = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            Direction dir = null;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:  dir = Direction.WEST; break;
                case KeyEvent.VK_RIGHT: dir = Direction.EAST; break;
                case KeyEvent.VK_UP:    dir = Direction.NORTH; break;
                case KeyEvent.VK_DOWN:  dir = Direction.SOUTH; break;
                default:
                    return;
            }

            if (dir != null) {
                world.movePlayer(dir);
                worldView.repaint();
            }
        }
    };

    public void run() {
        world = createWorld();
        worldView = new WorldView(world, 32, 32);
        WorldViewDefaults.configure(worldView);

        worldView.setFocusable(true);
        worldView.addKeyListener(keyboardController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(worldView);
        frame.pack();
        frame.setVisible(true);
    }

    private MazeWorld createWorld() {
        String text = 
            "wwwwwdw\n" +
            "wfddddw\n" +
            "wdddddd\n" +
            "ddddddw\n" +
            "wdddddw\n" +
            "wwwwwdw\n";
             
        MazeTextFormat fmt = new MazeTextFormat();
        try {
            Maze maze = fmt.read(new StringReader(text));
            return new MazeWorld(maze, new Position(4, 4));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App2().run());
    }
}
