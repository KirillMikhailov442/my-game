package dev;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean QPressed, CPressed, VPressed;

    public GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                upPressed = true;
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                downPressed = true;
            }
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                leftPressed = true;
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                rightPressed = true;
            }
            case KeyEvent.VK_Q -> {
                QPressed = true;
            }
            case KeyEvent.VK_C -> {
                CPressed = true;
                gamePanel.zoomInOut(1);
            }
            case KeyEvent.VK_V -> {
                VPressed = true;
                gamePanel.zoomInOut(-1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code){
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                upPressed = false;
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                downPressed = false;
            }
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                leftPressed = false;
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                rightPressed = false;
            }
            case KeyEvent.VK_Q -> {
                QPressed = false;
            }
            case KeyEvent.VK_C -> {
                CPressed = false;
            }
            case KeyEvent.VK_V -> {
                VPressed = false;
            }
        }
    }
}
