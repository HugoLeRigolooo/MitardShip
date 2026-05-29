package main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Ennemy;
import entity.EnnemyBase;
import entity.Player;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class GameOver {
    GamePanel gp;
    public int commandNum = 0;
    public boolean win=true;
    public int m_score;
    KeyHandler m_keyH;
    Font pixelFont;
    

    public GameOver(GamePanel gp, KeyHandler a_keyH) {
        this.gp = gp;
        this.m_keyH = a_keyH;
        this.m_score=0;
//        try {
//            InputStream is = getClass().getResourceAsStream("/font/04B_30__.ttf");
//            if (is == null) {
//                throw new IOException("Fichier introuvable !");
//            }
//            // createFont ne définit pas la taille, on ajoute deriveFont derrière
//            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
//            is.close(); // Important de fermer le flux après lecture
//        } catch (FontFormatException | IOException e) {
//            System.out.println("Erreur chargement police : " + e.getMessage());
//            // Solution de secours (Fallback) pour que le jeu ne crash pas
//            pixelFont = new Font("Arial", Font.BOLD, 30);
//        }
    }
    
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.SCREEN_WIDTH, gp.SCREEN_HEIGHT);
        //g2.setFont(pixelFont.deriveFont(Font.BOLD, 50F));
        g2.setColor(Color.WHITE);
        String text="";
        int x = 0;
        int y = gp.SCREEN_HEIGHT / 4;
        if (win) {
        	text = "MISSION TERMINÉE";
        	x = getXforCenteredText(text, g2);
            g2.drawString(text, x, y);
            text = "SCORE : "+m_score;
            x = getXforCenteredText(text, g2);
            y += gp.TILE_SIZE * 3;
            g2.drawString(text, x, y);
        }else {
        	text = "GAME OVER";
        	x = getXforCenteredText(text, g2);
            g2.drawString(text, x, y);
        }
        
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