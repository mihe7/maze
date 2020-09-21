import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

import java.util.ArrayList;
import java.util.List;

public class MazeWorldFactory {

    public MazeWorld createInitialWorld() {
        String text = "f";
             
        MazeTextFormat fmt = new MazeTextFormat();
        try {
            Maze maze = fmt.read(new StringReader(text));
            return new MazeWorld(maze, new Position(0, 0));
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public MazeWorld createWorld(Maze maze) {
        Position pos = determineStartPosition(maze);
        if (pos == null) {
            return createInitialWorld(); 
        }
        return new MazeWorld(maze, pos);
    }

    private Position determineStartPosition(Maze maze) {
        List<Position> free = new ArrayList<>();
        for (int y = 0, height = maze.getHeight(); y < height; y++) {
            for (int x = 0, width = maze.getWidth(); x < width; x++) {
                if (maze.get(x,y) == FieldType.DESERT) {
                    free.add(new Position(x,y));
                }
            }
        }

        if (free.isEmpty()) {
            return null;
        }
        return free.get((int)(Math.random() * free.size()));
    }
}

