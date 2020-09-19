enum Direction { 
    NORTH(0,-1), EAST(1,0), SOUTH(0,1), WEST(-1,0);

    private int dx;
    private int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Position move(Position pos) {
        return pos.move(dx, dy);
    }
}

