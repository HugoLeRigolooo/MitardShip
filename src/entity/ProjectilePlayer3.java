package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ProjectilePlayer3 extends ProjectilePlayer{
	int m_pos;
	
	public ProjectilePlayer3 (GamePanel a_gp, Player p, int pos,int degat, int speed) {
		super(a_gp,p,degat,speed);
		m_pos=pos;
	}
	public ProjectilePlayer3 (GamePanel a_gp, Player p) {
		super(a_gp,p,5,8);
	}
	protected void setDefaultValues() {
		m_x = m_p.getX() + m_gp.TILE_SIZE * 6/2 - m_gp.TILE_SIZE / 2;
		m_y = m_p.getY()+10;

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
		int m_vitesseDiagonale = 0;
		if (m_pos==1) {
			m_vitesseDiagonale = -2;
		}
		if(m_pos==2)m_vitesseDiagonale = 2;
	     
	    m_x += m_vitesseDiagonale;
	}
	

}
