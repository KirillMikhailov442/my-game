package dev.entities;

import java.awt.image.BufferedImage;

public class Entity {
    public double worldX, worldY;
    public double speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction = Direction.DOWN;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

}
