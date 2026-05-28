package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel m_gp;
    KeyHandler m_keyH;
    public ProjectilePlayer projectile_actuel;
    BufferedImage m_idleImageVie;

    public Player(GamePanel a_gp, KeyHandler a_keyH) {
        this.m_gp = a_gp;
        this.m_keyH = a_keyH;
        setDefaultValues();
        getPlayerImage();
    }

    protected void setDefaultValues() {
        m_x = m_gp.SCREEN_WIDTH / 2 - (m_gp.TILE_SIZE * 6) / 2;
        m_y = m_gp.SCREEN_HEIGHT - 100;
        m_speed = 4;
        vie = 100;
        
        projectile_actuel = new ProjectilePlayer1(m_gp, this);
        getVieImage();
    }

    public void getPlayerImage() {
        try {
            m_idleImage = ImageIO.read(getClass().getResource("/player/vaisseau.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getVieImage() {
        try {
            String path = "/barre_vie/";
            if (vie > 80) path += "100.png";
            else if (vie > 60) path += "80.png";
            else if (vie > 40) path += "60.png";
            else if (vie > 20) path += "40.png";
			else if (vie > 0) path += "20.png";
            else path += "0.png";
            m_idleImageVie = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (m_keyH.upPressed) m_y -= m_speed;
        if (m_keyH.downPressed) m_y += m_speed;
        if (m_keyH.leftPressed) m_x -= m_speed;
        if (m_keyH.rightPressed) m_x += m_speed;

        if (m_x < 0) m_x = 0;
        if (m_y < 0) m_y = 0;
        if (m_x > m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE * 6) m_x = m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE * 6;
        if (m_y > m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE * 6) m_y = m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE * 6;

        int leftX = m_x;
        int rightX = m_x + (m_gp.TILE_SIZE * 6) - 1; 
        int topY = m_y;
        int bottomY = m_y + (m_gp.TILE_SIZE * 6) - 1;

        int leftCol = leftX / m_gp.TILE_SIZE;
        int rightCol = rightX / m_gp.TILE_SIZE;
        int topRow = (topY - m_gp.m_position) / m_gp.TILE_SIZE;
        int bottomRow = (bottomY - m_gp.m_position) / m_gp.TILE_SIZE;

        int tileTopLeft = m_gp.getTileManager().getTileNum(leftCol, topRow);
        int tileTopRight = m_gp.getTileManager().getTileNum(rightCol, topRow);
        int tileBottomLeft = m_gp.getTileManager().getTileNum(leftCol, bottomRow);
        int tileBottomRight = m_gp.getTileManager().getTileNum(rightCol, bottomRow);

        if (tileTopLeft == 7 || tileTopLeft == 8 ||
            tileTopRight == 7 || tileTopRight == 8 ||
            tileBottomLeft == 7 || tileBottomLeft == 8 ||
            tileBottomRight == 7 || tileBottomRight == 8) {
            
            m_gp.m_gameState = m_gp.gameOverState;
        }
        getVieImage();
    }

    public void draw(Graphics2D a_g2) {
        a_g2.drawImage(m_idleImage, m_x, m_y, m_gp.TILE_SIZE * 6, m_gp.TILE_SIZE * 6, null);
        if (m_idleImageVie != null) {
            a_g2.drawImage(m_idleImageVie, 10, 10, m_gp.TILE_SIZE * 9, m_gp.TILE_SIZE * 3, null);
        }
    }
	
	int getX() {
		return m_x;
	}

	int getY() {
		return m_y;
	}
	
}
