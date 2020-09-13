public class MazeWorld {
    private Maze maze;
    private Position player;

    public MazeWorld(Maze maze, Position player) {
        this.maze = maze;
        this.player = player;
    }

    public Maze getMaze() { return maze; }
    public Position getPlayer() { return player; }

    public void movePlayer(int dx, int dy) {
        Position newPos = player.move(dx, dy);
        if (isLegalMove(newPos)) {
            player = newPos;
        }
    }
    
    private boolean isLegalMove(Position pos) {
        return maze.contains(pos) &&
                maze.get(pos.x, pos.y) != FieldType.WALL;
    }  
}

