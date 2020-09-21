import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.io.File;

import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadMazeAction extends AbstractAction {
    private final MazeLoader loader;
    private final JFileChooser chooser;

    public LoadMazeAction(MazeLoader loader) {
        super("Labyrinth laden...");
        this.loader = loader;

        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        putValue(Action.DISPLAYED_MNEMONIC_INDEX_KEY, 10);

        chooser = new JFileChooser(".");
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter(
                "Maze Text File", "mtf"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File mazeFile = selectFile(getWindow(e));
        if (mazeFile != null) {
            loader.load(mazeFile);
        }
    }

    private File selectFile(Window parent) {
        int result = chooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private Window getWindow(ActionEvent e) {
        Object src = e.getSource();
        if (src instanceof JComponent) {
            return SwingUtilities.getWindowAncestor((JComponent) src);
        }
        return null;
    }
}

