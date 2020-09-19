public class MazeGame {
    private MazeWorld world;
    private GameStateObserver observer;

    public MazeGame(MazeWorld world) {
        this.world = world;
    }

    public void setObserver(GameStateObserver observer) {
        this.observer = observer;
    }

    public MazeWorld getWorld() { return world; }

    public boolean isWin() {
        return world.isPlayerOn(FieldType.FINISH);
    }

    public void movePlayer(int dx, int dy) {
        if (isWin()) {
            return;
        }

        world.movePlayer(dx, dy);

        if (isWin()) {
            observer.gameOver();
        }
    }
}

