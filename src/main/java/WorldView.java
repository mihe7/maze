import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JComponent;

import java.util.EnumMap;
import java.util.Map;

public class WorldView extends JComponent {
    private final Size tileSize;

    private final Map<FieldType, Image> images = new EnumMap<>(FieldType.class);
    private Image player;

    private MazeWorld world;
    private MazeWorldObserver observer = new MazeWorldObserver() {
        @Override
        public void playerMoved(Position from, Position to) {
            repaint();
        }
    };
    
    public WorldView(MazeWorld world, int tileWidth, int tileHeight) {
        this(world, new Size(tileWidth, tileHeight));
    }

    public WorldView(MazeWorld world, Size tileSize) {
        this.world = world;
        this.tileSize = tileSize;
        installObserver();
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet()) {
            return super.getPreferredSize();
        }
        Insets insets = getInsets();
        Size mazeSize = world.getMaze().getSize();
        int w = insets.left + insets.right + 
                tileSize.width * mazeSize.width;
        int h = insets.top + insets.bottom + 
                tileSize.height * mazeSize.height;
        return new Dimension(w, h);
    }

    public void changeWorld(MazeWorld world) {
        uninstallObserver();
        this.world = world;
        installObserver();
        repaint();
    }

    private void uninstallObserver() {
        if (world != null) {
            world.setObserver(null);
        }
    }

    private void installObserver() {
        if (world != null) {
            world.setObserver(observer);
        }
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
        if (image.getWidth(null) != tileSize.width || 
                image.getHeight(null) != tileSize.height) {
            String fmt = "Given image doesn't match tile size (%d, %d)";
            String msg = String.format(fmt, tileSize.width, tileSize.height);
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
        Size mazeSize = maze.getSize();
        for (int y = 0; y < mazeSize.height; y++) {
            for (int x = 0; x < mazeSize.width; x++) {
                Image img = images.get(maze.get(x, y));
                if (img != null) {
                    paintImage(g, img, x, y);
                }
            }
        }
    }

    private void paintPlayer(Graphics g) {
        if (player == null) {
            return;
        }

        Position pos = world.getPlayer();
        paintImage(g, player, pos.x, pos.y);
    }

    private void paintImage(Graphics g, Image img, int xTile, int yTile) {
        g.drawImage(img, xTile * tileSize.width, yTile * tileSize.height, null);
    }
}

