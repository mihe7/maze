public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position move(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }

    public Position move(Position delta) {
        return new Position(x + delta.x, y + delta.y);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o == this || !(o instanceof Position)) {
            return o == this;
        }

        Position p = (Position) o;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return 5*x+13*y;
    }
}

