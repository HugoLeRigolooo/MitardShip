package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import entity.ProjectilePlayer;
import entity.ProjectilePlayer1;
import entity.ProjectilePlayer2;
import entity.ProjectilePlayer3;


/**
 * D�fintition du comportement d'un joueur
 *
 */
public class Player extends Entity{

	GamePanel m_gp;
	KeyHandler m_keyH;
	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Player(GamePanel a_gp, KeyHandler a_keyH) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.setDefaultValues();
		this.getPlayerImage();
		ProjectilePlayer projectilePlayer1 = new ProjectilePlayer1(m_gp, m_keyH);
		ProjectilePlayer projectilePlayer2 = new ProjectilePlayer2(m_gp, m_keyH);
		ProjectilePlayer projectilePlayer3 = new ProjectilePlayer3(m_gp, m_keyH);
		ProjectilePlayer projectile_actuel = projectilePlayer1;
	}
	
	/**
	 * Initialisation des donnees membres avec des valeurs par defaut
	 */
	protected void setDefaultValues() {
		m_x = m_gp.SCREEN_WIDTH / 2 - m_gp.TILE_SIZE / 2;
		m_y = m_gp.SCREEN_HEIGHT - 100;
		m_speed = 4;
		m_collision_ennemi = false;
		m_collision_projectile = false;
		vie = 100;
	}
	
	/**
	 * R�cup�ration de l'image du personnage
	 */
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/player/vaisseau.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Mise � jour des donn�es du joueur
	 */
	public void update() {
        if (m_keyH.upPressed) {
            m_y -= m_speed;
        }
        if (m_keyH.downPressed) {
            m_y += m_speed;
        }
        if (m_keyH.leftPressed) {
            m_x -= m_speed;
        }
        if (m_keyH.rightPressed) {
            m_x += m_speed;
        }

		if (m_x < 0){
			m_x = 0;
		} 
		if (m_y < 0){
			m_y = 0;
		}
		if (m_x > m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE){
			m_x = m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE;
		}
		if (m_y > m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE){
			m_y = m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE;
		}

    }
	
	/**
	 * Affichage du l'image du joueur dans la fenetre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	
	int getX() {
		return m_x;
	}

	int getY() {
		return m_y;
	}
	
}
