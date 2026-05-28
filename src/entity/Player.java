package entity;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel m_gp;
    KeyHandler m_keyH;
	ArrayList<ProjectilePlayer> m_projectiles;
	private int m_cooldownTir; 
	private int m_cadenceTir;
	int m_vie;
	
    BufferedImage m_idleImageVie;

    public Player(GamePanel a_gp, KeyHandler a_keyH) {
        this.m_gp = a_gp;
        this.m_keyH = a_keyH;
        setDefaultValues();
        getPlayerImage();
		this.m_projectiles = new ArrayList<>();
    }

    protected void setDefaultValues() {
        m_x = m_gp.SCREEN_WIDTH / 2 - (m_gp.TILE_SIZE * 6) / 2;
        m_y = m_gp.SCREEN_HEIGHT - 100;
        m_speed = 4;
        m_vie = 100;
		m_cooldownTir = 0;   
		m_cadenceTir = 10;
		
        

        getVieImage();
    }

    public void getPlayerImage() {
        try {
            m_idleImage = ImageIO.read(getClass().getResource("/player/vaisseau.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getVieImage() {
        try {
            String path = "/barre_vie/";
            if (vie > 80) path += "100.png";
            else if (vie > 60) path += "80.png";
            else if (vie > 40) path += "60.png";
            else if (vie > 20) path += "40.png";
			else if (vie > 0) path += "20.png";
            else path += "0.png";
            m_idleImageVie = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void tirer(int mode) {
		if(mode==0) {

			ProjectilePlayer missile = new ProjectilePlayer1(m_gp, this,5,8);
	
			m_projectiles.add(missile);}
		
		else if (mode==1) {
		
			ProjectilePlayer mGauche = new ProjectilePlayer2(m_gp, this,5,8);
			
			mGauche.setX(this.m_x);
			
	
			ProjectilePlayer mDroit = new ProjectilePlayer2(m_gp, this,5,8);
			mDroit.setX(this.m_x + 60);
			
			m_projectiles.add(mGauche);
			m_projectiles.add(mDroit);}
		
		else {
			
			ProjectilePlayer mGauche = new ProjectilePlayer3(m_gp, this,1,5,8);
			mGauche.setX(this.m_x + 20);
			
			ProjectilePlayer mMilieu = new ProjectilePlayer3(m_gp, this,0,5,8); 
			
			ProjectilePlayer mDroit = new ProjectilePlayer3(m_gp, this,2,5,8);
			mDroit.setX(this.m_x + 35);
			
			m_projectiles.add(mGauche);
			m_projectiles.add(mMilieu);
			m_projectiles.add(mDroit);}
		}
    public ArrayList<ProjectilePlayer> getProjectiles(){return m_projectiles;}

    public void update() {
        if (m_keyH.upPressed) m_y -= m_speed;
        if (m_keyH.downPressed) m_y += m_speed;
        if (m_keyH.leftPressed) m_x -= m_speed;
        if (m_keyH.rightPressed) m_x += m_speed;

        if (m_x < 0) m_x = 0;
        if (m_y < 0) m_y = 0;
        if (m_x > m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE * 6) m_x = m_gp.SCREEN_WIDTH - m_gp.TILE_SIZE * 6;
        if (m_y > m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE * 6) m_y = m_gp.SCREEN_HEIGHT - m_gp.TILE_SIZE * 6;

        int leftX = m_x;
        int rightX = m_x + (m_gp.TILE_SIZE * 6) - 1; 
        int topY = m_y;
        int bottomY = m_y + (m_gp.TILE_SIZE * 6) - 1;

        int leftCol = leftX / m_gp.TILE_SIZE;
        int rightCol = rightX / m_gp.TILE_SIZE;
        int topRow = (topY - m_gp.m_position) / m_gp.TILE_SIZE;
        int bottomRow = (bottomY - m_gp.m_position) / m_gp.TILE_SIZE;

        int tileTopLeft = m_gp.getTileManager().getTileNum(leftCol, topRow);
        int tileTopRight = m_gp.getTileManager().getTileNum(rightCol, topRow);
        int tileBottomLeft = m_gp.getTileManager().getTileNum(leftCol, bottomRow);
        int tileBottomRight = m_gp.getTileManager().getTileNum(rightCol, bottomRow);

        if (tileTopLeft == 7 || tileTopLeft == 8 ||
            tileTopRight == 7 || tileTopRight == 8 ||
            tileBottomLeft == 7 || tileBottomLeft == 8 ||
            tileBottomRight == 7 || tileBottomRight == 8) {
            
            m_gp.m_gameState = m_gp.gameOverState;
        }
        getVieImage();
        
        if (m_cooldownTir > 0) {
			m_cooldownTir--; 
		}
		
		if (m_keyH.spacePressed && m_cooldownTir == 0) {
			tirer(3);
			m_cooldownTir = m_cadenceTir; 
		}
		
		for (int i = m_projectiles.size() - 1; i >= 0; i--) {
			ProjectilePlayer p = m_projectiles.get(i);
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
	public int getVie() {
		return m_vie;
	}
	public void setVie(int degat) {
		m_vie-=degat;
	}

    public void draw(Graphics2D a_g2) {
    	for (int i = 0; i < m_projectiles.size(); i++) {
			m_projectiles.get(i).draw(a_g2);
		}
        a_g2.drawImage(m_idleImage, m_x, m_y, m_gp.TILE_SIZE * 6, m_gp.TILE_SIZE * 6, null);
        if (m_idleImageVie != null) {
            a_g2.drawImage(m_idleImageVie, 10, 10, m_gp.TILE_SIZE * 9, m_gp.TILE_SIZE * 3, null);
        }
    }
	

}
