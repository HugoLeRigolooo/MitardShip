package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyKamikaze extends Ennemy {

    private boolean m_sprint;
    private int m_normalSpeed;
    private int m_sprintSpeed;
    private int scoreValue;

    public EnnemyKamikaze(GamePanel a_gp, int x, int y, int typeTir, int cadenceTir) {
        super(a_gp, x, y, 2, typeTir, cadenceTir); 
        this.getEnnemyImage();
        this.scoreValue = 15;
        this.m_vie = 15;
        
        this.m_normalSpeed = 2;
        this.m_sprintSpeed = 12;
        this.m_sprint = false;
    }
    
    public EnnemyKamikaze(GamePanel a_gp, int x, int y, int speed, int typeTir, int cadenceTir) {
        super(a_gp, x, y, speed, typeTir, cadenceTir);
        this.getEnnemyImage();
        this.scoreValue = 15;
        this.m_vie = 15;
        
        this.m_normalSpeed = speed;
        this.m_sprintSpeed = speed * 4;
        this.m_sprint = false;
    }

    @Override
    public void update_move() {
        if (!m_sprint) {
            int playerX = m_gp.m_player.getX();
            int playerY = m_gp.m_player.getY();
            
            int playerWidth = m_gp.TILE_SIZE * 6; 
            int ennemyWidth = m_gp.TILE_SIZE;

            boolean isAligned = (m_x + ennemyWidth >= playerX) && (m_x <= playerX + playerWidth);
            
            boolean isAbovePlayer = m_y < playerY;

            if (isAligned && isAbovePlayer) {
                m_sprint = true;
            }
        }

        if (m_sprint) {
            m_y += m_sprintSpeed;
        } else {
            m_y += m_normalSpeed;
        }
    }
    @Override
	public void getEnnemyImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/ennemy/ennemy_5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
