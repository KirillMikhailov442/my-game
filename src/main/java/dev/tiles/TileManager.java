package dev.tiles;

import dev.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNumbers[][];

    public TileManager(GamePanel gamePanel) throws Exception {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        mapTileNumbers = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        loadMap("/maps/map.txt");
        getTileImage();
    }

    public void loadMap(String filePath) throws Exception {
        try{
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(Objects.requireNonNull(inputStream))));

            for (int y = 0; y < gamePanel.maxWorldRow; y++) {
                String line = bufferedReader.readLine();

                for (int x = 0; x < gamePanel.maxWorldCol; x++) {
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[x]);
                    mapTileNumbers[y][x] = number;
                }
            }
        }catch (Exception e){
            throw  new Exception("The map file is invalid or not found" + "\n" + filePath);
        }
    }

    public void getTileImage(){
        try{
            //grass
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass.png")));

            //water
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/water.png")));
            tiles[1].collision = true;

            //stone
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/stone.png")));
            tiles[2].collision = true;
        } catch (IOException e) {
            throw new RuntimeException("Textures not found");
        }
    }

    public void draw(Graphics2D graphics2D){
        for (int y = 0; y < gamePanel.maxWorldRow; y++) {
            for (int x = 0; x < gamePanel.maxWorldCol; x++) {
                int tileNumber = mapTileNumbers[y][x];
                int worldX = x * gamePanel.tileSize;
                int worldY = y * gamePanel.tileSize;
                double screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                double screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                if(worldX > gamePanel.player.worldX - gamePanel.player.screenX - gamePanel.tileSize &&
                worldX < gamePanel.player.worldX + gamePanel.player.screenX + gamePanel.tileSize &&
                worldY > gamePanel.player.worldY - gamePanel.player.screenY - gamePanel.tileSize &&
                worldY < gamePanel.player.worldY + gamePanel.player.screenY + gamePanel.tileSize){
                    graphics2D.drawImage(tiles[tileNumber].image, (int)screenX, (int)screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }

        }
    }
}
