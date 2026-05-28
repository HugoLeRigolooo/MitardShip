package entity;

import java.awt.image.BufferedImage;

/**
 * Entit� de base du jeu
 *
 */
public abstract class Entity {
	public int m_x, m_y;				//position sur la map
	public int m_speed;					//D�placement de l'entit�
	public BufferedImage m_idleImage;	//Une image de l'entit�
	public boolean m_collision_ennemi;		//Indique si l'entit� est en collision avec une autre entit�
	public boolean m_collision_projectile;	//Indique si l'entit� est en collision avec un projectile
	public int vie;						//Vie de l'entit�
	int getX() {
		return m_x;
	}

	int getY() {
		return m_y;
	}
	
}
