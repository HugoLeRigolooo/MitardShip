package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ProjectilePlayer2 extends ProjectilePlayer{
	
	public ProjectilePlayer2 (GamePanel a_gp, Player p, int degat, int speed) {
		super(a_gp,p,degat,speed);
	}
	public ProjectilePlayer2 (GamePanel a_gp, Player p) {
		super(a_gp,p,5,8);
	}
	protected void setDefaultValues() {
		m_x = m_p.getX();
		m_y = m_p.getY() + 10;

	}
	
	
	public void getProjectileImage() {
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/projectiles/projectilePlayer1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getDegat() {return m_degat;}
	public void update() {
		m_y-=m_speed;
	}
	

}
