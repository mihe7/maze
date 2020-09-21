public interface GameStateObserver {
    void gameOver();
    void worldChanged(MazeWorld world);
}

