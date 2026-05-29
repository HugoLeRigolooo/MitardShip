package entity;

import main.GamePanel;

public abstract class ProjectileEnnemy extends ProjectileBase{
	
	Ennemy m_e;
	
	
	public ProjectileEnnemy (GamePanel a_gp, Ennemy e, int degat, int speed) {
		super(a_gp, degat, speed);
		m_e=e;
		this.m_x = e.getX() + (a_gp.TILE_SIZE * 3); 
        this.m_y = e.getY() + (a_gp.TILE_SIZE * 6);
		this.getProjectileImage();
	}
	
	public abstract void getProjectileImage();
	
	public abstract void update();
	
	

}
