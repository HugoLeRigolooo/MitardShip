package main;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import entity.Ennemy;
import entity.EnnemyBase;
import entity.Player;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameOver {
    GamePanel gp;
    public int commandNum = 0;
    KeyHandler m_keyH;

    public GameOver(GamePanel gp, KeyHandler a_keyH) {
        this.gp = gp;
        this.m_keyH = a_keyH;
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);

        String text = "MISSION TERMINÉE";
        g2.setColor(Color.WHITE);
        
        int x = getXforCenteredText(text, g2);
        int y = gp.SCREEN_HEIGHT / 4;
        g2.drawString(text, x, y);
        
        text = "REJOUER";
        x = getXforCenteredText(text, g2);
        y += gp.TILE_SIZE * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        text = "QUITTER";
        x = getXforCenteredText(text, g2);
        y += gp.TILE_SIZE * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public int getXforCenteredText(String text, Graphics2D g2) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.SCREEN_WIDTH / 2 - length / 2;
    }
} 