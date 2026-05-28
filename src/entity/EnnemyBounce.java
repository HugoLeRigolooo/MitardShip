package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyBounce extends Ennemy {

	int m_speed_x;
	int m_speed_y;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyBounce(GamePanel a_gp, int x, int y, int speed, int typeTir, int cadenceTir) {
		super(a_gp, x, y, speed,typeTir, cadenceTir);
		this.getEnnemyImage();
		m_speed_x = speed;
		m_speed_y = speed;
	}
	
	public EnnemyBounce(GamePanel a_gp, int x, int y, int speed_x, int speed_y, int typeTir, int cadenceTir) {
		super(a_gp, x, y, speed_x, typeTir, cadenceTir);
		this.getEnnemyImage();
		m_speed_x = speed_x;
		m_speed_y = speed_y;
	}
	
	public void update_move() {
		m_y += m_speed_y;			
		m_x += m_speed_x;
		if (m_x>m_gp.SCREEN_WIDTH-m_gp.TILE_SIZE) {
			m_x= m_gp.SCREEN_WIDTH-m_gp.TILE_SIZE;
			m_speed_x=-m_speed_x;
		}
		if (m_x<0) {
			m_x= 0;
			m_speed_x=-m_speed_x;
		}
	}
}
