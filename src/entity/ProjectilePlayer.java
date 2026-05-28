package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public abstract class ProjectilePlayer extends ProjectileBase{
	Player m_p;
	
	
	public ProjectilePlayer (GamePanel a_gp, Player p) {
		super(a_gp,0,0);
		m_p=p;
		this.setDefaultValues();
		this.getProjectileImage();
	}
	
	protected abstract void setDefaultValues();
	
	public abstract void getProjectileImage();
	
	public abstract void update();
	
	
	
}



