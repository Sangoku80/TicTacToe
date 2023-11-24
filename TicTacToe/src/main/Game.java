package main;

public class Game implements Runnable {

    public GamePanel gamePanel;
    public GameWindow gameWindow;
    public boolean running = false;

    public Game()
    {
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(this);
        gamePanel.requestFocus();
    }

    public void startGameLoop() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        int FPS_SET = 120;
        double timePerFrame = 1000000000.0 / FPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaF = 0;

        while (running) {
            long currentTime = System.nanoTime();

            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;

            }
        }


    }
}

