package dev;

import dev.entities.Player;
import dev.tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    //SCREEN SETTINGS
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    //WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 20;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameTread;
    TileManager tileManager = new TileManager(this);
    public Player player = new Player(this, keyHandler);

    public GamePanel() throws Exception {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameTread = new Thread(this);
        gameTread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(gameTread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(keyHandler.QPressed){
            System.exit(0);
        }

        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileManager.draw(graphics2D);
        player.draw(graphics2D);
        graphics2D.dispose();
    }
}
