package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ProjectilePlayer2 extends ProjectilePlayer{
	
	public ProjectilePlayer2 (GamePanel a_gp, Player p) {
		super(a_gp,p);
	}
	protected void setDefaultValues() {
		m_x = m_p.getX()+10;
		m_y = m_p.getY()+10;
		m_speed = 10;
		m_degat=30;
	}
	
	
	public void getProjectileImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/projectiles/projectilePlayer1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		m_y-=m_speed;
	}
	

}
