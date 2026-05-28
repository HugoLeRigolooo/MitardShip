package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyEight extends Ennemy {

	int m_amp;
	double m_timer;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyEight(GamePanel a_gp, int x, int y, int speed, int amp) { // mettre m_speed à 1 pour un comportement classique
		super(a_gp, x, y, speed);
		m_amp=amp;
		this.getEnnemyImage();
		m_timer=0;
	}
	
	public EnnemyEight(GamePanel a_gp, int x, int y, int speed) {
		super(a_gp, x, y, speed);
		m_amp=50;
		this.getEnnemyImage();
		m_timer=0;
	}
	
	public EnnemyEight(GamePanel a_gp) {
		super(a_gp);
		m_amp=50;
		this.getEnnemyImage();
		m_timer=0;
	}
	
	public void update() {
	    m_timer += 0.02;
	    m_x = (int) (m_init_x + 3* m_amp * Math.sin(m_speed * m_timer));
	    m_y = (int) (m_init_y + m_amp * Math.sin(m_speed * m_timer * 2));
	}
}
