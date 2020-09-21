import javax.swing.*;

public class MainWindow {

    private final MazeGame game;
    private final LoadMazeAction loadMazeAction;

    private JFrame frame;
    private MessageOverlay winnerMessage;
    private final WorldView worldView;

    private final GameStateObserver gameStateHandler = new GameStateObserver() {
        @Override
        public void gameOver() {
            winnerMessage.show();
        }
        @Override
        public void worldChanged(MazeWorld newWorld) {
            worldView.changeWorld(newWorld);
            frame.pack();
        }
    };

    public MainWindow(MazeGame game, MazeLoader loader) {
        this.game = game;
        game.setObserver(gameStateHandler);
        loadMazeAction = new LoadMazeAction(loader);

        worldView = new WorldView(game.getWorld(), 32, 32);
        WorldViewDefaults.configure(worldView);
        worldView.setFocusable(true);
        worldView.addKeyListener(new KeyboardController(game));
    }

    public void show() {
        frame = new JFrame("maze");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setJMenuBar(createMenuBar());
        frame.add(worldView);
        frame.pack();
        frame.setVisible(true);

        initWinnerMessage(frame); 
    }

    private JMenuBar createMenuBar() {
        JMenuItem loadMazeItem = new JMenuItem(loadMazeAction);
        JMenuItem exitItem = new JMenuItem("Beenden");
        exitItem.setMnemonic('e');
        exitItem.addActionListener(e -> System.exit(0));
        JMenu gameMenu = new JMenu("Spiel");
        gameMenu.setMnemonic('S');
        gameMenu.add(loadMazeItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        return menuBar;
    }

    private void initWinnerMessage(JFrame frame) {
        String message = "<html><h1 style=\"text-align: center;\">Gewonnen!</h1>" +
            "<p>Weiter mit der Leertaste.</p>";

        winnerMessage = new MessageOverlay(frame)
                .display(message);
    }
}
