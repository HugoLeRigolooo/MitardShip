package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.ArrayList;

import entity.Ennemy;
import entity.EnnemyBase;
import entity.EnnemyKamikaze;
import entity.EnnemyCircularVertical;
import entity.Player;
import entity.ProjectileEnnemy;
import entity.ProjectilePlayer;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Panel principal du jeu contenant la map principale
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	//Param�tres de l'�cran
	final int ORIGINAL_TILE_SIZE = 4; 							// une tuile de taille 16x16
	final int SCALE = 3; 										// �chelle utilis�e pour agrandir l'affichage
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; 	// 48x48
	public final int MAX_SCREEN_COL = 80;						// Nombre de tuile en longueur de l'ensemble affiché
	public final int MAX_WORLD_COL= 160;							// Nombre de tuile en longueur de l'ensemble de la map
	public final int MAX_SCREE_ROW = 60;							// Nombre de tuile en largeur de l'ensemble affiché
	public final int MAX_WORLD_ROW= 300;							// Nombre de tuile en largeur de l'ensemble de la map
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREE_ROW;	// 576 pixels

	// FPS : taux de rafraichissement
	int m_FPS;
	
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler m_keyH;
	Thread m_gameThread;
	Player m_player;
	TileManager m_tileM;
	public Menu m_menu;
	GameOver m_gameover;
	
	public int m_position; //position de la caméra
	public int score; //score du joueur
	
	//parametre des gamestate
	public final int startState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int gameOverState = 3;
	public int m_gameState; // État du jeu
	public int compteur; //pour bouton menu gameover
		
	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;				
		m_keyH = new KeyHandler(this);
		m_player = new Player(this, m_keyH);
		m_tileM = new TileManager(this);
		m_menu = new Menu(this);
		m_position=-(MAX_WORLD_ROW * TILE_SIZE) + SCREEN_HEIGHT;
		m_gameState=startState;
		m_gameover=new GameOver(this, m_keyH);
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH+30, SCREEN_HEIGHT)); //+30 : Place pour la barre de progression
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.setFocusable(true);
	}
	
	/**
	 * Lancement du thread principal
	 */
	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}

	public TileManager getTileManager() {
		return m_tileM;
	}
	
	public void run() {
		
		double drawInterval = 1000000000/m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 
		
		while(m_gameThread != null) { //Tant que le thread du jeu est actif
			
			//Permet de mettre � jour les diff�rentes variables du jeu
			this.update();
			
			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			this.repaint();
			
			//Calcule le temps de pause du thread
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void checkCollisions() {
	    
		ArrayList<ProjectilePlayer> tirsJoueur = m_player.getProjectiles();
	    

	    for (int i = Ennemy.listEnnemy.size() - 1; i >= 0; i--) {
	        Ennemy ennemy = Ennemy.listEnnemy.get(i);
	        
	      
	        Rectangle hitboxEnnemi = new Rectangle(ennemy.getX(), ennemy.getY(), TILE_SIZE, TILE_SIZE);
	        
	        
	        for (int j = tirsJoueur.size() - 1; j >= 0; j--) {
	            ProjectilePlayer pPlayer = tirsJoueur.get(j);
	            
	           
	            Rectangle hitboxProjectilePlayer = new Rectangle(pPlayer.getX(), pPlayer.getY(), TILE_SIZE, TILE_SIZE);
	            
	            if (hitboxEnnemi.intersects(hitboxProjectilePlayer)) {
	            
	              
	                ennemy.substractVie(pPlayer.getDegat()); 
	                tirsJoueur.remove(j);                   
	                
	              
	                if (ennemy.getVie() <= 0) {
	                    Ennemy.listEnnemy.remove(i);
	                    System.out.println("Ennemi détruit !");
	                }
	                
	                break; // On sort de la boucle des projectiles car cet ennemi a déjà été géré pour cette frame
	            }
	        }
	    }

	
	
	    int tailleHitbox = TILE_SIZE * 2; 
	    int offsetX = (TILE_SIZE * 6 - tailleHitbox) / 2; 
	    int offsetY = (TILE_SIZE * 6 - tailleHitbox) / 2; 

	    Rectangle hitboxJoueur = new Rectangle(m_player.getX() + offsetX, m_player.getY() + offsetY, tailleHitbox, tailleHitbox);
	    
	   
	    for (Ennemy ennemy : Ennemy.listEnnemy) {
	       
	    	
	    	ArrayList<ProjectileEnnemy>  tirsEnnemi = ennemy.getProjectiles(); 
	        
	        for (int k = tirsEnnemi.size() - 1; k >= 0; k--) {
	            ProjectileEnnemy pEnnemy = tirsEnnemi.get(k);
	            
	     
	            Rectangle hitboxProjectileEnnemy = new Rectangle(pEnnemy.getX(), pEnnemy.getY(), TILE_SIZE, TILE_SIZE);
	            
	            
	            if (hitboxJoueur.intersects(hitboxProjectileEnnemy)) {
	  
	                m_player.setVie(pEnnemy.getDegat());
	                tirsEnnemi.remove(k);            
	                
	                System.out.println("Joueur touché ! Vie restante : " + m_player.getVie());
	                
	                if (m_player.getVie() <= 0) {
	                	 this.m_gameState = gameOverState;
	
	                }
	            }
	        }
	    }
	}
	


	public void drawProgression(Graphics2D g2) {
		int hauteur=SCREEN_HEIGHT-25;
		int largeur=10;
		int x=SCREEN_WIDTH+5;
		int y=10;
		g2.setColor(Color.WHITE);
		g2.drawRect(x, y, largeur, hauteur);
		g2.setColor(Color.YELLOW);
		double hauteur_avancer=((((double) TILE_SIZE*(double) MAX_WORLD_ROW)+m_position)*(double) hauteur)/((double) TILE_SIZE*(double) MAX_WORLD_ROW);
		g2.fillRect(x+1, ((int) hauteur+y)-(int) hauteur_avancer, largeur-1, (int) hauteur_avancer);
	}
	
	

	/**
	 * Mise � jour des donn�es des entit�s
	 */
	public void update() {
		if (m_gameState==playState) {
			m_player.update();
			for (int i = Ennemy.listEnnemy.size() - 1; i >= 0; i--) {
				Ennemy.listEnnemy.get(i).update();
			}
			

			checkCollisions();

			
			m_player.update();
			m_position+=5;
		}
	}
	
	/**
	 * Affichage des �l�ments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    if (m_gameState == startState) {
	        m_menu.draw(g2);
	    }else if (m_gameState==playState){
			m_tileM.draw(g2);
			m_player.draw(g2);

			for (int i = 0; i < Ennemy.listEnnemy.size(); i++) {
				Ennemy.listEnnemy.get(i).draw(g2);
			}

			drawProgression(g2);

			g2.setColor(Color.WHITE);
			g2.drawString("Score: " + score, 50, 50);
		}else if(m_gameState==gameOverState) {
			m_tileM.draw(g2);
			m_player.draw(g2);
			drawProgression(g2);
			m_gameover.draw(g2);
		}
		g2.dispose();
	}
	
}