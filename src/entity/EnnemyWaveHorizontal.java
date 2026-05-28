package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyWaveHorizontal extends Ennemy {

	int m_side;
	int m_amp;
	int m_dir;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyWaveHorizontal(GamePanel a_gp, int x, int y, int speed,int type_tire) {
		super(a_gp, x, y, speed,type_tire);
		Random r = new Random();
		this.m_gp = a_gp;
		this.getEnnemyImage();
		m_amp=50;
		m_dir=0;
		m_side=0;
	}
	
	public EnnemyWaveHorizontal(GamePanel a_gp, int x, int y, int speed, int amp, int type_tire) {
		super(a_gp, x, y, speed,type_tire);
		Random r = new Random();
		this.m_gp = a_gp;
		this.getEnnemyImage();
		m_amp=amp;
		m_dir=0;
		m_side=0;
	}
	
	public EnnemyWaveHorizontal(GamePanel a_gp, int x, int y, int speed, int amp, int dir, int typeTir, int cadenceTir) {
		super(a_gp, x, y, speed, typeTir, cadenceTir);
		Random r = new Random();
		this.m_gp = a_gp;
		this.getEnnemyImage();
		m_side = r.nextInt(2);
		m_amp=amp;
		m_dir=dir;
	}
	
	public EnnemyWaveHorizontal(GamePanel a_gp, int x, int y, int speed, int amp, int dir, int side, int typeTir, int cadenceTir) {
		super(a_gp, x, y, speed,typeTir, cadenceTir);
		Random r = new Random();
		this.m_gp = a_gp;
		this.getEnnemyImage();
		m_side = side;
		m_amp=amp;
		m_dir=dir;
	}
	
	public EnnemyWaveHorizontal(GamePanel a_gp, int typeTir, int cadenceTir) {
		super(a_gp,0,a_gp.SCREEN_HEIGHT,typeTir, cadenceTir);
		Random r = new Random();
		this.m_gp = a_gp;
		this.getEnnemyImage();
		m_side = r.nextInt(2);
		m_amp = m_gp.SCREEN_HEIGHT/8;
	}
	
	public void update_move() {
		if(m_dir == 0) {			
			m_y = (int) (m_init_y + m_amp * Math.sin(m_x*0.05));	
			if (m_side == 0) {
				m_x += m_speed;
			}		
			else {
				m_x -= m_speed;
			}
		}
		else {
			m_x = (int) (m_init_x + m_amp * Math.sin(m_y*0.05));	
			m_y += m_speed;
		}
	}
}
