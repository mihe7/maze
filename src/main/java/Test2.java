import java.io.StringReader;
import java.util.Scanner;
import java.util.StringJoiner;

public class Test2 {
    private final MazeWorld world;

    public Test2(MazeWorld world) {
        this.world = world;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            showMap();
            String line = sc.nextLine();
            char move = 0;
            if (!line.isEmpty()) {
                move = line.charAt(0);
            }

            switch(move) {
                case 'u': world.movePlayer(Direction.NORTH); break;
                case 'd': world.movePlayer(Direction.SOUTH); break;
                case 'l': world.movePlayer(Direction.WEST); break;
                case 'r': world.movePlayer(Direction.EAST); break;
                case 'q': done = true; break;
                default:
                    System.out.println("Bitte nur u/d/l/r verwenden. Ende mit q.");
            }
        }
    }

    public void showMap() {
        System.out.println(mapToString());
    }

    private String mapToString() {
        Maze maze = world.getMaze();
        Position player = world.getPlayer();

        StringJoiner sj = new StringJoiner("\n");

        for (int y = 0, n = maze.getHeight(); y < n; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0, m = maze.getWidth(); x < m; x++) {
                if (player.x == x && player.y == y) {
                    line.append('*');
                } else {
                    line.append(getTypeChar(maze.get(x,y)));
                }
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
            "wwwwwdw\n" +
            "wfddddw\n" +
            "wdddddd\n" +
            "ddddddw\n" +
            "wdddddw\n" +
            "wwwwwdw\n";
             
        MazeTextFormat fmt = new MazeTextFormat();
        Maze maze = fmt.read(new StringReader(text));
        MazeWorld world = new MazeWorld(maze, new Position(4, 4));
        Test2 app = new Test2(world);
        app.run();
    }
}

