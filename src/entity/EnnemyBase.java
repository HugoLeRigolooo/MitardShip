package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyBase extends Ennemy {

	int m_target_x;
	int m_target_y;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public EnnemyBase(GamePanel a_gp, int y_depart, int y_target,int type_tire, int cadenceTir) {
		super(a_gp,type_tire, cadenceTir);
		Random r = new Random();
		this.setDefaultValues();
		m_y=y_depart;
		this.getEnnemyImage();
		m_target_x = r.nextInt(m_gp.SCREEN_WIDTH+1);
		m_target_y = y_target;
		m_cooldownTir = 0;
	}
	
	public void update_move() {
		double dx = m_target_x-m_init_x;
		double dy = m_target_y-m_init_y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		m_y += m_speed * (dy/distance);			
		m_x += m_speed * (dx/distance);
	}
}
