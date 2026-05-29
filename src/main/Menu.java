package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class Menu {
    GamePanel m_gp;
    public int m_commandNum = 0;
    Font pixelFont;
    BufferedImage menuBg;

    public Menu(GamePanel gp) {
        this.m_gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/04B_30__.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            pixelFont = new Font("Monospaced", Font.BOLD, 30); 
        }
        
        try {
            menuBg = ImageIO.read(getClass().getResource("/tiles/fond_menu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (m_gp.m_gameState == m_gp.startState) {
            drawMenuScreen(g2);
        }
    }

    private void drawMenuScreen(Graphics2D g2) {
    	if (menuBg != null) {
            g2.drawImage(menuBg, 0, 0, m_gp.SCREEN_WIDTH+30, m_gp.SCREEN_HEIGHT, null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, m_gp.SCREEN_WIDTH, m_gp.SCREEN_HEIGHT);
        }

        g2.setFont(pixelFont.deriveFont(Font.BOLD, 50F));
        String text = "MITARDSHIP";
        g2.setColor(Color.WHITE);
        
        int x = getXForCenteredText(text, g2);
        int y = m_gp.TILE_SIZE * 15;
        g2.drawString(text, x, y);

        g2.setFont(pixelFont.deriveFont(Font.BOLD, 20F));

        text = "COMMENCER LE JEU";
        x = getXForCenteredText(text, g2);
        y += m_gp.TILE_SIZE * 15;
        g2.drawString(text, x, y);
        if (m_commandNum == 0) {
            g2.drawString(">", x - m_gp.TILE_SIZE * 2, y);
        }

        text = "QUITTER";
        x = getXForCenteredText(text, g2);
        y += m_gp.TILE_SIZE * 5;
        g2.drawString(text, x, y);
        if (m_commandNum == 1) {
            g2.drawString(">", x - m_gp.TILE_SIZE * 2, y);
        }
    }

    private int getXForCenteredText(String text, Graphics2D g2) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (m_gp.SCREEN_WIDTH + 30) / 2 - length / 2;
    }
}