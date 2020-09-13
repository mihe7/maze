public class MazeGame {
    private MazeWorld world;

    public MazeGame(MazeWorld world) {
        this.world = world;
    }

    public MazeWorld getWorld() { return world; }

    public boolean isWin() {
        Maze maze = world.getMaze();
        Position player = world.getPlayer();
        return maze.get(player.x, player.y) == FieldType.FINISH;
    }

    public void movePlayer(int dx, int dy) {
        if (isWin()) {
            return;
        }
        world.movePlayer(dx, dy);
    }
}

