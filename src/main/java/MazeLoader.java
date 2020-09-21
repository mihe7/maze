import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MazeLoader {
    private static final MazeTextFormat FORMAT = new MazeTextFormat();

    private MazeHolder holder;
    private ExceptionHandler exceptionHandler;

    public void setMazeHolder(MazeHolder holder) {
        this.holder = holder;
    }

    public void setExceptionHandler(ExceptionHandler handler) {
        exceptionHandler = handler;
    }

    public void load(File file) {
        try(FileReader reader = new FileReader(file)) {
            Maze maze = FORMAT.read(reader);
            if (holder != null) {
                holder.setMaze(maze);
            }
        } catch (IllegalArgumentException | IOException ex) {
            if (exceptionHandler != null) {
                exceptionHandler.handleException(ex);
            } else {
                ex.printStackTrace();
            }
        }
    }
}

