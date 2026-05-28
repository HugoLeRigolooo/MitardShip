package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyVertical extends Ennemy {

	int m_target_x;
	int m_target_y;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyVertical(GamePanel a_gp, int x, int y) {
		super(a_gp, x,y,2);
		this.getEnnemyImage();
	}
	
	public EnnemyVertical(GamePanel a_gp, int x, int y, int speed) {
		super(a_gp, x ,y, speed);
		this.getEnnemyImage();
	}
	
	public void update() {
		double dx = m_target_x-m_init_x;
		double dy = m_target_y-m_init_y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		m_y += m_speed * (dy/distance);			
		m_x += m_speed * (dx/distance);
		
	}
}
