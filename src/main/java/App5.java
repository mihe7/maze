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

public class App5 {

    private MazeGame game;
    private WorldView worldView;
    private MessageOverlay winnerMessage;

    private KeyListener keyboardController;

    public void run() {
        newGame();

        worldView = new WorldView(game.getWorld(), 32, 32);
        WorldViewDefaults.configure(worldView);

        worldView.setFocusable(true);
        worldView.addKeyListener(keyboardController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(worldView);
        frame.pack();
        frame.setVisible(true);

        initWinnerMessage(frame); 
    }

    private void initWinnerMessage(JFrame frame) {
        String message = "<html><h1 style=\"text-align: center;\">Gewonnen!</h1>" +
            "<p>Weiter mit der Leertaste.</p>";

        winnerMessage = new MessageOverlay(frame)
                .display(message)
                .onDismiss(this::restart);
    }

    private void restart() {
        newGame();
        worldView.changeWorld(game.getWorld());
    }

    private void newGame() {
        game = new MazeGame(createWorld());
        keyboardController = new KeyboardController(game);
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
        SwingUtilities.invokeLater(() -> new App5().run());
    }
}
