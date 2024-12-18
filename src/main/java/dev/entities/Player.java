package dev.entities;

import dev.GamePanel;
import dev.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends  Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 2;
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-up-1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-up-2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-down-1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-down-2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-left-1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-left-2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-right-1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/player/player-right-2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed){
                y -= speed;
                direction = Direction.UP;
            }
            else if(keyHandler.downPressed){
                y += speed;
                direction = Direction.DOWN;
            }
            else if(keyHandler.leftPressed){
                x -= speed;
                direction = Direction.LEFT;
            }
            else if(keyHandler.rightPressed){
                x += speed;
                direction = Direction.RIGHT;
            }

            spriteCounter++;
            if(spriteCounter > 10){
                if (spriteNumber == 1){
                    spriteNumber = 2;
                }else {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D){
        BufferedImage image = null;
        switch (direction) {
            case UP:
                if(spriteNumber == 1){
                    image = up1;
                } else if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case DOWN:
                if(spriteNumber == 1){
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case LEFT:
                if(spriteNumber == 1){
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case RIGHT:
                if(spriteNumber == 1){
                    image = right1;
                } else if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        };
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
