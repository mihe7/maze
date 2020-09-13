public class MazeWorld {
    private Maze maze;
    private Position player;

    public MazeWorld(Maze maze, Position player) {
        this.maze = maze;
        this.player = player;
    }

    public Maze getMaze() { return maze; }
    public Position getPlayer() { return player; }

    public void move(int dx, int dy) {
        Position newPos = player.move(dx, dy);
        if (isLegalMove(newPos)) {
            player = newPos;
        }
    }
    
    private boolean isLegalMove(Position pos) {
        if (pos.x < 0 || pos.x >= maze.getWidth() ||
                pos.y < 0 || pos.y >= maze.getHeight()) {
            return false;
        }

        return maze.get(pos.x, pos.y) != FieldType.WALL;
    }  
}

