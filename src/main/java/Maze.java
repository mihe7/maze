public class Maze {
    private final FieldType[][] fields;
    private final Size size;

    public Maze(FieldType[][] fields) {
        int height = fields.length;
        int width = height == 0 ? 0 : fields[0].length;
        this.fields = new FieldType[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.fields[y][x] = fields[y][x];
            }
        }

        size = new Size(width, height);
    }

    public int getHeight() { return size.height; }
    public int getWidth() { return size.width; }
    public Size getSize() { return size; }
    public FieldType get(int x, int y) { return fields[y][x]; }            

    public boolean contains(Position pos) {
        return pos.x >= 0 && pos.x < size.width &&
                pos.y >= 0 && pos.y < size.height;
    }

    public boolean hasTypeAt(FieldType type, Position pos) {
        return contains(pos) && get(pos.x, pos.y) == type;
    }
}

