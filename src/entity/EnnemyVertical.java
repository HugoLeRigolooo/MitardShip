package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.Random;

public class EnnemyVertical extends Ennemy {

	int scoreValue;
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	
	public EnnemyVertical(GamePanel a_gp, int x, int y,int typeTir, int cadenceTir) {
		super(a_gp, x,y,2,typeTir, cadenceTir);
		this.getEnnemyImage();
		scoreValue = 10;// supp mettre dans ennemy 
		m_vie = 20;
	}
	
	public EnnemyVertical(GamePanel a_gp, int x, int y, int speed,int typeTir, int cadenceTir) {
		super(a_gp, x ,y, speed,typeTir ,cadenceTir );
		this.getEnnemyImage();
		scoreValue = 10;
		m_vie = 20;
	}
	
	public void update_move() {
		m_y += m_speed;
	}
	@Override
	public void getEnnemyImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/ennemy/ennemy_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
