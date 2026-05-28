package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public abstract class ProjectilePlayer extends ProjectileBase{
	Player m_p;
	
	
	public ProjectilePlayer (GamePanel a_gp, Player p) {
		super(a_gp);
		m_p=p;
		this.setDefaultValues();
		this.getProjectileImage();
	}
	
	protected abstract void setDefaultValues();
	
	public abstract void getProjectileImage();
	
	public void update() {
		m_y+=m_speed;
	}
	
	
	
	
}



