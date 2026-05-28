package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ProjectileEnnemy1 extends ProjectileEnnemy {
	public ProjectileEnnemy1 (GamePanel a_gp, Ennemy e) {
		super(a_gp,e);
	}
	protected void setDefaultValues() {
		//m_x = m_e.getX()+10;
		//m_y = m_e.getY()+10;
		m_speed = 8;
		m_degat=20;
	}
	
	
	public void getProjectileImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/projectiles/projectileEnnemy1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		m_y+=m_speed;
	}
	
}


