import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JComponent;

import java.util.EnumMap;
import java.util.Map;

public class WorldView extends JComponent {
    private final int tileWidth;
    private final int tileHeight;

    private final Map<FieldType, Image> images = new EnumMap<>(FieldType.class);
    private Image player;

    private MazeWorld world;
    
    public WorldView(MazeWorld world, int tileWidth, int tileHeight) {
        this.world = world;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        Insets insets = getInsets();
        int w = insets.left + insets.right + 
                tileWidth * world.getMaze().getWidth();
        int h = insets.top + insets.bottom + 
                tileHeight * world.getMaze().getHeight();
        return new Dimension(w, h);
    }

    public void changeWorld(MazeWorld world) {
        this.world = world;
        repaint();
    }

    public void setImage(FieldType type, Image image) {
        images.put(type, requireCompatibleImage(image));
        repaint();
    }

    public void setPlayer(Image image) {
        player = requireCompatibleImage(image);
        repaint();
    }

    private Image requireCompatibleImage(Image image) {
        if (image.getWidth(null) != tileWidth || image.getHeight(null) != tileHeight) {
            String fmt = "Given image doesn't match tile size (%d, %d)";
            String msg = String.format(fmt, tileWidth, tileHeight);
            throw new IllegalArgumentException(msg);
        }
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        paintMap(g);
        paintPlayer(g);
    }

    private void paintMap(Graphics g) {
        Maze maze = world.getMaze();
        for (int y = 0, n = maze.getHeight(); y < n; y++) {
            for (int x = 0, m = maze.getWidth(); x < m; x++) {
                Image img = images.get(maze.get(x, y));
                if (img != null) {
                    g.drawImage(img, x * tileWidth, y * tileHeight, null);
                }
            }
        }
    }

    private void paintPlayer(Graphics g) {
        if (player == null) {
            return;
        }

        Position pos = world.getPlayer();
        g.drawImage(player, pos.x * tileWidth, pos.y * tileHeight, null);
    }
}

