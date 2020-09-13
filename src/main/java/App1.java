import javax.swing.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

public class App1 {

    public void run() {
        WorldView worldView = new WorldView(createWorld(), 32, 32);
        WorldViewDefaults.configure(worldView);
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
        SwingUtilities.invokeLater(() -> new App1().run());
    }
}
