import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import java.util.List;
import java.util.stream.Collectors;

public class MazeTextFormat {

    public Maze read(Reader reader) throws IOException {
        List<String> rows = readLines(reader);
        return createMaze(rows);
    }

    private List<String> readLines(Reader reader) throws IOException {
        try(BufferedReader buffered = new BufferedReader(reader)) {
            return buffered.lines().collect(Collectors.toList());
        }
    }

    private Maze createMaze(List<String> rows) {
        int height = rows.size();

        if (height == 0) { return new Maze(new FieldType[0][0]); }

        FieldType[][] fields = new FieldType[height][rows.get(0).length()];

        for (int y = 0; y <height; y++) {
            char[] chars = rows.get(y).toCharArray();
            for (int x = 0; x < chars.length; x++) {
                fields[y][x] = getFieldType(chars[x]);
            }                
        }

        return new Maze(fields);
    }

    private FieldType getFieldType(char ch) {
        switch(ch) {
            case 'w': return FieldType.WALL;
            case 'd': return FieldType.DESERT;
            case 'f': return FieldType.FINISH;
            default:
                throw new IllegalArgumentException(ch + " is not a known field type.");
        }
    }
}

