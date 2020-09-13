import java.io.StringReader;
import java.util.StringJoiner;

public class Test1 {
    private final Maze maze;

    public Test1(Maze maze) {
        this.maze = maze;
    }

    public void showMap() {
        System.out.println(mapToString());
    }

    private String mapToString() {
        StringJoiner sj = new StringJoiner("\n");

        for (int y = 0, n = maze.getHeight(); y < n; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0, m = maze.getWidth(); x < m; x++) {
                line.append(getTypeChar(maze.get(x,y)));
            }
            sj.add(line.toString());
        }
        return sj.toString();
    }

    private char getTypeChar(FieldType type) {
        switch(type) {
            case WALL: return 'W';
            case DESERT: return '.';
            case FINISH: return 'F';
            default: return '?';
        }
    }


    public static void main(String[] args) throws Exception {
        String text = 
            "wwwwwww\n" +
            "wfddddw\n" +
            "wdddddw\n" +
            "wdddddw\n" +
            "wdddddw\n" +
            "wwwwwww\n";
             
        MazeTextFormat fmt = new MazeTextFormat();
        Maze maze = fmt.read(new StringReader(text));
        Test1 app = new Test1(maze);
        app.showMap();
    }
}

