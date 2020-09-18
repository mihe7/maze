import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MessageOverlay {
    private final JFrame frame;

    private JComponent component;
    private Runnable runOnDismiss;

    private final KeyListener dismissByKeyboard = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            JComponent panel = (JComponent) e.getSource();
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                panel.setVisible(false);
                if (runOnDismiss != null) {
                    runOnDismiss.run();
                }
            }
        }
    };

    public MessageOverlay(JFrame frame) {
        this.frame = frame;
    }

    public MessageOverlay display(String text) {
        return display(new JLabel(text, SwingConstants.CENTER));
    }

    public MessageOverlay display(JComponent component) {
        this.component = component;
        return this;
    }

    public MessageOverlay onDismiss(Runnable runnable) {
        runOnDismiss = runnable;
        return this;
    }

    public void show() {
        TransparentPanel panel = new TransparentPanel(new BorderLayout());
        panel.setFocusable(true);
        panel.setBackground(new Color(255, 255, 255, 200));
        panel.addKeyListener(dismissByKeyboard);
        panel.add(component);

        frame.setGlassPane(panel);
        panel.setVisible(true);
        panel.requestFocus();
    }

}

