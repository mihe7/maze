public class MazeGame {
    private MazeWorld world;

    public MazeGame(MazeWorld world) {
        this.world = world;
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
    }
}

