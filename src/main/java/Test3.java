import java.io.StringReader;
import java.util.Scanner;
import java.util.StringJoiner;

public class Test3 {
    private final MazeGame game;

    public Test3(MazeGame game) {
        this.game = game;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (!game.isWin()) {
            showMap();
            String line = sc.nextLine();
            char move = 0;
            if (!line.isEmpty()) {
                move = line.charAt(0);
            }

            switch(move) {
                case 'u': game.movePlayer(0, -1); break;
                case 'd': game.movePlayer(0, 1); break;
                case 'l': game.movePlayer(-1, 0); break;
                case 'r': game.movePlayer(1, 0); break;
                default:
                    System.out.println("Bitte nur u/d/l/r verwenden.");
            }
        }
        showMap();
        System.out.println("Ziel erreicht");
    }

    public void showMap() {
        System.out.println(mapToString());
    }

    private String mapToString() {
        MazeWorld world = game.getWorld();
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
        Test3 app = new Test3(new MazeGame(world));
        app.run();
    }
}

