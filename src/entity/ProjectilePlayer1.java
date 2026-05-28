package entity;


import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
public class ProjectilePlayer1 extends ProjectilePlayer{
	
	public ProjectilePlayer1 (GamePanel a_gp, Player p) {
		super(a_gp,p);
	}
	protected void setDefaultValues() {
		m_x = m_p.getX() + m_gp.TILE_SIZE * 6/2 - m_gp.TILE_SIZE / 2;
		m_y = m_p.getY() + 10;
		m_speed = 8;
		m_degat=20;
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
