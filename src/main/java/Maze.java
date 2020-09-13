public class Maze {
    private final FieldType[][] fields;

    public Maze(FieldType[][] fields) {
        int height = fields.length;
        int width = height == 0 ? 0 : fields[0].length;
        this.fields = new FieldType[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.fields[y][x] = fields[y][x];
            }
        }
    }

    public int getHeight() { return fields.length; }
    public int getWidth() { return getHeight() == 0 ? 0 : fields[0].length; }   
    public FieldType get(int x, int y) { return fields[y][x]; }            
}

