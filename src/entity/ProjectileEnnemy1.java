package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ProjectileEnnemy1 extends ProjectileEnnemy {
	public ProjectileEnnemy1 (GamePanel a_gp, Ennemy e, int degat, int speed) {
		super(a_gp,e, degat, speed);

	}
	
	public ProjectileEnnemy1 (GamePanel a_gp, Ennemy e) {
		super(a_gp,e, 20, 8);

	}
	
	
	public void getProjectileImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/projectiles/projectileEnnemy1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getDegat() {return m_degat;}
	public void update() {
		m_y+=m_speed;
	}
	
}


