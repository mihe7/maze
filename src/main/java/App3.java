import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

public class App3 {

    private MazeGame game;
    private WorldView worldView;
    private TransparentPanel winnerMessage;

    private KeyListener dismissMessageByKeyboard = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                hideWinnerMessage();
                restart();
            }
        }
    };

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
                game.movePlayer(dir);
                worldView.repaint();
            }

            if (game.isWin()) {
                showWinnerMessage();
            }
        }
    };

    public void run() {
        initWinnerMessage(); 

        newGame();
        worldView = new WorldView(game.getWorld(), 32, 32);
        WorldViewDefaults.configure(worldView);

        worldView.setFocusable(true);
        worldView.addKeyListener(keyboardController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setGlassPane(winnerMessage);
        frame.add(worldView);
        frame.pack();
        frame.setVisible(true);
    }

    private void initWinnerMessage() {
        winnerMessage = new TransparentPanel(new BorderLayout());
        winnerMessage.setBackground(new Color(255, 255, 255, 200));

        String message = "<html><h1 style=\"text-align: center;\">Gewonnen!</h1>" +
            "<p>Weiter mit der Leertaste.</p>";
        winnerMessage.add(new JLabel(message, SwingConstants.CENTER));
        winnerMessage.setFocusable(true);
        winnerMessage.addKeyListener(dismissMessageByKeyboard);
    }

    private void showWinnerMessage() {
        if (winnerMessage.isVisible()) {
            return;
        }
        winnerMessage.setVisible(true);
        winnerMessage.requestFocus();
    }

    private void hideWinnerMessage() {
        winnerMessage.setVisible(false);
    }

    private void restart() {
        newGame();
        worldView.changeWorld(game.getWorld());
    }

    private void newGame() {
        game = new MazeGame(createWorld());
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
        SwingUtilities.invokeLater(() -> new App3().run());
    }
}
