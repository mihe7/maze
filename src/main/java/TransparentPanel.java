import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class TransparentPanel extends JPanel {
    public TransparentPanel() {
        setOpaque(false);
    }

    public TransparentPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
