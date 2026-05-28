package entity;

import main.GamePanel;

public abstract class ProjectileEnnemy extends ProjectileBase{
	
	Ennemy m_e;
	
	
	public ProjectileEnnemy (GamePanel a_gp, Ennemy e) {
		super(a_gp);
		m_e=e;
		this.setDefaultValues();
		this.getProjectileImage();
	}
	
	protected abstract void setDefaultValues();
	
	public abstract void getProjectileImage();
	
	public abstract void update();
	
	

}
