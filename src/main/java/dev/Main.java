package dev;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        try{
            JFrame window = new JFrame();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle(Constants.TITLE + " (version " + Constants.VERSION + ")");

            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);
            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.startGameThread();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}