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

public class Ennemy extends Entity {

	GamePanel m_gp;
	KeyHandler m_keyH;
	int m_init_x;
	int m_init_y;
	static List<Ennemy> listEnnemy = new ArrayList<Ennemy>();
	int m_vie;
	ArrayList<ProjectileEnnemy> m_projectiles;
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Ennemy(GamePanel a_gp) {
		this.m_gp = a_gp;
		this.setDefaultValues();
		this.getEnnemyImage();
		listEnnemy.add(this);
		this.m_projectiles = new ArrayList<>();
	}
	
	public Ennemy(GamePanel a_gp, int x, int y) {
		this.m_gp = a_gp;
		m_x=m_init_x=x;
		m_y=m_init_y=y;
		m_speed = 2;
		this.getEnnemyImage();
		listEnnemy.add(this);
		this.m_projectiles = new ArrayList<>();
	}
	
	public Ennemy(GamePanel a_gp, int x, int y, int speed) {
		this.m_gp = a_gp;
		m_x= m_init_x =x;
		m_y = m_init_y =  y;
		m_speed = speed;
		this.getEnnemyImage();
		listEnnemy.add(this);
		this.m_projectiles = new ArrayList<>();
	}
	
	int getVie() {
		return m_vie;
	}
	
	void substractVie(int vie) {
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

	/**
	 * Mise � jour des donn�es du joueur
	 */
	public void update() {
		
		
		
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
