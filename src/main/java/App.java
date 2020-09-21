import javax.swing.SwingUtilities;

public class App {

    private final MazeGame game;
    private final MazeWorldFactory factory;

    public App() {
        factory = new MazeWorldFactory();
        game = new MazeGame(factory.createInitialWorld());
    }

    public void run() {
        MazeLoader loader = new MazeLoader();
        loader.setMazeHolder(maze -> game.setWorld(factory.createWorld(maze)));
        new MainWindow(game, loader).show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().run());
    }
}

