package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyKamikaze extends Ennemy {

	int m_target_x;
	int m_target_y;
	int scoreValue;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public EnnemyKamikaze(GamePanel a_gp, int typeTir, int cadenceTir) {
		super(a_gp,typeTir, cadenceTir);
		Random r = new Random();
		this.m_gp = a_gp;
		this.setDefaultValues();
		this.getEnnemyImage();
		m_target_x = r.nextInt(m_gp.SCREEN_WIDTH+1);
		m_target_y = m_gp.SCREEN_HEIGHT;
		scoreValue = 10;
		vie = 20;
	}
	
	public void update_move() {
		double dx = m_target_x-m_init_x;
		double dy = m_target_y-m_init_y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		m_y += m_speed * (dy/distance);			
		m_x += m_speed * (dx/distance);
		
	}
}
