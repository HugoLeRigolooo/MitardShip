package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyCircularVertical extends Ennemy {

	int m_radius;
	double m_angle;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyCircularVertical(GamePanel a_gp, int x, int y, int speed, int typeTir, int cadenceTir) {
		super(a_gp,x,y,speed,typeTir, cadenceTir);
		m_angle=0;
		m_radius= 20;
		this.getEnnemyImage();
	}
	
	public EnnemyCircularVertical(GamePanel a_gp, int x, int y, int speed, int radius, int typeTir, int cadenceTir) {
		super(a_gp,x,y,speed,typeTir, cadenceTir);
		m_angle=0;
		m_radius=radius;
		this.getEnnemyImage();
	}
	
	public void update_move() {
		m_init_y += m_speed; 
	    
	    m_angle += 0.1; 
	    
	    m_x = (int) (m_init_x + m_radius * Math.cos(m_angle));
	    m_y = (int) (m_init_y + m_radius * Math.sin(m_angle));
		
	}
}
