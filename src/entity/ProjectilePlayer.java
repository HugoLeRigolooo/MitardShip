package entity;


import main.GamePanel;

public abstract class ProjectilePlayer extends ProjectileBase{
	Player m_p;

	
	public ProjectilePlayer (GamePanel a_gp, Player p, int degat,int speed) {
		super(a_gp,degat,speed);
		m_p=p;
		this.setDefaultValues();
		this.getProjectileImage();
	}
	
	protected abstract void setDefaultValues();
	
	public abstract void getProjectileImage();
	
	public abstract void update();
	
	
	
}



