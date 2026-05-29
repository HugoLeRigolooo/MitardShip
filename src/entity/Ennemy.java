package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Ennemy extends Entity {

	GamePanel m_gp;
	KeyHandler m_keyH;
	int m_init_x;
	int m_init_y;
	public static List<Ennemy> listEnnemy = new ArrayList<Ennemy>();
	int m_vie;
	ArrayList<ProjectileEnnemy> m_projectiles;
	int m_cooldownTir; 
	int m_cadenceTir;
	int m_typeTir;
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Ennemy(GamePanel a_gp, int typeTir, int cadenceTir) {
		this.m_gp = a_gp;
		this.setDefaultValues();
		this.getEnnemyImage();
		listEnnemy.add(this);
		this.m_projectiles = new ArrayList<>();
		m_typeTir = typeTir;
		m_cadenceTir= cadenceTir;
	}
	
	public Ennemy(GamePanel a_gp, int x, int y, int type_tire, int cadenceTir) {
		this.m_gp = a_gp;
		m_x=m_init_x=x;
		m_y=m_init_y=y;
		m_speed = 2;
		this.getEnnemyImage();


		m_typeTir = type_tire;
		m_cadenceTir= cadenceTir;
		listEnnemy.add(this);
		this.m_projectiles = new ArrayList<>();

	}
	
	public Ennemy(GamePanel a_gp, int x, int y, int speed, int type_tire, int cadenceTir) {
		this.m_gp = a_gp;
		m_x= m_init_x =x;
		m_y = m_init_y =  y;
		m_speed = speed;
		this.getEnnemyImage();
		m_typeTir = type_tire;
		m_cadenceTir= cadenceTir;
		listEnnemy.add(this);
		this.m_projectiles = new ArrayList<>();
	}
	
	public int getVie() {
		return m_vie;
	}
	
	public void substractVie(int vie) {
		m_vie -= vie;
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		Random m = new Random();
		m_x = m_init_x = m.nextInt(m_gp.SCREEN_WIDTH+1);
		m_y = m_init_y =  0;
		m_speed = 2;
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getEnnemyImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/ennemy/ennemy_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void update_move();
	
	/**
	 * Mise � jour des donn�es du joueur
	 */
	
	
	
	public void update() {
		update_move();
		if (m_cooldownTir > 0) {
			m_cooldownTir--; 
		}
		if (m_cooldownTir == 0) {
				tirer();
				m_cooldownTir = m_cadenceTir; 
		}
		for (int i = m_projectiles.size() - 1; i >= 0; i--) {
			ProjectileEnnemy p = m_projectiles.get(i);
			p.update();
			

			if (p.getY() < -m_gp.TILE_SIZE) {
				m_projectiles.remove(i);
			}
		}
	}
	public int getX() {
		return m_x;
	}

	public int getY() {
		return m_y;
	}
	  public ArrayList<ProjectileEnnemy> getProjectiles(){return m_projectiles;}
	

	
	
	public void tirer() {
		if (m_typeTir==1) {
			ProjectileEnnemy missile = new ProjectileEnnemy1(m_gp, this);
			m_projectiles.add(missile);
		}
		else if(m_typeTir==2) {
			ProjectileEnnemy missile = new ProjectileEnnemy2(m_gp, this);
			m_projectiles.add(missile);
		}
	}

	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {
		for (int i = 0; i < m_projectiles.size(); i++) {
			m_projectiles.get(i).draw(a_g2);
		}
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE *6, m_gp.TILE_SIZE*6, null);
	}
}
