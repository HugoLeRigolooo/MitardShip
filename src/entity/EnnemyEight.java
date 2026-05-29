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
	int scoreValue;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyEight(GamePanel a_gp, int x, int y, int speed, int amp, int typeTir, int cadenceTir) { // mettre m_speed à 1 pour un comportement classique
		super(a_gp, x, y, speed,typeTir);
		m_amp=amp;
		this.getEnnemyImage();
		m_timer=0;
		scoreValue = 10;
		vie = 20;
	}
	
	public EnnemyEight(GamePanel a_gp, int x, int y, int speed, int typeTir, int cadenceTir) {
		super(a_gp, x, y, speed,typeTir);
		m_amp=50;
		this.getEnnemyImage();
		m_timer=0;
		scoreValue = 10;
		vie = 20;
	}
	
	public EnnemyEight(GamePanel a_gp, int typeTir, int cadenceTir) {
		super(a_gp,typeTir, cadenceTir);
		m_amp=50;
		this.getEnnemyImage();
		m_timer=0;
		scoreValue = 10;
		vie = 20;
	}
	
	public void update_move() {
	    m_timer += 0.02;
	    m_x = (int) (m_init_x + 3* m_amp * Math.sin(m_speed * m_timer));
	    m_y = (int) (m_init_y + m_amp * Math.sin(m_speed * m_timer * 2));
	}
	
	@Override
	public void getEnnemyImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/ennemy/ennemy_4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
