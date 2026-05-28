package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;


public abstract class ProjectileBase extends Entity{
	int m_degat;
	GamePanel m_gp;
	boolean en_collision; //si le projectile a une collision se met sur true donc le projectile ne s'affiche plus


	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param degat int, degat du projectile
	 * @param e Entity, tireur du projectile
	 
	 */
	public ProjectileBase(GamePanel a_gp) {
		this.m_gp = a_gp;

		en_collision = false;
	}
	
	
	protected abstract void setDefaultValues();
	
	/**
	 * R�cup�ration de l'image de projectile
	 */
	public abstract void getProjectileImage();
	
	public void collision() {
		if(false) {
		en_collision = true;}
	}
	
	public int getDegat() {return m_degat;}
	
	public abstract void update();

	public void draw(Graphics2D a_g2) {
		if(en_collision==false) {

			BufferedImage l_image = m_idleImage;
			a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);}
		}
	
	
	
	public int getX() {return m_x;}
	public int getY() {return m_y;}
	
	
	public void setX(int x) {m_x=x;}
	public void setY(int y) {m_y=y;}

}
