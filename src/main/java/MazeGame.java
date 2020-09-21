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

    public void setWorld(MazeWorld world) {
        this.world = world;
        if (observer != null) {
            observer.worldChanged(world);
        }
    }

    public boolean isWin() {
        return world.isPlayerOn(FieldType.FINISH);
    }

    public void movePlayer(Direction dir) {
        if (isWin()) {
            return;
        }

        world.movePlayer(dir);

        if (observer != null && isWin()) {
            observer.gameOver();
        }
    }
}

