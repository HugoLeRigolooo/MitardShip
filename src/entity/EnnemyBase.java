package entity;



import main.GamePanel;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class EnnemyBase extends Ennemy {

	int m_target_x;
	int m_target_y;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyBase(GamePanel a_gp,int speed, int type_tire, int cadenceTir) {
		super(a_gp,type_tire, cadenceTir);
		Random r = new Random();
		this.setDefaultValues();
		m_y=0;
		this.getEnnemyImage();
		m_target_x = r.nextInt(m_gp.SCREEN_WIDTH+1);
		m_target_y = m_gp.SCREEN_HEIGHT-m_gp.TILE_SIZE;
		m_cooldownTir = 0;
		m_speed = speed;
	}
	
	public EnnemyBase(GamePanel a_gp, int y,int speed, int type_tire, int cadenceTir) {
		super(a_gp,type_tire, cadenceTir);
		Random r = new Random();
		this.setDefaultValues();
		m_y=y;
		this.getEnnemyImage();
		m_target_x = r.nextInt(m_gp.SCREEN_WIDTH+1);
		m_target_y = m_y + m_gp.SCREEN_HEIGHT;
		m_cooldownTir = 0;
		m_speed = speed;
	}
	
	public void update_move() {
		double dx = m_target_x-m_init_x;
		double dy = m_target_y-m_init_y;
		double distance = Math.sqrt(dx * dx + dy * dy);
		m_y += m_speed * (dy/distance);			
		m_x += m_speed * (dx/distance);
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	@Override
	public void getEnnemyImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/ennemy/ennemy_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
