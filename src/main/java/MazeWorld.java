public class MazeWorld {
    private final Maze maze;
    private Position player;
    private MazeWorldObserver observer;

    public MazeWorld(Maze maze, Position player) {
        this.maze = maze;
        this.player = player;
    }

    public void setObserver(MazeWorldObserver observer) {
        this.observer = observer;
    }

    public Maze getMaze() { return maze; }
    public Position getPlayer() { return player; }

    public void movePlayer(int dx, int dy) {
        Position newPos = player.move(dx, dy);
        if (isLegalMove(newPos)) {
            Position oldPos = player;
            player = newPos;
            movedFrom(oldPos);
        }
    }

    private void movedFrom(Position oldPos) {
        if (observer != null) {
            observer.playerMoved(oldPos, player);
        }
    }
    
    private boolean isLegalMove(Position pos) {
        return maze.contains(pos) &&
                !maze.hasTypeAt(FieldType.WALL, pos);
    }

    public boolean isPlayerOn(FieldType type) {
        return maze.hasTypeAt(type, player);
    }
}

